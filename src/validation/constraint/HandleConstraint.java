package validation.constraint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오후 3:56:16
 * </ul>
 *
 * @author dev
 */
public final class HandleConstraint {

	protected final String SPLIT_KEY = ":";

	protected final String CONTRAINT_DATA = "\\s*\\{(.+)?\\}\\s*$";

	protected String constraintData;

	/**
	 * @param constrant
	 */
	public HandleConstraint(String constraintData) {
		super();
		this.constraintData = constraintData;
	}

	public final String getConstraintName() {
		assert (constraintData.contains(SPLIT_KEY));

		return constraintData.split(SPLIT_KEY)[0].toLowerCase();
	}

	public final String getConstraintValue() {
		int expectedLeft = constraintData.indexOf("{");
		int expectRight = constraintData.indexOf("}");
		assert (expectedLeft >= 0 && expectRight > 0 && expectedLeft < expectRight);

		Pattern r = Pattern.compile(CONTRAINT_DATA);
		Matcher m = r.matcher(constraintData);

		if (m.find()) {
			return m.group(1);
		}
		return "";
	}
}