package brian.example.boot.web.app.controller;

import brian.example.boot.web.app.command.LoginCommand;
import brian.example.boot.web.app.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Slf4j
@Controller
public class LoginController {

    HttpSession session;
    AppUserService service;

    @Autowired
    public LoginController(HttpSession session, AppUserService service) {
        this.session = session;
        this.service = service;
    }

    @GetMapping({ "/", "/login" })
    public String initIndex(Model model){
        LoginCommand form = new LoginCommand();
        model.addAttribute("loginCommand", form);

        log.info(">>>>>>>>>>>>>>>>>>>>>>> Login Screen >>>>>>>>>>>>>>>>>>>>>>>>>>>");

        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/login-success")
    public String processLogin(Model model, Principal principal){

        log.info(principal.getName()+"<<<<<<<<<<<<<<<<<<<<<<<<<<< Logged in properly <<<<<<<<<<<<<");

        session.setAttribute("loggedInUser", service.getAppUser(principal.getName()));

        return "redirect:/posts";
    }

//    @GetMapping("/logout")
//    public String logout(Model model, Principal principal){
//
//        log.info(principal.getName()+"################ LOGOUT #######################");
//
//        return "redirect:/";
//    }

}
