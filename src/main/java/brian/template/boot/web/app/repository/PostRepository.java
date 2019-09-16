package brian.template.boot.web.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import brian.template.boot.web.app.domain.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{
	
	public Post findByPostId(Integer postId);
	
}
