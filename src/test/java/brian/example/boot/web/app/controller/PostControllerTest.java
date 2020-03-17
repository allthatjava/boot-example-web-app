package brian.example.boot.web.app.controller;

import brian.example.boot.web.app.command.PostCommand;
import brian.example.boot.web.app.domain.AppPost;
import brian.example.boot.web.app.mapper.AppToPostMapper;
import brian.example.boot.web.app.service.AppPostService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringJUnit4ClassRunner.class)
public class PostControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AppPostService postService;
    @Mock
    private AppToPostMapper mapper;
    @InjectMocks
    private AppPostController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetPost_withWrongFormatParam_expectBadRequest() throws Exception {
        mockMvc.perform(get("/post/adf"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/400"))
        ;
    }

    @Test
    public void testGetPostsReturnValue() throws Exception {

        AppPost p1 = new AppPost();
        p1.setPostId(1);
        p1.setSubject("Subject");
        p1.setContent("Content");
        List<AppPost> posts = new ArrayList<>();
        posts.add(p1);

        // Given
        when(postService.getAllPost()).thenReturn(posts);

        // When
        MvcResult mvcResult = mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("posts", posts))
                .andExpect(view().name("post/posts"))
                .andReturn()
        ;

        // Then
        verify(postService, times(1)).getAllPost();
        Assert.assertEquals( 1,
                ((List)mvcResult.getModelAndView().getModel().get("posts")).size());
    }

    // @GetMapping("/post/{post_id}")
    @Test
    public void testGetPost_withValidParam_ReturnValue() throws Exception {

        AppPost p1 = new AppPost();
        p1.setPostId(1);
        p1.setSubject("Subject");
        p1.setContent("Content");

        PostCommand pc1 = new PostCommand();
        pc1.setPostId(1);
        pc1.setSubject("Subject");
        pc1.setContent("Content");

        // Given
        when(postService.getPost(1)).thenReturn(p1);
        when(mapper.toPostCommand(any())).thenReturn(pc1);

        // When
        MvcResult mvcResult = mockMvc.perform(get("/post/1"))
                .andExpect(status().isOk())
//                .andExpect(model().attribute("postCommand", postCommand))
                .andExpect(view().name("post/post"))
                .andReturn()
                ;

        // Then
        verify(postService, times(1)).getPost(1);
        Assert.assertEquals( p1.getPostId(),
                ((PostCommand)mvcResult.getModelAndView().getModel().get("postCommand")).getPostId());
    }

    // @GetMapping("/post")


    // @PostMapping("/post")
    // @PutMapping("/post/{post_id}")
    // @DeleteMapping("/post/{post_id}")
}
