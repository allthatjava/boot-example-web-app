package brian.example.boot.web.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "app_post")
public class AppPost implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer postId;
	private String subject;
	private String content;
	@Column(name = "user_id", insertable = false, updatable = false)
	private String userId;

	@Column(name = "created_datetime")
	private Timestamp createdDatetime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private AppUser appUser;

	public AppPost() {
		this.createdDatetime = new Timestamp(new Date().getTime());
	}
}
