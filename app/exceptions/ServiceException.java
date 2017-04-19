package exceptions;

/**
 * The type Service exception.
 */
public class ServiceException extends Exception {
	/**
	 * The Error.
	 */
	private String error = "Bad Request";
	/**
	 * The Message.
	 */
	private String message;

	/**
	 * Instantiates a new Service exception.
	 *
	 * @param error   the error
	 * @param message the message
	 */
	public ServiceException(String error, String message) {
		this.error = error;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Gets error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}
}
