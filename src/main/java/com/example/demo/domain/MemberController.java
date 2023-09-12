package com.example.demo.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberRepository repository;



    @GetMapping("/member/list")
    public String list(Model model) {
        List<User> memberList = this.repository.findAll();
        model.addAttribute("memberList", memberList);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = ((UserDetails) principal).getUsername();
        model.addAttribute("data", username);
        UserInfo.setUserInfo(username);
        if (username == null) {
            return "login_form";
        }


        return "member_list";
    }

}
