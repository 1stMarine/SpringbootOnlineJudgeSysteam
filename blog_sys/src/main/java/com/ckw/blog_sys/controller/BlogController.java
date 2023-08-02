package com.ckw.blog_sys.controller;

import com.ckw.blog_sys.pojo.Blog;
import com.ckw.blog_sys.service.Impl.BlogServiceImpl;
import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

    /**
     * 发布帖子
     * @param blog
     * @return
     */
    @PostMapping("/uploadBlog")
    public Object uploadBlog(@RequestBody Blog blog){
        System.out.println(blog);
        boolean flag = blogService.publishBlog(blog);
        return new  Message(flag ? State.SUCCESS : State.FAILURE,
                        flag,
                        flag ? "发布成功!" : "发布失败!"
                );
    }

    @GetMapping("/getBlogList/{page}/{size}/{uid}/{type}")
    public Object getBlogLIst(@PathVariable int page,@PathVariable int size,@PathVariable long uid,@PathVariable String type){
        return new Message(State.SUCCESS,blogService.getBlogList(page,size,uid,type),"获取成功!");
    }

    @GetMapping("/likeBlog/{uid}/{bid}/{state}")
    public Object likeBlog(@PathVariable long uid,@PathVariable long bid,@PathVariable int state){
        boolean flag = blogService.addBlogLike(bid,uid,state);
        return new  Message(flag ? State.SUCCESS : State.FAILURE,
                flag,
                flag ? state == 0 ? "取消点赞":"点赞成功!" : "操作失败!"
        );
    }
    @GetMapping("/starBlog/{uid}/{bid}/{state}")
    public Object starBlog(@PathVariable long uid,@PathVariable long bid,@PathVariable int state){

        boolean flag = blogService.addBlogStar(bid,uid,state);
        return new  Message(flag ? State.SUCCESS : State.FAILURE,
                flag,
                flag ? state == 0 ? "取消收藏":"收藏成功!" : "操作失败!"
        );
    }

    /**
     * 根据id查blog
     * @param uid
     * @param bid
     * @return
     */
    @GetMapping("/queryBlog/{uid}/{bid}")
    public Object queryUp(@PathVariable long uid,@PathVariable long bid){
        Blog blogById = blogService.getBlogById(uid,bid);
        return new Message(blogById == null ? State.FAILURE : State.SUCCESS,blogById,blogById == null ? "查询失败":"查询成功");
    }

    @GetMapping("/queryBlogByContent/{content}/{uid}")
    public Object queryBlogByContent(@PathVariable String content,@PathVariable long uid){
        return new Message(State.SUCCESS,blogService.queryBlogByContent(content,uid),"查询成功！");
    }

    @GetMapping("/removeBlog/{bid}")
    public Object removeBlog(@PathVariable long bid){
        boolean remove = blogService.removeBlog(bid);
        return new Message(
                remove ? State.SUCCESS : State.FAILURE,
                remove,
                remove ? "删除成功!" : "删除失败"
        );
    }

}
