package org.fireplume.controller;

import org.fireplume.services.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SomeController {

    @Autowired
    private SomeService someService;

    @GetMapping("/")
    public String getPage(Model model) {
        return "index";
    }

    @GetMapping("/log-in")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/{data}")
    public String getPageWithData(Model model, @PathVariable("data") String data) {
        model.addAttribute("data", data);
        return "index";
    }

    @GetMapping("/secured")
    @Secured({"ROLE_USER"})
    public String getSecuredPage(Model model) {
        model.addAttribute("data", "***Secured***");
        return "index";
    }

    @GetMapping("/convert")
    @ResponseBody
    public String getString(@RequestParam(name = "str") String str) {
        return someService.getStringWithoutSpaces(str);
    }
}
