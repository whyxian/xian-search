package com.whyxian.springbootinit.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @program: xian-search-project
 * @description: 聚合搜索VO
 * @author: whyxian
 * @create: 2023-05-17 20:55
 **/
public class AggregateSearchVO implements Serializable {

    private List<UserVO> userVOList;

    private List<PostVO> postVOList;

    private List<PictureVO> pictureVOList;

    private static final long serialVersionUID = 1L;

}
