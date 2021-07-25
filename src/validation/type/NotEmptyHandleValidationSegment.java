package validation.type;

import java.util.Collection;
import java.util.Map;

import validation.constraint.HandleValidationSegment;
import validation.utils.CommonUtils;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : Jul 9, 2021 8:36:24 AM
 * </ul>
 *
 * @author dev
 */
public class NotEmptyHandleValidationSegment extends HandleValidationSegment<Object> {

	public static final String KEY = "notempty";

	/**
	 * @param constraintData
	 */
	public NotEmptyHandleValidationSegment(String constraintData) {
		super(constraintData);
	}

	/**
	 *
	 * @see validation.custom.ValidateSegment#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Object input) throws Exception {
		if (input == null) {
			return false;
		}
		if (input instanceof String) {
			return !((String) input).isEmpty();
		}
		if (input instanceof Collection<?>) {
			return !((Collection<?>) input).isEmpty();
		}
		if (input instanceof Map<?, ?>) {
			return !((Map<?, ?>) input).isEmpty();
		}
		
		if( CommonUtils.isNotNull(input.getClass().getComponentType())) {
			return ((Object[]) input).length > 0;
		}

		return false;
	}

	/**
	 * @see validation.constraint.HandleValidationSegment#isValidConstraintType()
	 */
	@Override
	public boolean isValidConstraintType() {
		return true;
	}

	/**
	 * @see validation.constraint.HandleValidationSegment#getValidateType()
	 */
	@Override
	public String getValidateType() {
		return KEY;
	}
}