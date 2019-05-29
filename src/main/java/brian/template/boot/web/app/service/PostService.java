package brian.template.boot.web.app.service;

import brian.template.boot.web.app.domain.Post;
import brian.template.boot.web.app.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost()
    {
        return (List<Post>)postRepository.findAll();
    }
    
    public Post getPost(int postId) {
    	
    	return postRepository.findOne(postId);
    }
    
    public Post addPost(Post post) {
    	return postRepository.save(post);
    }
}
