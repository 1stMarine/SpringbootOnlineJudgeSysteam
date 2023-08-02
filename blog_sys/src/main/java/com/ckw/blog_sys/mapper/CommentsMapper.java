package com.ckw.blog_sys.mapper;

import com.ckw.blog_sys.pojo.Comments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {
    /**
     * 插入一条评论
     * @param comments
     * @return
     */
    boolean insertComments(Comments comments);

    /**
     * 得到blog评论
     * @param bid
     * @return
     */
    List<Comments> getBLogComments(long bid,long uid);
    /**
     * 查询这个用户在这个评论有无点赞
     * @param uid
     * @param bid
     * @return
     */
    Integer queryLikeState(long uid,long bid);
    /**
     * 新建用户喜欢记录
     * @param uid
     * @param cid
     * @return
     */
    Integer newUserLike(long uid,long cid);
    /**
     * 改变用户对评论的点赞状态
     * @param uid
     * @param bid
     * @param state
     * @return
     */
    Integer changeUserLikeState(long uid,long bid,int state);

    /**
     * 改变评论点赞数量
     * @param bid
     * @param count
     * @return
     */
    Integer changeCommentsLikeCount(long bid,int count);

    /**
     * 删除评论
     * @param cid
     * @return
     */
    Integer removeComments(long cid);

}
