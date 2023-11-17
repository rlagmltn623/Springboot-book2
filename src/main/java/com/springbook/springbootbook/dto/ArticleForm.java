package com.springbook.springbootbook.dto;

import com.springbook.springbootbook.enetity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
//@RequiredArgsConstructor //생성자
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

//    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//
//
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity(){
        return new Article(id, title, content); //생성자 입력 양식에 맞게 작성

    }
}
