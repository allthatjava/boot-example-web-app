package brian.boot.template.web.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import brian.boot.template.web.app.domain.TestUser;

@Repository
public interface TestUserRepository extends CrudRepository<TestUser, String>{
	
}
