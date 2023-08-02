package com.ckw.blog_sys.service.Impl;

import com.ckw.blog_sys.mapper.BlogMapper;
import com.ckw.blog_sys.mapper.CommentsMapper;
import com.ckw.blog_sys.pojo.Comments;
import com.ckw.blog_sys.service.CommentsService;
import com.ckw.common.utils.DateUtils;
import com.ckw.common.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 插入评论
     * @param comments
     * @return
     */
    @Transactional
    @Override
    public boolean addComments(Comments comments) {
        comments.setTime(DateUtils.getCurrentTime());
        comments.setId(SnowflakeIdWorker.snowFlow.nextId());
        blogMapper.addBlogCommentsCount(comments.getBid(),1);
        return commentsMapper.insertComments(comments);
    }

    /**
     * 得到某个帖子得评论列表
     *
     * @param bid
     * @return
     */
    @Override
    public List<Comments> getCommentsList(long bid,long uid) {
        List<Comments> bLogComments = commentsMapper.getBLogComments(bid,uid);
        System.out.println(bLogComments);
        return bLogComments;
    }

    /**
     * 给帖子点赞
     *
     * @param bid
     * @param uid
     * @param state
     * @return
     */
    @Override
    public boolean addCommentsLike(long bid, long uid, int state) {
        //        看看有没有点踩、点赞的操作
        Integer blogState = commentsMapper.queryLikeState(uid, bid);

        if(blogState == null){
//            这个用户还没有对这个帖子进行过任何操作

            Integer integer = commentsMapper.newUserLike(uid, bid);
            return integer > 0;
        }
//       有过操作的数据
        Integer integer = commentsMapper.changeUserLikeState(uid, bid, state);
//        添加、减少 帖子点赞用户数量
        commentsMapper.changeCommentsLikeCount(bid,state == 1 ? 1 : -1);
        return integer > 0;
    }

    /**
     * 删除评论
     *
     * @param cid
     * @return
     */
    @Override
    @Transactional
    public boolean removeComments(long cid,long bid) {
        blogMapper.addBlogCommentsCount(bid,-1);
        return commentsMapper.removeComments(cid) > 0;
    }
}
