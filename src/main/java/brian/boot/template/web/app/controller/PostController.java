package brian.boot.template.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import brian.boot.template.web.app.domain.Post;
import brian.boot.template.web.app.service.PostService;

@RestController
public class PostController {

    private PostService postService;

    public PostController(@Autowired PostService postService)
    {
        this.postService = postService;
    }

    @GetMapping(path="/posts")
    public List<Post> getPosts()
    {
        return postService.getAllPost();
    } 
    
    @GetMapping(path="/post/{id}")
    public Post getPost(@PathVariable("id") Integer id )
    {
    	return postService.getPost(id);
    }
}
