package brian.boot.template.web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brian.boot.template.web.app.domain.TestUser;
import brian.boot.template.web.app.repository.TestUserRepository;

@Service
public class TestUserService {

    @Autowired
    private TestUserRepository testUserRepository;

    public TestUser getTestUser(String userId) {
    	
    	return testUserRepository.findOne(userId);
    }
}
