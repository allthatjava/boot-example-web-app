package brian.example.boot.web.app.exception;

import org.springframework.http.HttpStatus;

// @ResponseStatus(HttpStatus.NOT_FOUND)  // Instead of using ControllerAdvice, you can put status code here as well.
public class NotFoundException extends RuntimeException {

	/**
	 * Throw this exception when item was not found
	 */
	private static final long serialVersionUID = 1323040521845611469L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Exception ex) {
		super(message, ex);
	}

}
