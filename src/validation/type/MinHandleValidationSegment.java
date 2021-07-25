package validation.type;

import java.util.Collection;
import java.util.Map;

import validation.constraint.HandleValidationSegment;
import validation.utils.CommonUtils;

public class MinHandleValidationSegment extends HandleValidationSegment<Object> {
	
	public static final String KEY = "min";

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
        
        if( CommonUtils.isNotNull(input.getClass().getComponentType())) {
			return constraintValue  <=((Object[]) input).length;
		}
        
        return false;
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