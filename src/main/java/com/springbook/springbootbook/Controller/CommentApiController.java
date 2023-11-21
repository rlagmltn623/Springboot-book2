package com.springbook.springbootbook.Controller;

import com.springbook.springbootbook.Repository.AritcleRepository;
import com.springbook.springbootbook.Repository.CommentRepository;
import com.springbook.springbootbook.Service.CommentService;
import com.springbook.springbootbook.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService; //댓글 서비스 객체 주입

    //1. 댓글 조회
    //2. 댓글 생성
    //3. 댓글 수정
    //4. 댓글 삭제제

    @Autowired
    private CommentRepository commentRepository; //댓글 리파지터리 객체 주입
    @Autowired
    private AritcleRepository aritcleRepository; //게시글 리파지터리 객체 주입입


    // 리스트 뷰 보여주기
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentDto>  dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 만들기
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){ //create 메서드 생성

        //서비스의 위임
        CommentDto createDto = commentService.create(articleId, dto);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);

    }

    //수정하기
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        //서비스 위임
        CommentDto upddateDto = commentService.update(id, dto);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(upddateDto);
    }

}
