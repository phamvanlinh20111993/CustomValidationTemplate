package validation.type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import validation.constraint.HandleValidationSegment;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오후 4:20:27
 * </ul>
 *
 * @author dev
 */
public class PatternHandleValidationSegment extends HandleValidationSegment<String> {

	public static final String KEY = "pattern";

	/**
	 * @param constraintData
	 */
	public PatternHandleValidationSegment(String constraintData) {
		super(constraintData);
	}

	/**
	 * @see validation.custom.ValidateSegment#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(String input) throws Exception {
		if (input == null) {
			return false;
		}
		Pattern r = Pattern.compile(constraintData);
		Matcher m = r.matcher(input);
		return m.find();
	}

	/**
	 * @see validation.constraint.HandleValidationSegment#isValidConstraintType()
	 */
	@Override
	public boolean isValidConstraintType() {
		try {
			Pattern.compile(constraintData);
		} catch (PatternSyntaxException exception) {
			return false;
		}
		return true;
	}

	/**
	 * @see validation.constraint.HandleValidationSegment#getValidateType()
	 */
	@Override
	public String getValidateType() {
		return KEY;
	}

	/**
	 * @see validation.custom.ValidateSegment#getInfor(java.lang.Object)
	 */
	@Override
	public String[] getInfor(String input) {
		return null;
	}

}