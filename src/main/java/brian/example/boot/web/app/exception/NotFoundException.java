package brian.example.boot.web.app.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1323040521845611469L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, String... args) {
		super(String.format(message, args));
	}

	public NotFoundException(String message, Exception ex) {
		super(message, ex);
	}

	public NotFoundException(String message, Exception ex, String... args) {
		super(String.format(message, args), ex);
	}
}
