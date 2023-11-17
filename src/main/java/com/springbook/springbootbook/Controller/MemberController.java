package com.springbook.springbootbook.Controller;


import com.springbook.springbootbook.Repository.MemberRepository;
import com.springbook.springbootbook.dto.MemberForm;
import com.springbook.springbootbook.enetity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String newForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String IdCreated(MemberForm form){
        System.out.println(form.toString());


        //1. DTO로 변환
        Member member = form.toEntity();
        System.out.println(form.toEntity()); //DTO가 엔티티로 잘 변환되는지 확인 출력
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";


    }

}
