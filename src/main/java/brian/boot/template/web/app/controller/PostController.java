package brian.boot.template.web.app.controller;

import brian.boot.template.web.app.domain.Post;
import brian.boot.template.web.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    public PostController(@Autowired PostService postService)
    {
        this.postService = postService;
    }

    @RequestMapping(name = "/posts", method = RequestMethod.GET)
    public List<Post> getPosts()
    {
        return postService.getAllPost();
    }
}
