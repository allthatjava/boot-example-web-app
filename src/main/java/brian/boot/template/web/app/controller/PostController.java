package brian.boot.template.web.app.controller;

import brian.boot.template.web.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(name = "/posts", method = RequestMethod.GET)
    public String getPosts()
    {
        return postRepository.findAll().toString();
    }
}
