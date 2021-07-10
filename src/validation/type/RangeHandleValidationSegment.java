package validation.type;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.constraint.HandleValidationSegment;
import validation.exception.InvalidInputException;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오후 4:34:09
 * </ul>
 *
 * @author dev
 */
public class RangeHandleValidationSegment extends HandleValidationSegment<Object> {

	public static final String KEY = "range";

	/**
	 * @param constraintData
	 */
	public RangeHandleValidationSegment(String constraintData) {
		super(constraintData);
	}

	/**
	 * @see validation.custom.ValidateSegment#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Object input) throws Exception {
		if (input == null) {
			return false;
		}
		Pattern r = Pattern.compile(rangePattern());
		Matcher m = r.matcher(constraintData);

		Integer left = 0, right = 0;
		Integer inputLength = 0;
		if (m.find()) {
			left = Integer.parseInt(m.group(1));
			right = Integer.parseInt(m.group(2));
		}

		if (input instanceof String) {
			inputLength = ((String) input).length();
		}

		if (input instanceof Collection<?>) {
			inputLength = ((Collection<?>) input).size();
		}

		if (input instanceof Map<?, ?>) {
			inputLength = ((Map<?, ?>) input).size();
		}

		return inputLength >= left && inputLength < right;
	}

	/**
	 * 
	 * @see validation.constraint.HandleValidationSegment#isValidConstraintType()
	 */
	@Override
	public boolean isValidConstraintType() throws InvalidInputException {
		Pattern r = Pattern.compile(rangePattern());
		Matcher m = r.matcher(constraintData);
		if (m.find()) {
			try {
				Integer left = Integer.valueOf(m.group(1));
				Integer right = Integer.valueOf(m.group(2));
				if (left >= right) {
					throw new InvalidInputException(
							"Error number constraints [left, right]" + ": [" + left + ", " + right + "]");
				}
			} catch (NumberFormatException e) {
				throw new InvalidInputException("Error number constraints " + e.getMessage());
			}

		}

		return false;
	}

	/**
	 * @see validation.constraint.HandleValidationSegment#getValidateType()
	 */
	@Override
	public String getValidateType() {
		return KEY;
	}

	private String rangePattern() {
		return "^(\\d+),\\s*(\\d+)$";
	}

	/**
	 * @see validation.custom.ValidateSegment#getInfor(java.lang.Object)
	 */
	@Override
	public String[] getInfor(Object input) {
		return null;
	}
}