package brian.template.boot.web.app.controller.exception;

public class NoUserFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6604542579197801636L;

	public NoUserFoundException() {
		super();
	}
	
	public NoUserFoundException(String message) {
		super(message);
	}
	
	public NoUserFoundException(String message, Exception ex) {
		super(message, ex);
	}
}
