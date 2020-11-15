package com.xuetao.java.demo.controller;

import com.xuetao.java.demo.mapper.QuestionMapper;
import com.xuetao.java.demo.mapper.UserMapper;
import com.xuetao.java.demo.model.Question;
import com.xuetao.java.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: TestRookie * @Date: 2020/11/7 8:05 * @Description: *
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null || title==""){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if(description==null || description==""){
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }
        if(tag==null || tag==""){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }

        Question question = new Question();
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";
    }
}
