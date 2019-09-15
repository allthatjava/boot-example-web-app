package brian.template.boot.web.app.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest 
{
	private String subject;
	private String content;
	private String userId;
}
