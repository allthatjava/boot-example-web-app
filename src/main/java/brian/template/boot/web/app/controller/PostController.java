package brian.template.boot.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import brian.template.boot.web.app.controller.exception.NoUserFoundException;
import brian.template.boot.web.app.controller.exception.NotFoundException;
import brian.template.boot.web.app.domain.Post;
import brian.template.boot.web.app.domain.PostRequest;
import brian.template.boot.web.app.domain.PostResponse;
import brian.template.boot.web.app.domain.TestUser;
import brian.template.boot.web.app.service.PostService;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService)
    {
        this.postService = postService;
    }

    /**
     * Returns all posts 
     * 
     * @return
     */
    @GetMapping(path="/posts")
    public List<Post> getPosts()
    {
        return postService.getAllPost();
    }
    
    /**
     * Return the post with given id
     * 
     * @param postId
     * @return
     */
    @GetMapping(path="/post/{post_id}")
    public Post getPost(@PathVariable("post_id") Integer postId )
    {
    	Post p = postService.getPost(postId);
    	
    	if( p != null)
    		return p;
    	else 
    		throw new NotFoundException("Post not found with post id : "+postId);
    }
    
    /**
     *  Insert new Post
     * @param post
     * @return
     */
    @PostMapping(path="/post")
    public PostResponse addPost(PostRequest postRequest) {
    	PostResponse res = new PostResponse();

    	if( null == postRequest 
    			|| null == postRequest.getSubject()
    			|| null == postRequest.getContent() )
    		throw new IllegalArgumentException("Subject or Content cannot be null");
    	
    	String userId = postRequest.getUserId();
    	
    	Post post = new Post();
    	post.setSubject(postRequest.getSubject());
    	post.setContent(postRequest.getContent());
    	if( null == userId )
    		throw new NoUserFoundException("User ID ["+userId+"] was not found");
    	post.setTestUser(new TestUser(userId));
    	
    	post = postService.addPost(post);

    	if( null == post.getPostId() )
    		throw new RuntimeException();	// TODO : Add proper exception
    	else
    		res.setStatus("Inserted");
    		
    	return res;
    }
    
    
    // TODO : Update existing Post
    
    
    
}
