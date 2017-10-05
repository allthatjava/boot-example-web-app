package brian.boot.template.web.app.repository;

import brian.boot.template.web.app.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{

}
