package com.xuetao.java.demo.controller;
import com.xuetao.java.demo.dto.AccessTokenDTO;
import com.xuetao.java.demo.dto.GithubUser;
import com.xuetao.java.demo.mapper.UserMapper;
import com.xuetao.java.demo.model.User;
import com.xuetao.java.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Value("${github.token}")
    private String token;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name="state") String state,
                             HttpServletResponse response
                            ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        if(githubUser!=null){
            User user = new User();
            String tokenUUID = UUID.randomUUID().toString();
            user.setToken(tokenUUID);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",tokenUUID));
            //登录成功，写cookie和session

            //HttpSession session = request.getSession();
            //session.setAttribute("user",githubUser);
            return "redirect:/";//将页面重定向到index

        }else{
            //登录失败
            return "redirect:/";
        }
        //return "index";
    }
}
