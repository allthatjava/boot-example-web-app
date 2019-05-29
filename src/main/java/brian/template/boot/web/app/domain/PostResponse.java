package brian.boot.template.web.app.domain;

public class PostResponse 
{
	private String status = "Failed";	// Default is 'Failed'
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
