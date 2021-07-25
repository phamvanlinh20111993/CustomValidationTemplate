package validation.custom;

import validation.constraint.HandleConstraint;
import validation.constraint.HandleValidationSegment;
import validation.exception.InvalidInputException;
import validation.type.MaxHandleValidationSegment;
import validation.type.MinHandleValidationSegment;
import validation.type.NotEmptyHandleValidationSegment;
import validation.type.NotNullHandleValidationSegment;
import validation.type.PatternHandleValidationSegment;
import validation.type.RangeHandleValidationSegment;
import validation.type.SizeHandleValidationSegment;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오후 2:58:50
 * </ul>
 *
 * @author dev
 */
public class CustomTemplateValidation extends CustomValidation<Object> {

	private Object input;

	/**
	 * @param input
	 */
	public CustomTemplateValidation(Object input, String constraints) {
		super(constraints);
		this.input = input;
	}

	public final boolean checkValidateConstraints() throws Exception {
		assert (this.input != null);
		
		String[] constraints = splitConstraints();

		for (String constraint : constraints) {

			HandleConstraint handleConstraint = new HandleConstraint(constraint);
			HandleValidationSegment<Object> handleValidationSegment = null;
			switch (handleConstraint.getConstraintName()) {
			
			case NotNullHandleValidationSegment.KEY:
				handleValidationSegment = new NotNullHandleValidationSegment(handleConstraint.getConstraintValue());
				break;

			case MaxHandleValidationSegment.KEY:
				handleValidationSegment = new MaxHandleValidationSegment(handleConstraint.getConstraintValue());
				break;

			case MinHandleValidationSegment.KEY:
				handleValidationSegment = new MinHandleValidationSegment(handleConstraint.getConstraintValue());
				break;

			case NotEmptyHandleValidationSegment.KEY:
				handleValidationSegment = new NotEmptyHandleValidationSegment(handleConstraint.getConstraintValue());
				break;

			case RangeHandleValidationSegment.KEY:
				handleValidationSegment = new RangeHandleValidationSegment(handleConstraint.getConstraintValue());
				break;

			case PatternHandleValidationSegment.KEY:
				handleValidationSegment = new PatternHandleValidationSegment(handleConstraint.getConstraintValue());
				break;
				
			case SizeHandleValidationSegment.KEY:
				handleValidationSegment = new SizeHandleValidationSegment(handleConstraint.getConstraintValue());
				break;

			default:
				break;
			}

			if (!handleValidationSegment.handle(input)) {
				throw new InvalidInputException("Fail validate at " + handleValidationSegment.getClass()
						+ ", (constraint, input) = (" + handleValidationSegment.getInfor(input)[0] + ", "
						+ handleValidationSegment.getInfor(input)[1] + ")");
			}
		}

		return true;
	}
}