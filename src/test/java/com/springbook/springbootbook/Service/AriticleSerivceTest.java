package com.springbook.springbootbook.Service;

import com.springbook.springbootbook.dto.ArticleForm;
import com.springbook.springbootbook.enetity.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AriticleSerivceTest {

    @Autowired
    AriticleSerivce ariticleSerivce; //ariticleSerivce 객체 주입

    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected  = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. 실제 데이터
        List<Article> articles  = ariticleSerivce.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_성공_존재하는_id_입력() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //2. 실제 데이터
        Article article = ariticleSerivce.show(id);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    void show_실패() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = null; //에상 데이터를 null로 작성
        //2. 실제 데이터
        Article article = ariticleSerivce.show(id);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    void create_성공() {

        //1. 예상 데이터
       String title = "라라라라";
       String content = "4444";
       ArticleForm dto = new ArticleForm(null, title, content);
       Article expected = new Article(4L, title, content);
        //2. 실제 데이터
        Article article = ariticleSerivce.create(dto);

        //3. 비교 및 검증
       assertEquals(expected.toString(), article.toString());
    }

//    @Test
//    void create_실패() {
//
//        //1. 예상 데이터
//        Long id = 4L;
//        String title = "라라라라";
//        String content = "4444";
//        ArticleForm dto = new ArticleForm(id, title, content);
//        Article expected = null;
//        //2. 실제 데이터
//        Article article = ariticleSerivce.create(dto);
//
//        //3. 비교 및 검증
//        assertEquals(expected, article);
//
//    }
}