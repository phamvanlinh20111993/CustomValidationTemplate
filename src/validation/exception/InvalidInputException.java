package validation.exception;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : Jul 9, 2021 9:06:21 AM
 * </ul>
 *
 * @author dev
 */
public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}

}
