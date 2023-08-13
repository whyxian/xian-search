package com.whyxian.springbootinit.model.dto.search;

import com.whyxian.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @program: xian-search-project
 * @description: 聚合搜索请求
 * @author: whyxian
 * @create: 2023-05-17 21:06
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRequest extends PageRequest implements Serializable {

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 类型
     */
    private String type;

    private static final long serialVersionUID = 1L;
}
