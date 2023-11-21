package com.springbook.springbootbook.Service;

import com.springbook.springbootbook.Repository.AritcleRepository;
import com.springbook.springbootbook.Repository.CommentRepository;
import com.springbook.springbootbook.dto.CommentDto;
import com.springbook.springbootbook.enetity.Article;
import com.springbook.springbootbook.enetity.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AritcleRepository aritcleRepository;

    public List<CommentDto> comments(Long articleId){
        //1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        //2. 엔티티 -> DTO 변환
//        List<CommentDto>  dtos = new ArrayList<CommentDto>();
//        for(int i = 0; i < comments.size(); i++){ // 1. 조회한 댓글 엔티티 수 만큼 반복하기
//            Comment c = comments.get(i); //2. 조회한 댓글 엔티티 하나씩 가져오기
//            CommentDto dto = CommentDto.createCommentDto(c); //3. 엔티티를 DTO로 변환
//            dtos.add(dto); //4. 변환한 DTO를 dtos 리스트에 삽입
//
//        }
//        //3. 결과 반환
        return commentRepository.finByArticleId(articleId)//댓글 엔티티 목록조회
                .stream() //댓글 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) //엔티티를 DTO로 매핑
                .collect(Collectors.toList()); //스트림을 리스트로 변환
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //1.게시글 조회 및 예외 발생
        Article article = aritcleRepository.findById(articleId).orElseThrow(
                ()->new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));
        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        //3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        //4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);


   }

    public CommentDto update(Long id, CommentDto dto) {
        //1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 없습니다."));
        //2. 댓글 수정
        target.patch(dto);
        //3. DB로 갱신
        Comment updated = commentRepository.save(target);

        //4. 댓글 엔티티를 DTO로 변환 및 반환788
        return CommentDto.createCommentDto(updated);
    }
}