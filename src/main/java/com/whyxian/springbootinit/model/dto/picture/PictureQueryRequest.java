package com.whyxian.springbootinit.model.dto.picture;

import com.whyxian.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @program: xian-search-project
 * @description: 图片查询请求
 * @author: whyxian
 * @create: 2023-05-17 18:59
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {

    private String searchText;

    private static final long serialVersionUID = 1L;

}
