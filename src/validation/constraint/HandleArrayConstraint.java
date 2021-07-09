package validation.constraint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.custom.ArrayTypeConstraintRule;
import validation.utils.CommonUtils;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 8. 오전 10:23:12
 * </ul>
 *
 * @author dev
 * 
 * 
 */
public final class HandleArrayConstraint {

	private final String DETECT_ARRAY_LEFT = "[";

	private final String DETECT_ARRAY_RIGHT = "]";

	private final String DETECT_ELEMENT_LEFT = "{";

	private final String DETECT_ELEMENT_RIGHT = "}";

	protected String constraintData;

	/**
	 * @param constraintData
	 */
	public HandleArrayConstraint(String constraintData) {
		super();
		this.constraintData = constraintData;
	}

	private String validationPattern() {
		return "^\\s*\\" + DETECT_ARRAY_LEFT + "(.*)" + "\\" + DETECT_ARRAY_RIGHT + "(?:\\" + DETECT_ELEMENT_LEFT
				+ "(\\d*),\\s*(\\d*)" + "\\" + DETECT_ELEMENT_RIGHT + ")?\\s*$";
	}

	/**
	 * First is contraints, the once is from elemnet, second param is to element
	 * 
	 * @return
	 */
	public ArrayTypeConstraintRule getConstraintProperty() {
		ArrayTypeConstraintRule constraintProperty = new ArrayTypeConstraintRule();
		Pattern r = Pattern.compile(validationPattern());
		Matcher m = r.matcher(constraintData);

		if (m.find()) {
			constraintProperty.setConstraint(m.group(1));
			if (CommonUtils.isNotEmpty(m.group(2))) {
				constraintProperty.setFrom(Integer.valueOf(m.group(2)));
			}
			if (CommonUtils.isNotEmpty(m.group(3))) {
				constraintProperty.setTo(Integer.valueOf(m.group(3)));
			}
		}

		return constraintProperty;
	}
}