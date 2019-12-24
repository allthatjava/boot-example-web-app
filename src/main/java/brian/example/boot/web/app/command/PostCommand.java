package brian.example.boot.web.app.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostCommand {

    private Integer postId;

    @NotNull
    @Size( min=1, max=50, message = "{post.command.subject.max.message}")
    private String subject;

    @NotNull
    @Size( min=3, max=200, message = "{post.command.content.max.message}")
    private String content;
    private String userId;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
