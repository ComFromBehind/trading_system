package com.example.demo.user;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.ProductService;
import com.example.demo.tradelog.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final ProductService productService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TradeLogRepository tradeLogRepository;
    private final TradeLog2Repository tradeLog2Repository;
    private final TradeLog3Repository tradeLog3Repository;
    @GetMapping("/signup/list")
    public String list(Model model){
        List<SiteUser> siteUserList = this.userRepository.findAll();
        model.addAttribute("siteUserList",siteUserList);
        return "siteuser_list";
    }
    @GetMapping("/login")
    public String login(){

        return "login_form";
    }
    @ResponseBody
    @PostMapping("/kick")
    public String hell(@RequestBody Map<String, List<Long>> ids){

        List<Long> idss = ids.get("ids");
        for(Long id : idss){
            Optional<TradeLog3> tr = tradeLog3Repository.findById(id);
            Optional<SiteUser> user = userRepository.findByLoginID(tr.get().getUsername());
            if(tr.get().getBuy()==1)
                user.get().setBudget(user.get().getBudget()+tr.get().getAllsum());


            tradeLog3Repository.deleteById(id);
        }
        return "success";

    }



    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm)
    {
        return "signup_form";
    }

    @PostMapping("/signup")

    public String signup(@Valid UserCreateForm userCreateForm, BindingResult
                         bindingResult){
        if(bindingResult.hasErrors()){

            log.info("failure");
            return "signup_form";
        }
        try {
            userService.create(userCreateForm.getLoginID(),
                    100000, userCreateForm.getPassword());

        }
        catch(DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }
        catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed",e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }
    @ResponseBody
    @GetMapping(value="/update")
    public List<TradeLog3> trlist(){
        List<TradeLog3> trlist = productService.findByName23(UserInfo.getUserInfo());
        return trlist;
    }


    @GetMapping(value="/mypage")
    public String mypage(Model model){
        model.addAttribute("userInfo", UserInfo.getUserInfo());
        Optional<SiteUser> user = this.userRepository.findByLoginID(UserInfo.getUserInfo());
        model.addAttribute("budget",user.get().getBudget());
        List<TradeLog> tradeLogList = productService.itemListfindByLoginId(user.get().getLoginID());
        List<TradeLog3> tradeLog3List = tradeLog3Repository.findAll();
        model.addAttribute("tradeLogList", tradeLogList);
        model.addAttribute("tradeLog3List",tradeLog3List);
        return "mypage";

    }


}
