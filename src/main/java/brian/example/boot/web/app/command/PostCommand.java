package brian.example.boot.web.app.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostCommand {

    private Integer postId;

    @NotEmpty (message = "{post.command.subject.not.null}")
    @Size ( min=1, max=50, message = "{post.command.subject.max.message}")
    private String subject;

    @NotNull
    @Size( min=3, max=200, message = "{post.command.content.max.message}")
    private String content;
    private String userId;

}
