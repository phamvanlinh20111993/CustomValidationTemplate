package validation.exception;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : Jul 9, 2021 9:04:48 AM
 * </ul>
 *
 * @author dev
 */
public class InvalidConstraintException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidConstraintException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public InvalidConstraintException(String errorMessage) {
		super(errorMessage);
	}
}
