package com.springbook.springbootbook.enetity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 GETTER 메서드 자동생성
@ToString //모든 필드를 출력할 수 있는 tostring 메서드 자동 생성
@AllArgsConstructor //모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor //매개 변수가 아예 없는 기본 생성자 자동생성
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //대표키

    @ManyToOne //Comment 엔티티와 article 엔티티를 다대일 관계로 설정
    @JoinColumn(name = "article_id")
    private Article article; //해당 댓글의 부모 게시글

    @Column
    private String nickname; //댓글을 단 사람

    @Column
    private String body; //댓글 본문
}
