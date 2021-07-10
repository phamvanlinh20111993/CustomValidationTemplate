package validation.type;

import validation.constraint.HandleValidationSegment;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오후 4:15:04
 * </ul>
 *
 * @author dev
 */
public class NotNullHandleValidationSegment extends HandleValidationSegment<Object> {

    public static final String KEY = "notnull";

    /**
     * @param constraintData
     */
    public NotNullHandleValidationSegment(String constraintData) {
        super(constraintData);
    }

    /**
     * @see validation.custom.ValidateSegment#isValid(java.lang.Object)
     */
    @Override
    public boolean isValid(Object input) throws Exception {
        return input != null;
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

    /**
     * @see validation.custom.ValidateSegment#getInfor(java.lang.Object)
     */
    @Override
    public String[] getInfor(Object input) {
        return null;
    }
}
