package brian.boot.template.web.app.service;

import brian.boot.template.web.app.domain.Post;
import brian.boot.template.web.app.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    PostService service;

    @Mock
    PostRepository postRepository;

    List<Post> expectedList = new ArrayList<>();

    @Before
    public void setUp(){
        service = new PostService();

        Post p1 = new Post();
        expectedList.add(p1);
    }

    @Test
    public void testGetAllPost_shouldReturnList(){

        // Given
        List<Post> list = new ArrayList<>();
        Post p1 = new Post();
        list.add(p1);

        // When
        when(postRepository.findAll()).thenReturn(expectedList);

        // Test
        List<Post> expected = service.getAllPost();

        assertThat(list).isEqualTo(expected);
    }

}
