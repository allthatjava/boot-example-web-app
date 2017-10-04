package brian.boot.template.web.app.controller;

import brian.boot.template.web.app.domain.Post;
import brian.boot.template.web.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(name = "/posts", method = RequestMethod.GET)
    public List<Post> getPosts()
    {
        return (List<Post>)postRepository.findAll();
    }
}
