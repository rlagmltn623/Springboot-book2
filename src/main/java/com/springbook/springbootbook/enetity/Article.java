package com.springbook.springbootbook.enetity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 코드를 작성하지 않아도 된다.
@ToString
@Entity
@Getter
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }
//
//    Article(){
//
//    }

//    //생성자 추가를 대신하는 어노테이션 @AllArgsConstructor
//    public Article(Long id, String title, String content){
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//  tostring 을 대신하는 어노테이션 @ToString 이걸 사용하면 된다.
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }


}
