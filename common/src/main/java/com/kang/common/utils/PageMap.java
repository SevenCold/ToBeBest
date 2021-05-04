package com.kang.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 汪少
 * @date 2021/5/4 17:18
 */
public class PageMap extends HashMap<String, Object> {
    @Override
    public PageMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    private PageMap() {

    }

    public static PageMap getInstance() {
        return new PageMap();
    }

    public static PageMap getInstance(Object page, Object pageSize) {
        PageMap pageMap = new PageMap();
        pageMap.put("page", page);
        pageMap.put("pageSize", pageSize);
        return pageMap;
    }

    public Map<String, Object> getParamsMap() {
        return this;
    }
}
