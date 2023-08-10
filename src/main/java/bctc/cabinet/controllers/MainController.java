package bctc.cabinet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bctc")
public class MainController {
    @GetMapping()
    public String main(){
        return "/bctc/main";
    }
}
