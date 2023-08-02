package com.ckw.blog_sys.controller;

import com.ckw.blog_sys.pojo.Comments;
import com.ckw.blog_sys.service.Impl.CommentsServiceImpl;
import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CommentsController {
    @Autowired
    private CommentsServiceImpl commentsService;

    @PostMapping("/addComments")
    public Object addComments(@RequestBody Comments comments){
        return new Message(State.SUCCESS,commentsService.addComments(comments),"评论成功!");
    }
    @GetMapping("/getCommentsList/{bid}/{uid}")
    public Object getCommentsList(@PathVariable long bid,@PathVariable long uid){
        return new Message(State.SUCCESS,commentsService.getCommentsList(bid,uid),"获取成功!");
    }

    @GetMapping("/likeComments/{uid}/{cid}/{state}")
    public Object likeBlog(@PathVariable long uid,@PathVariable long cid,@PathVariable int state){

        boolean flag = commentsService.addCommentsLike(cid,uid,state);
        return new  Message(flag ? State.SUCCESS : State.FAILURE,
                flag,
                flag ? state == 0 ? "取消点赞":"点赞成功!" : "操作失败!"
        );
    }

    @GetMapping("/removeComments/{cid}/{bid}")
    public Object removeComments(@PathVariable long cid,@PathVariable long bid){
        boolean remove = commentsService.removeComments(cid,bid);
        return new Message(
                remove ? State.SUCCESS : State.FAILURE,
                remove,
                remove ? "删除成功!" : "删除失败!"
        );
    }

}
