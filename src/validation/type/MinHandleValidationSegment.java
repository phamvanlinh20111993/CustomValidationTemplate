package validation.type;

import java.util.Collection;
import java.util.Map;

import validation.constraint.HandleValidationSegment;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : Jul 9, 2021 8:35:41 AM
 * </ul>
 *
 * @author dev
 */
public class MinHandleValidationSegment extends HandleValidationSegment<Object> {
	public static final String KEY = "min";

	/**
     * @param constraintData
     */
    public MinHandleValidationSegment(String constraintData) {​​​​​​​
        super(constraintData);
    }​
	/**
     * @see validation.custom.ValidateSegment#isValid(java.lang.Object)
     */
    @Override
    public boolean isValid(Object input) throws Exception {​​​​​​​
        if(input == null){​​​​​​​
            return false;
        }​​​​​​​
        
        Long constraintValue = Long.valueOf(constraintData);
        if (input instanceof Number) {​​​​​​​
            Number num = (Number) input;
            return constraintValue <= num.doubleValue();
        }​​​​​​​
        
        if (input instanceof String) {​​​​​​​
            String inputStr = input.toString();
            return constraintValue <= inputStr.length();
        }​​​​​​​
        
        if (input instanceof Collection<?>) {​​​​​​​
            return constraintValue <= ((Collection<?>) input).size();
        }​​​​​​​
        
        if (input instanceof Map<?, ?>) {​​​​​​​
            return constraintValue <= ((Map<?, ?>) input).size();
        }​​​​​​​
        
        return false;
    }​​

	/**
     * @see validation.constraint.HandleValidationSegment#isValidConstraintType()
     */
    @Override
    public boolean isValidConstraintType() {​​​​​​​
        return constraintData.matches("^-?\\d+$");
    }​​

	/**
     * @see validation.constraint.HandleValidationSegment#getValidateType()
     */
    @Override
    public String getValidateType() {​​​​​​​
        return KEY;
    }​
	/**
     * @see validation.custom.ValidateSegment#getInfor(java.lang.Object)
     */
    @Override
    public String[] getInfor(Object input) {​​​​​​​
        return null;
    }​​

}​ ​ ​ 