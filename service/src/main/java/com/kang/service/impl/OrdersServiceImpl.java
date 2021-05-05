package com.kang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kang.BO.SubmitOrderBO;
import com.kang.BO.center.OrderItemsCommentBO;
import com.kang.VO.ShopcartVO;
import com.kang.common.enums.OrderStatusEnum;
import com.kang.common.enums.YesNoEnum;
import com.kang.common.exception.KangException;
import com.kang.common.org.n3r.idworker.Sid;
import com.kang.mapper.OrdersMapper;
import com.kang.pojo.*;
import com.kang.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, OrdersEntity> implements OrdersService {

    @Autowired
    private Sid sid;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private UserAddressService addressService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private ItemsCommentsService commentsService;

    @Override
    public String createOrder(SubmitOrderBO orderInfo) {
        String itemSpecIds = orderInfo.getItemSpecIds();
        List<ShopcartVO> voList = itemsService.searchItemsBySpecIds(itemSpecIds);
        if (CollectionUtils.isEmpty(voList)) {
            throw new KangException("订单商品信息不存在，创建失败！");
        }
        // 1.插入order_items表
        String orderId = sid.nextShort();
        List<OrderItemsEntity> orderItemsList = voList.stream()
                .map(vo -> getOrderItemByCart(orderId, vo))
                .collect(Collectors.toList());
        orderItemsService.saveBatch(orderItemsList);

        // 2.插入orders表
        // 订单原价总和
        int totalAmount = 0;
        // 订单实际支付总和
        int realPayAmount = 0;
        for (ShopcartVO vo : voList) {
            int buyCount = getBuyCountFromRedis(vo.getSpecId());
            totalAmount += Integer.parseInt(vo.getPriceNormal()) * buyCount;
            realPayAmount += Integer.parseInt(vo.getPriceDiscount()) * buyCount;
            // 3.规格表中扣除库存
            itemsService.decreaseStock(vo.getSpecId(), buyCount);
        }
        OrdersEntity orders = getOrders(orderId, orderInfo, totalAmount, realPayAmount);
        this.save(orders);

        // 4.插入order_status表
        OrderStatusEntity orderStatus = getOrderStatus(orderId);
        orderStatusService.save(orderStatus);
        return orderId;
    }

    @Override
    public OrdersEntity getOrderByUserId(String orderId, String userId) {
        return this.lambdaQuery()
                .eq(OrdersEntity::getId, orderId)
                .eq(OrdersEntity::getUserId, userId)
                .eq(OrdersEntity::getIsDelete, YesNoEnum.NO.getCode())
                .one();
    }

    @Override
    public void saveComment(String orderId, String userId, List<OrderItemsCommentBO> boList) {
        Date now = new Date();
        // 1.插入items_comments表
        List<ItemsCommentsEntity> itemsCommentList = getItemsCommentList(userId, boList, now);
        commentsService.saveBatch(itemsCommentList);
        // 2.修改orders表
        this.lambdaUpdate()
                .set(OrdersEntity::getIsComment, YesNoEnum.YES.getCode())
                .set(OrdersEntity::getUpdatedTime, now)
                .eq(OrdersEntity::getId, orderId)
                .eq(OrdersEntity::getIsComment, YesNoEnum.NO.getCode())
                .eq(OrdersEntity::getUserId, userId)
                .update();
        // 3.修改order_status表
        orderStatusService.lambdaUpdate()
                .set(OrderStatusEntity::getCommentTime, now)
                .eq(OrderStatusEntity::getOrderId, orderId)
                .update();
    }

    private int getBuyCountFromRedis(String itemSpecId) {
        System.out.println(itemSpecId);
        // TODO 整合redis后，商品购买的数量重新从redis的购物车中获取
        return 1;
    }

    /**
     * 通过source生成orderItems对象
     * @param orderId 订单id
     * @param source 源对象
     * @return orderItems对象
     */
    private OrderItemsEntity getOrderItemByCart(String orderId, ShopcartVO source) {
        OrderItemsEntity orderItem = new OrderItemsEntity();
        orderItem.setId(sid.nextShort());
        orderItem.setOrderId(orderId);
        orderItem.setItemId(source.getItemId());
        orderItem.setItemImg(source.getItemImgUrl());
        orderItem.setItemName(source.getItemName());
        orderItem.setItemSpecId(source.getSpecId());
        orderItem.setItemSpecName(source.getSpecName());
        orderItem.setPrice(Integer.valueOf(source.getPriceDiscount()));
        orderItem.setBuyCounts(getBuyCountFromRedis(source.getSpecId()));
        return orderItem;
    }

    /**
     * 通过表单信息生成订单对象
     * @param orderId 订单id
     * @param orderBO 表单信息
     * @param totalAmount 订单原价总和
     * @param realPayAmount 订单实际支付总和
     * @return 订单对象
     */
    private OrdersEntity getOrders(String orderId, SubmitOrderBO orderBO, Integer totalAmount, Integer realPayAmount) {
        OrdersEntity orders = new OrdersEntity();
        orders.setId(orderId);
        orders.setUserId(orderBO.getUserId());
        UserAddressEntity addressEntity = addressService.lambdaQuery().eq(UserAddressEntity::getId, orderBO.getAddressId())
                .one();
        orders.setReceiverName(addressEntity.getReceiver());
        orders.setReceiverMobile(addressEntity.getMobile());
        orders.setReceiverAddress(addressEntity.getAddress());
        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        // 暂定包邮
        orders.setPostAmount(0);
        orders.setPayMethod(orderBO.getPayMethod());
        orders.setLeftMsg(orderBO.getLeftMsg());
        orders.setIsComment(YesNoEnum.NO.getCode());
        orders.setIsDelete(YesNoEnum.NO.getCode());
        Date now = new Date();
        orders.setCreatedTime(now);
        orders.setUpdatedTime(now);
        return orders;
    }

    private OrderStatusEntity getOrderStatus(String orderId) {
        OrderStatusEntity statusEntity = new OrderStatusEntity();
        statusEntity.setOrderId(orderId);
        statusEntity.setOrderStatus(OrderStatusEnum.WAIT_PAY.getValue());
        statusEntity.setCreatedTime(new Date());
        return statusEntity;
    }

    /**
     * 将bo转成entity
     * @param userId 用户id
     * @param boList 源list
     * @param now 当前时间
     * @return 目标list
     */
    private List<ItemsCommentsEntity> getItemsCommentList(
            String userId, List<OrderItemsCommentBO> boList, Date now) {
        List<ItemsCommentsEntity> commentList = new ArrayList<>(boList.size());
        for (OrderItemsCommentBO commentBO : boList) {
            ItemsCommentsEntity entity = new ItemsCommentsEntity();
            BeanUtils.copyProperties(commentBO, entity);
            entity.setId(sid.nextShort());
            entity.setUserId(userId);
            entity.setSepcName(commentBO.getItemSpecName());
            entity.setCreatedTime(now);
            entity.setUpdatedTime(now);
            commentList.add(entity);
        }
        return commentList;
    }
}