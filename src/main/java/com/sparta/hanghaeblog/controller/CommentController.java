package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.CommentRequestDto;
import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService CommentService;
    @PostMapping("/api/Comment")
    public Comment createComment(@RequestBody CommentRequestDto requestDto, User user){
        return CommentService.createComment(requestDto, user.getUsername());
    }
    //@RequestMapping(value = "/write", method = RequestMethod.POST)
    //public String posttWrite(ReplyVO vo) throws Exception {

        //replyService.wirte(vo);

        //return "redirect:board/view?bno=" + vo.getBno();

    }
