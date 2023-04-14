package com.ckw.match.controller;

import com.ckw.common.pojo.Message;
import com.ckw.common.pojo.State;
import com.ckw.match.pojo.Match;
import com.ckw.match.server.impl.MatchServiceImpl;
import com.ckw.question.pojo.MatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 凯威
 */
@RestController
public class MatchController {

    @Autowired
    private MatchServiceImpl matchServer;

    @PostMapping("/addMatch")
    public Object addMatch(@RequestBody Match match){

        return new Message(State.SUCCESS,matchServer.addMatch(match),"操作成功!");
    }


    @PostMapping("/getMatchList")
    public Object getMatchList(){
        return new Message(State.SUCCESS,matchServer.getMatchList(),"操作成功!");
    }


    @GetMapping("/getMatchDetail/{mid}")
    public Object getMatchDetail(@PathVariable int mid){
        return new Message(State.SUCCESS,matchServer.getMatchDetail(mid),"操作成功");
    }

    @GetMapping("/participateMatch/{uid}/{mid}")
    public Object participateMatch(@PathVariable int uid,@PathVariable int mid){
        return new Message(State.SUCCESS,matchServer.participateMatch(uid,mid),"操作成功!");
    }

    @GetMapping("/getUserMatch/{uid}")
    public Object getUserMatch(@PathVariable int uid){
        return new Message(State.SUCCESS,matchServer.getUserMatch(uid),"操作成功!");
    }
    @GetMapping("/getMatchRank/{mid}")
    public Object getMatchRank(@PathVariable int mid){
        List<MatchResult> matchResult = matchServer.getMatchResult(mid);
        return new Message(State.SUCCESS,matchResult,"操作成功");
    }

    @GetMapping("/deleteMatch/{mid}")
    public Object deleteMatch(@PathVariable int mid){
        boolean flag = matchServer.deleteMatch(mid);
        return new Message(flag?State.SUCCESS:State.FAILURE,flag,flag?"操作成功":"操作失败");
    }

}
