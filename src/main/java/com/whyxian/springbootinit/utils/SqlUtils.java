package com.whyxian.springbootinit.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL 工具
 *
 * @author whyxian
 * @version 1.0
 * @date 2023-04-01 00:00
 */
public class SqlUtils {

    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField
     * @return
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
