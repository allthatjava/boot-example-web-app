package brian.template.boot.web.app.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brian.template.boot.web.app.domain.Post;
import brian.template.boot.web.app.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> getAllPost() {
		return StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Post getPost(int postId) {

		return repo.findByPostId(postId);
	}

	public Post addPost(Post post) {
		return repo.save(post);
	}

	public void deletePost(Integer postId) {
		repo.delete(postId);
	}

	public Post updatePost(Post post) {
		return repo.save(post);
	}
}
