package brian.boot.template.web.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import brian.boot.template.web.app.domain.Post;
import brian.boot.template.web.app.repository.PostRepository;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

	@Mock private PostRepository postRepository;
    @InjectMocks private PostService service;

    private List<Post> expectedList;

    @Before
    public void setUp(){
//        service = new PostService();	// @InjectMocks will initialize it.

        expectedList = new ArrayList<>();
        Post p1 = new Post();
        p1.setSubject("test");
        expectedList.add(p1);
    }

    @Test
    public void testGetAllPost_shouldReturnList(){

        // Given
        List<Post> list = new ArrayList<>();
        Post p1 = new Post();
        p1.setSubject("test");
        list.add(p1);

        // When
        when(postRepository.findAll()).thenReturn(expectedList);

        // Test
        List<Post> expected = service.getAllPost();

        assertThat(list.get(0).getSubject()).isEqualTo(expected.get(0).getSubject());
    }

}
