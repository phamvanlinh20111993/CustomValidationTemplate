package validation.type;

import java.util.Collection;
import java.util.Map;

import validation.constraint.HandleValidationSegment;

public class MinHandleValidationSegment extends HandleValidationSegment<Object> {
	
	public static final String KEY = "max";

	public MinHandleValidationSegment(String constraintData) {
		super(constraintData);
	}

	@Override
	public boolean isValid(Object input) throws Exception {
		if(input == null) {
			return false;
		}
		
        Long constraintValue = Long.valueOf(constraintData);
        if (input instanceof Number) {
            Number num = (Number) input;
            return constraintValue <= num.doubleValue();
        }
        
        if (input instanceof String) {
            String inputStr = input.toString();
            return constraintValue <= inputStr.length();
        }
        
        if (input instanceof Collection<?>) {
            return constraintValue <= ((Collection<?>) input).size();
        }
        
        if (input instanceof Map<?, ?>) {
            return constraintValue <= ((Map<?, ?>) input).size();
        }
        
        return false;
	}

	@Override
	public String[] getInfor(Object input) {
		return null;
	}

	@Override
	public boolean isValidConstraintType() {
		return constraintData.matches("^-?\\d+$");
	}

	@Override
	public String getValidateType() {
		return KEY;
	}
	
}