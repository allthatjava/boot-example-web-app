package brian.example.boot.web.app.service;

import brian.example.boot.web.app.domain.AppPost;
import brian.example.boot.web.app.repository.AppPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppPostService {

	private AppPostRepository repo;

	@Autowired
	public AppPostService(AppPostRepository repo){
		this.repo = repo;
	}

	public List<AppPost> getAllPost() {
		return StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public AppPost getPost(int postId) {

		return repo.findByPostId(postId);
	}

	public AppPost addPost(AppPost post) {
		return repo.save(post);
	}

	public void deletePost(Integer postId) {
		repo.deleteById(postId);
	}

	public AppPost updatePost(AppPost post) {
		return repo.save(post);
	}

}
