package com.springbook.springbootbook.Controller;

import com.springbook.springbootbook.Repository.AritcleRepository;
import com.springbook.springbootbook.dto.ArticleForm;
import com.springbook.springbootbook.enetity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    @Autowired //스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private AritcleRepository aritcleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());


        //1. DTO를 엔티티로 변환
        Article article = form.toEntity(); //DTO가 엔티티로 잘 변환되는지 확인 출력
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = aritcleRepository.save(article); //article이 DB에 잘 저장되는지 확인 출력
        log.info(saved.toString());
        return "redirect:/articles";
    }

    //글읽기
    @GetMapping("/articles/{id}")
    public String view(@PathVariable Long id, Model model){
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그 찍기
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = aritcleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        //model.addAttribute(String name, Object value);
        model.addAttribute("article", articleEntity);
        //1. 조회한 데이터 가져오기
        //Optional<Article> article = aritcleRepository.findById(id);

        log.info("id = " + id);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = aritcleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }


    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = aritcleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);


        return "/articles/edit";
    }

    //수정
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity(); //DTO(from)를 엔티티로 변환
        log.info(articleEntity.toString());
        //2. 엔티티를 DB에 저장하기
        //2-1 DB에서 기존 데이터 가져오기
        Article target = aritcleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2 기존 데이터 값을 갱신하기
        if(target != null){
            aritcleRepository.save(articleEntity); //엔티티를 DB에 저장(갱신)
        }

        //3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    //삭제 기능
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다.");
        //1. 삭제할 대상 가져오기
        Article target = aritcleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target != null){
            aritcleRepository.delete(target);
            rttr.addAttribute("msg", "삭제되었습니다.");
        }

        //3. 결과 페이지 리다이렉트하기

        return "redirect:/articles";
    }
}
