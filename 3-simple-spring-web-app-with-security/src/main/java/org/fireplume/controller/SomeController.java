package org.fireplume.controller;

import org.fireplume.services.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SomeController {

    @Autowired
    private SomeService someService;

    @GetMapping("/")
    public String getPage(Model model, @RequestParam(value = "data", required = false) String data) {
        model.addAttribute("data", data);
        return "index";
    }

    @GetMapping("/log-in")
    public String getLogInPage(Model model) {
        return "log-in";
    }

    @GetMapping("/convert")
    @ResponseBody
    public String getString(@RequestParam(name = "str") String str) {
        return someService.getStringWithoutSpaces(str);
    }

    @GetMapping("/secured_method")
    @ResponseBody
    public String getSecuredService() {
        return someService.securedWithPermissionEvaluator("not null str");
    }

    @GetMapping("/secured")
    @ResponseBody
    @Secured({"ROLE_USER"})
    public String getSecuredPage() {
        return "*** SECURED ***";
    }
}
