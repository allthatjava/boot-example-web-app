package brian.example.boot.web.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import brian.example.boot.web.app.domain.AppPost;

@Repository
public interface AppPostRepository extends CrudRepository<AppPost, Integer>{
	
	public AppPost findByPostId(Integer postId);
	
}
