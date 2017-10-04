package brian.boot.template.web.app.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="POST")
public class Post {

    @Id
    private int postId;
    @ManyToOne
    private TestUser userId;
    private String subject;
    private String content;
    private Timestamp createdDatetime;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public TestUser getUserId() {
        return userId;
    }

    public void setUserId(TestUser userId) {
        this.userId = userId;
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
