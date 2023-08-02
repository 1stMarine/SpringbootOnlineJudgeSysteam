package com.ckw.blog_sys.mapper;

import com.ckw.blog_sys.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Blog Mapper
 * @author Echo
 */
@Mapper
public interface BlogMapper {

    /**
     * 插入一个新的帖子
     * @param blog
     * @return
     */
    boolean insertBlog(Blog blog);

    /**
     * 得到帖子列表
     * @param page
     * @param size
     * @return
     */
    List<Blog> getBlogList(int page, int size,long uid,String type);

    /**
     * 改变帖子点赞数量
     * @param bid
     * @param count
     * @return
     */
    Integer changeBlogLikeCount(long bid,int count);

    Integer changeBlogStarCount(long bid,int count);
    /**
     * 查询这个用户在这个帖子有无点赞
     * @param uid
     * @param bid
     * @return
     */
    Integer queryLikeState(long uid,long bid);

    Integer queryStarState(long uid,long bid);
    /**
     * 新建id为uid的用户对id为bid的帖子点赞操作记录
     * @param uid
     * @param bid
     * @return
     */
    Integer newUserLike(long uid,long bid);

    /**
     * 为某用户新建一个帖子收藏记录
     * @param uid
     * @param bid
     * @return
     */
    Integer newUserStar(long uid,long bid);

    /**
     * 改变用户对帖子的收藏状态
     * @param uid
     * @param bid
     * @param state
     * @return
     */
    Integer changeUserStarState(long uid,long bid,int state);
    /**
     * 改变用户对帖子的点赞状态
     * @param uid
     * @param bid
     * @param state
     * @return
     */
    Integer changeUserLikeState(long uid,long bid,int state);

    /**
     * 根据博客id查找博客
     * @param bid
     * @return
     */
    Blog queryBlog(long uid,long bid);

    /**
     * 添加、减少评论次数
     * @param bid
     * @param num
     * @return
     */
    Integer addBlogCommentsCount(long bid,int num);

    /**
     * 搜索帖子内容
     * @param content
     * @return
     */
    Blog queryBlogByContent(String content,long uid);

    Integer removeBlog(long bid);
}
