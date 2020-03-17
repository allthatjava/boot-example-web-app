package brian.example.boot.web.app.controller;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import brian.example.boot.web.app.domain.AppPost;
import brian.example.boot.web.app.service.AppPostService;

//@IfProfileValue(name = "spring.profiles.active", values = { "default", "dev" })
@RunWith(SpringRunner.class)
@WebMvcTest(AppPostController.class)
public class PostControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private AppPostService service;
	
    @Test
    public void testController_withPathVariable_shouldReturnOnePost() {
    	
    	// Given
    	AppPost post = new AppPost();
    	post.setPostId(1);
    	post.setSubject("Test Subject 1");
    	post.setContent("Contents of Test subject 1");
    	post.setUserId("tester1");
    	post.setCreatedDatetime( new Timestamp(1569122872739L) );
    	
    	when(service.getPost(1)).thenReturn(post);

    	// Test & Assert
    	this.mockMvc.perform( MockMvcRequestBuilders.get("/post/1") )
    			.andDo(MockMvcResultHandlers.print())
    			.andExpect( MockMvcResultMatchers.status().isOk())
    			.andExpect( MockMvcResultMatchers.content().json("{\n" + 
    					"    \"postId\": 1,\n" + 
    					"    \"subject\": \"Test Subject 1\",\n" + 
    					"    \"content\": \"Contents of Test subject 1\",\n" + 
    					"    \"userId\": \"tester1\",\n" + 
    					"    \"createdDatetime\": 1569122872739\n" + 
    					"}") );
    }
}
