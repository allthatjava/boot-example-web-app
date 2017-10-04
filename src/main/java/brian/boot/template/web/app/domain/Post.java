package brian.boot.template.web.app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Integer postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private TestUser testUser;

    private String subject;
    private String content;

    @Column(name="created_datetime")
    private Timestamp createdDatetime;

    private Post(){}

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public TestUser getTestUser() {
        return testUser;
    }

    public void setTestUser(TestUser testUser) {
        this.testUser = testUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

}
