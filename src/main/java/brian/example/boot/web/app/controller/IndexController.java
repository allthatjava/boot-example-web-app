package brian.example.boot.web.app.controller;

import brian.example.boot.web.app.command.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/", "index", "index.html"})
    public String initIndex(Model model){
        LoginCommand form = new LoginCommand();
        model.addAttribute(form);

        return "index";
    }

    // @FIXME create a loging process and redirect to Post Index screen
}
