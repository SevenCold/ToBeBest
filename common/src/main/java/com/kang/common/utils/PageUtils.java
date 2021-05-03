package com.kang.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author kang
 */
@Data
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	private int records;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 总页数
	 */
	private int total;
	/**
	 * 当前页数
	 */
	private int page;
	/**
	 * 列表数据
	 */
	private List<?> rows;
	
	/**
	 * 分页
	 * @param rows        列表数据
	 * @param records  总记录数
	 * @param pageSize    每页记录数
	 * @param page    当前页数
	 */
	public PageUtils(List<?> rows, int records, int pageSize, int page) {
		this.rows = rows;
		this.records = records;
		this.pageSize = pageSize;
		this.page = page;
		this.total = (int)Math.ceil((double) records /pageSize);
	}

	/**
	 * 分页
	 */
	public PageUtils(IPage<?> page) {
		this.rows = page.getRecords();
		this.records = (int)page.getTotal();
		this.pageSize = (int)page.getSize();
		this.page = (int)page.getCurrent();
		this.total = (int)page.getPages();
	}

	public PageUtils(IPage<?> page, List<?> records) {
		this.rows = records;
		this.records = (int)page.getTotal();
		this.pageSize = (int)page.getSize();
		this.page = (int)page.getCurrent();
		this.total = (int)page.getPages();
	}
	
}
