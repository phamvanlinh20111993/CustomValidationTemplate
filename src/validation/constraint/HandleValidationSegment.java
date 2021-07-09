package validation.constraint;

import validation.custom.ValidateSegment;
import validation.exception.InvalidConstraintException;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오전 10:58:37
 * </ul>
 *
 * @author dev
 * @param <T>
 */
public abstract class HandleValidationSegment<T> implements ValidateSegment<T> {

	protected String constraintData;

	/**
	 * @param constrant
	 */
	public HandleValidationSegment(String constraintData) {
		super();
		this.constraintData = constraintData;
	}

	/**
	 * 
	 * @return
	 */
	public abstract boolean isValidConstraintType();

	/**
	 * 
	 * @return
	 */
	public abstract String getValidateType();

	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean handle(T input) throws Exception {
		if (!isValidConstraintType()) {
			throw new InvalidConstraintException("Not valid constraint template in type { " + getValidateType() + " }, "
					+ "require " + getValidateType() + " {" + constraintData + "}");
		}
		return this.isValid(input);
	}
}
