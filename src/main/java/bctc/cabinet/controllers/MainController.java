package bctc.cabinet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bcpc")
public class MainController {
    @GetMapping()
    public String main(){
        return "/bcpc/main";
    }
}
