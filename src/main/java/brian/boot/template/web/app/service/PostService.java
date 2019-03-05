package brian.boot.template.web.app.service;

import brian.boot.template.web.app.domain.Post;
import brian.boot.template.web.app.repository.PostRepository;
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
    
    public Post getPost(int id) {
    	return postRepository.findOne(id);
    }
}
