package com.mytaxi.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController
{

    @RequestMapping("/")
    public String home()
    {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        return "redirect:swagger-ui.html";
    }

}
