package brian.example.boot.web.app.controller;

import brian.example.boot.web.app.service.AppPostService;
import brian.example.boot.web.app.service.AppUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    private AppPostController controller;
    private MockMvc mockMvc;

    private AppPostService postService;
    private AppUserService userService;
    private HttpSession session;

    @Autowired
    public PostControllerTest(){
//        controller = new AppPostController(postService, userService, session);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .setControllerAdvice(new ControllerExceptionHandler())
//                .build();
    }

    @Test
    public void testGetPost_withWrongFormatParam_expectBadRequest() throws Exception {
//        mockMvc.perform(get("/post/adf"))
//                .andExpect(status().isBadRequest())
//                .andExpect(view().name("error/400"))
//        ;
    }

}
