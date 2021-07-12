package validation.custom;

import java.util.Collection;

import validation.constraint.HandleConstraint;
import validation.type.MaxHandleValidationSegment;
import validation.type.MinHandleValidationSegment;
import validation.type.NotEmptyHandleValidationSegment;
import validation.type.NotNullHandleValidationSegment;
import validation.type.PatternHandleValidationSegment;
import validation.type.RangeHandleValidationSegment;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오후 2:58:50
 * </ul>
 *
 * @author dev
 */
public class CustomTemplateValidation<T> extends CustomValidation<T> {

	private T input;

	private Collection<T> inputs;

	/**
	 * @param input
	 */
	public CustomTemplateValidation(T input, String constraints) {
		super(constraints);
		this.input = input;
	}

	/**
	 * @param input
	 */
	public CustomTemplateValidation(Collection<T> inputs, String constraints) {
		super(constraints);
		this.inputs = inputs;
	}

	public final boolean checkValidateConstraints() throws Exception {
		assert (this.input != null);
		String[] constraints = splitConstraints();

		String invalidType = "";
		for (String constraint : constraints) {
			HandleConstraint handleConstraint = new HandleConstraint(constraint);
			boolean isValid = true;
			switch (handleConstraint.getConstraintName()) {

			case MaxHandleValidationSegment.KEY:
				isValid = new MaxHandleValidationSegment(handleConstraint.getConstraintValue()).handle(input);
				invalidType = MaxHandleValidationSegment.class.toString();
				break;

			case NotNullHandleValidationSegment.KEY:
				isValid = new NotNullHandleValidationSegment(handleConstraint.getConstraintValue()).handle(input);
				invalidType = NotNullHandleValidationSegment.class.toString();
				break;

			case MinHandleValidationSegment.KEY:
				isValid = new MinHandleValidationSegment(handleConstraint.getConstraintValue()).handle(input);
				invalidType = MinHandleValidationSegment.class.toString();
				break;

			case NotEmptyHandleValidationSegment.KEY:
				isValid = new NotEmptyHandleValidationSegment(handleConstraint.getConstraintValue()).handle(input);
				invalidType = NotEmptyHandleValidationSegment.class.toString();
				break;

			case RangeHandleValidationSegment.KEY:
				isValid = new RangeHandleValidationSegment(handleConstraint.getConstraintValue()).handle(input);
				invalidType = RangeHandleValidationSegment.class.toString();
				break;

			case PatternHandleValidationSegment.KEY:
				isValid = new PatternHandleValidationSegment(handleConstraint.getConstraintValue())
						.handle((String) input);
				invalidType = PatternHandleValidationSegment.class.toString();
				break;

			default:
				break;
			}

			if (!isValid) {
				System.out.println("Fail at " + invalidType);
				return false;
			}
		}

		return true;
	}
}