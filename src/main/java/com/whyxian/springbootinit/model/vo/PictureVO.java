package com.whyxian.springbootinit.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: xian-search-project
 * @description: 图片
 * @author: whyxian
 * @create: 2023-05-17 18:52
 **/
@Data
public class PictureVO implements Serializable {

    /**
     * 图片标题
     */
    private String title;

    /**
     * 图片URL
     */
    private String url;

    private static final long serialVersionUID = 1L;

}
