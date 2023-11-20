package com.springbook.springbootbook.Service;

import com.springbook.springbootbook.Repository.AritcleRepository;
import com.springbook.springbootbook.dto.ArticleForm;
import com.springbook.springbootbook.enetity.Article;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AriticleSerivce {

    @Autowired
    private AritcleRepository aritcleRepository; // 게시글 리파지터리 객체 주입

    public List<Article> index() {
        return aritcleRepository.findAll();
    }

    public Article show(Long id) {
        return aritcleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return aritcleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id,article.toString());
        //2. 타킷 조회
        Article target = aritcleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            return null; //응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        //4. 업데이트하기
        target.patch(article);
        Article updated = aritcleRepository.save(target);
        return updated;


    }

    public Article delete(Long id) {

        //1. 대상찾기
        Article target = aritcleRepository.findById(id).orElse(null);
        //2. 잘못된 요청처리 하기
        if(target == null){
            return null;
        }
        //3. 대상 삭제하기
        aritcleRepository.delete(target);
        return target;

    }

    @Transactional
    //1 보통 트랜잭션은 서비스에서 관리합니다.
    // 서비스의 메서드에 @Transactional을 붙이면 해당 메서드는 하나의 트랜잭션으로 묶입니다. 정말 그런지 확인해 보겠습니다. createArticles() 메서드 위에 @Transactional을 붙입니다.
    // 그러면 메서드가 중간에 실패하더라도 롤백을 통해 이전 상태로 돌아갈 수 있습니다
    public List<Article> createdArticles(List<ArticleForm> dtos) {

        //1. dto 묶음을 엔티티 묶음으로 변환하기
        //스트림(stream) 문법은 리스트와 같은 자료구조에 저장된 요소를 하나씩 순회하면서 처리하는 코드 패턴
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
//        List<Article> articleList = new ArrayList<>();
//        for (int i =0; i < dtos.size(); i++) {
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }
        //2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> aritcleRepository.save(article));
//        for (int i = 0; i < articleList.size(); i++) {
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }

        //3. 강제 예외 발생시키기
        aritcleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패"));

        //4. 결과 값 변환하기
        return articleList;
    }
}
