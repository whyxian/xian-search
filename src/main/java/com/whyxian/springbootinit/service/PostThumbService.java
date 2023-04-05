package com.whyxian.springbootinit.service;

import com.whyxian.springbootinit.model.entity.PostThumb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whyxian.springbootinit.model.entity.User;

/**
 * 帖子点赞服务
 *
 * @author whyxian
 * @version 1.0
 * @date 2023-04-01 00:00
 */
public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostThumb(long postId, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);
}
