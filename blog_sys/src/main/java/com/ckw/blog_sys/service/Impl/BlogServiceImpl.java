package com.ckw.blog_sys.service.Impl;

import com.ckw.blog_sys.mapper.BlogMapper;
import com.ckw.blog_sys.pojo.Blog;
import com.ckw.blog_sys.service.BlogService;
import com.ckw.common.utils.DateUtils;
import com.ckw.common.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Echo
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogMapper blogMapper;

    /**
     * @param blog
     * @return
     */
    @Override
    public boolean publishBlog(Blog blog) {
        blog.setTime(DateUtils.getCurrentTime());
        blog.setId(String.valueOf(SnowflakeIdWorker.snowFlow.nextId()));
        blog.getTag().replace("\\","");
        return blogMapper.insertBlog(blog);
    }

    /**
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Blog> getBlogList(int page, int size,long uid,String type) {
        return blogMapper.getBlogList(page,size,uid,"#"+type);
    }

    /**
     * 给帖子点赞
     *
     * @param bid
     * @param uid
     * @param state
     * @return
     */
    @Transactional
    @Override
    public boolean addBlogLike(long bid, long uid,int state) {
//        看看有没有点踩、点赞的操作
        Integer blogState = blogMapper.queryLikeState(uid, bid);

        if(blogState == null){
//            这个用户还没有对这个帖子进行过任何操作

            Integer integer = blogMapper.newUserLike(uid, bid);
            return integer > 0;
        }
//       有过操作的数据
        Integer integer = blogMapper.changeUserLikeState(uid, bid, state);
//        添加、减少 帖子点赞用户数量
        blogMapper.changeBlogLikeCount(bid,state == 1 ? 1 : -1);
        return integer > 0;
    }

    /**
     * 收藏帖子
     *
     * @param bid
     * @param uid
     * @param state
     * @return
     */
    @Override
    public boolean addBlogStar(long bid, long uid, int state) {
        log.info(bid + "," + uid + "," + state);
        Integer starState = blogMapper.queryStarState(uid, bid);
        if(starState == null){
//            该用户没有对这个帖子收藏
            Integer integer = blogMapper.newUserStar(uid, bid);
            return integer > 0;
        }
        Integer integer = blogMapper.changeUserStarState(uid, bid, state);
        blogMapper.changeBlogStarCount(bid,state == 1 ? 1 : -1);
        return integer > 0;
    }

    /**
     * 根据博客id得到博客信息
     *
     * @param bid
     * @return
     */
    @Override
    public Blog getBlogById(long uid,long bid) {
        return blogMapper.queryBlog(uid, bid);
    }

    /**
     * 搜索帖子
     *
     * @param content 搜索内容
     * @return 得到的帖子结果
     */
    @Override
    public Blog queryBlogByContent(String content,long uid) {
        return blogMapper.queryBlogByContent(content,uid);
    }

    /**
     * @param bid
     * @return
     */
    @Override
    public boolean removeBlog(long bid) {
        return blogMapper.removeBlog(bid) > 0;
    }
}
