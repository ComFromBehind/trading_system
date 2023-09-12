package com.example.demo.item;


import com.example.demo.domain.UserInfo;
import com.example.demo.service.ProductService;
import com.example.demo.tradelog.*;
import com.example.demo.user.SiteUser;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/item")

public class ItemController {
    private final ProductService productService;
    private final ItemRepository itemRepository;
    private final TradeLogRepository tradeLogRepository;
    private final ItemService itemService;

    private final TradeLog2Repository tradeLog2Repository;
    private final TradeLog3Repository tradeLog3Repository;

    private final UserRepository userRepository;

    @GetMapping("/signup/list")
    public String list(Model model){
        List<Item> itemList = this.itemRepository.findAll();
        model.addAttribute("itemList", itemList);
        System.out.println("ㅋ");
        return "item_list";
    }



    @ResponseBody
    @PostMapping("/happy/ha2ppy")
    public String cre(@RequestBody List<ItemCreateForm3> itemCreateForm3, Model model){

        Map<String, Integer> map = new HashMap<>();

        for(ItemCreateForm3 itemCreateForm31:itemCreateForm3){
            System.out.println("매수 " + itemCreateForm31.getBuysome());
            System.out.println("매도 " + itemCreateForm31.getSellsome());
            map.put(itemCreateForm31.getItemname(),itemCreateForm31.getBuysome()-itemCreateForm31.getSellsome());
        }
        model.addAttribute("mapss", map);
        System.out.println("성공!!!!!!!!!!!!!!");

        return "trade";
    }
    @ResponseBody
    @PostMapping(value="/it")
    public String haha(@RequestParam(value="newBudget") Integer budget){
        Optional<SiteUser> su =  userRepository.findByLoginID(UserInfo.getUserInfo());
        su.get().setBudget(budget);
        userRepository.save(su.get());
        System.out.print(su.get().getBudget());
        return "good";
    }

    @PostMapping(value="/signup/list")
    public String create(ItemCreateForm2 itemCreateForm2, Model model){

        Item item = new Item();
        item.setName(itemCreateForm2.getName());
        Optional<Item> t = this.itemRepository.findByName(item.getName());

        System.out.println(t.get().getName()+" 나는 해냈다~!");

        System.out.println("이름은 : "+ item.getName());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication loggedinUser = SecurityContextHolder.getContext().getAuthentication();
        NowItem.setNowItem(t.get().getName());

        System.out.println(UserInfo.getUserInfo()+"드디어 잡았다!");
        model.addAttribute("username",UserInfo.getUserInfo());
        model.addAttribute("t",t);
        model.addAttribute("nowItem",t.get().getPrice());

        //여기서 변수를 보낼 것
        int buysome=0; int sellsome=0;
        List<TradeLog3> tradeLog3List = this.tradeLog3Repository.findAll();
        for (TradeLog3 tradeitem : tradeLog3List) {
            System.out.println("hi");
            System.out.println(tradeitem.getItemname());
            System.out.println(t.get().getName());
            if(tradeitem.getItemname()==t.get().getName()){
                System.out.println(UserInfo.getUserInfo());
                System.out.println(tradeitem.getUsername());
                if(UserInfo.getUserInfo().equals(tradeitem.getUsername())&&tradeitem.getBuy()==1){
                    if(tradeitem.getChecked()!=null)
                        buysome++;
                }
                if(UserInfo.getUserInfo().equals(tradeitem.getUsername())&&tradeitem.getBuy()!=1){
                    if(tradeitem.getChecked()!=null)
                        sellsome++;
                }
            }
        }
        System.out.println("ㅊ");
        model.addAttribute("buysell", buysome-sellsome);
        model.addAttribute("user", UserInfo.getUserInfo());
        model.addAttribute("budget", userRepository.findByLoginID(UserInfo.getUserInfo()).get().getBudget());
        return "trade";
    }

    @PostMapping("/trade")
    public String trade2(TradeLog tradeLog, Model model, @RequestParam("buy") Integer buy) throws UnsupportedEncodingException {

        model.addAttribute("itemname",NowItem.getNowItem());
        model.addAttribute("buy",buy);

        tradeLog.setUsername(UserInfo.getUserInfo());
        tradeLog.setItemname(NowItem.getNowItem());

        this.tradeLogRepository.save(tradeLog);
        for(int i=0;i<tradeLog.getHowmany();i++){
            TradeLog3 tradeLog3 = new TradeLog3();
            tradeLog3.setAllsum(tradeLog.getAllsum());
            tradeLog3.setBuy(tradeLog.getBuy());
            tradeLog3.setItemname(tradeLog.getItemname());
            tradeLog3.setUsername(tradeLog.getUsername());
            this.tradeLog3Repository.save(tradeLog3);
        }
        model.addAttribute("user",UserInfo.getUserInfo());
        System.out.println("ㅈ");
        String encodedItemname = URLEncoder.encode(NowItem.getNowItem(),"UTF-8");
        return "redirect:/item/" + encodedItemname;
    }


    @GetMapping("/signup")
    public String string(ItemCreateForm itemCreateForm){
        return "itemsignup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid ItemCreateForm itemCreateForm, BindingResult
                         bindingResult){
        if(bindingResult.hasErrors()){
            return "itemsignup_form";
        }
        try{
            itemService.create(itemCreateForm.getName(),
                    itemCreateForm.getStartPrice(),itemCreateForm.getHowmany());

        }
        catch(DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "등록 실패");
            return "itemsignup_form";
        }
        catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "itemsignup_form";

        }
        return "redirect:/member/list";
    }

    @Transactional(isolation=Isolation.READ_UNCOMMITTED,noRollbackFor = Exception.class)
    @ResponseBody
    @PostMapping(value="/item/item")
    public String ss(@RequestParam(value="seller_id") Long seller, @RequestParam(value="buyer_id")
                     Long buyer,@RequestParam Integer nowPrice){


        Optional<TradeLog3> tradeLog3_seller = this.tradeLog3Repository.findById(seller);
        Optional<TradeLog3> tradeLog3_buyer = this.tradeLog3Repository.findById(buyer);
        Optional<Item> item = this.itemRepository.findByName(NowItem.getNowItem());
        Long seller_id = tradeLog3_seller.get().getId();
        Long buyer_id = tradeLog3_buyer.get().getId();
        Optional<SiteUser> su = userRepository.findByLoginID(tradeLog3_seller.get().getUsername());
        Optional<SiteUser> bu = userRepository.findByLoginID(tradeLog3_buyer.get().getUsername());

        su.get().setBudget(su.get().getBudget()+nowPrice);
        bu.get().setBudget(bu.get().getBudget()+(tradeLog3_buyer.get().getAllsum()-nowPrice));


        System.out.println(item.get().getPrice());
        item.get().setPrice(nowPrice);
        itemRepository.save(item.get());
        System.out.println("굳건");
        System.out.println("망함");
        tradeLog3_seller.get().setChecked(3);
        tradeLog3_seller.get().setTradeprice(nowPrice);
        tradeLog3_buyer.get().setChecked(3);
        tradeLog3_buyer.get().setTradeprice(nowPrice);
        tradeLog3Repository.save(tradeLog3_seller.get());
        tradeLog3Repository.save(tradeLog3_buyer.get());
        System.out.println("나는 결백하오");
        System.out.println(item.get().getPrice());
        System.out.println("ㄱ");
        return "theend";
    }

   // @ResponseBody
    //@PostMapping(value = "/hihihi/{itemName}")
    //public String f(@PathVariable(value = "itemName") String itemName,
      //              @RequestParam Map<String, Object> parameters,Model model) {
        //String json = parameters.get("paramList").toString();
//
 //       ObjectMapper mapper = new ObjectMapper();
   //     System.out.println("뭐먹을까");
     //   try {
       //     List<TradeLog2> list = mapper.readValue(json, new TypeReference<List<TradeLog2>>() {});
        //    System.out.println("하..배고프다");
         //   for(TradeLog2 item : list){
         //       if(item.getChecked()!=2)
         //       {
          //          item.setChecked(2);
         //           this.tradeLog2Repository.save(item);
          //      }

           // }

//
//            System.out.println("오늘은 여기까지?");
//            return "mypage";
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            // 처리 중 에러 발생 시 예외 처리
//            return "error";
//        }
//    }

    @ResponseBody
    @GetMapping(value="happy/happy2")
    public List<TradeLog3> getTradeLog3List2(){
        List<TradeLog3> tradeLog3List = productService.findByName3(NowItem.getNowItem());
        System.out.println("ㄴ");
        return tradeLog3List;
    }

    @ResponseBody
    @GetMapping(value="happy/happy3")
    public Item getTradeLog3List3(){
        Optional<Item> item = this.itemRepository.findByName(NowItem.getNowItem());
        System.out.println("ㄷ");
        return item.get();
    }

    @ResponseBody
    @GetMapping(value="happy/happy1")
    public List<TradeLog3> getTradeLog3List(){
        List<TradeLog3> tradeLog3List = productService.findByName2(NowItem.getNowItem());
        System.out.println("ㄹ");
        return tradeLog3List;
    }
    @ResponseBody
    @GetMapping("/happylife")
    public List<Item> getHappyLife(){
        List<Item> itemList = itemRepository.findAll();
        System.out.println("ㅁ");
        return itemList;
    }

    @GetMapping(value="/{name}")
    public String findByName2(@PathVariable(value="name") String name, Model model){
        System.out.println("일단 성공!");
        List<TradeLog3> items = productService.findByName2(name);
        Optional<Item> item = this.itemRepository.findByName(name);
        model.addAttribute("username",UserInfo.getUserInfo());
        model.addAttribute("itemname",name);
        model.addAttribute("items", items);
        List<TradeLog3> items2 = productService.findByName3(name);
        System.out.println("가격: "+item.get().getPrice());
        model.addAttribute("items2", items2);
        //추후 고쳐야할 부분
        //nowPrice 부분에 현재 문제가 잇다.
        //실시간으로 계산을 할 수있도록 고쳐야 한ㄷ.
        System.out.println("이상무");
        System.out.println("ㅂ");
        return "trade_list";
    }






}
