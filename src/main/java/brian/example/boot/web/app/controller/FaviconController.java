package brian.example.boot.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Not using Favicon, so this will ignore the Favicon not found errors
 */
@Controller
public class FaviconController {
    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
