package validation.type;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validation.constraint.HandleValidationSegment;
import validation.exception.InvalidInputException;
import validation.utils.CommonUtils;

public class SizeHandleValidationSegment extends HandleValidationSegment<Object> {

	public static final String KEY = "size";

	public SizeHandleValidationSegment(String constraintData) {
		super(constraintData);
	}

	@Override
	public boolean isValid(Object input) throws Exception {
		if (input == null) {
			return false;
		}
		Pattern r = Pattern.compile("^(\\d+)$");
		Matcher m = r.matcher(constraintData);
		
		Integer size = 0;
		if (m.find()) {
			size = Integer.parseInt(m.group(1));
		}
		
		Integer inputLength = 0;
		if (input instanceof String) {
			inputLength = ((String) input).length();
		}

		if (input instanceof Collection<?>) {
			inputLength = ((Collection<?>) input).size();
		}

		if (input instanceof Map<?, ?>) {
			inputLength = ((Map<?, ?>) input).size();
		}
		
		if( CommonUtils.isNotNull(input.getClass().getComponentType())) {
			inputLength = ((Object[]) input).length;
		}

		return size.equals(inputLength);
	}

	@Override
	public boolean isValidConstraintType() {
		Pattern r = Pattern.compile("^(\\d+)$");
		Matcher m = r.matcher(constraintData);
		if (m.find()) {
			try {
				return true;
			} catch (NumberFormatException e) {
				throw new InvalidInputException("Error number constraints " + e.getMessage());
			}
		}

		return false;
	}

	@Override
	public String getValidateType() {
		return KEY;
	}

}
