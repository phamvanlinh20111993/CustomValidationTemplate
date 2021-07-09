package validation.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import validation.constraint.HandleArrayConstraint;
import validation.exception.InvalidConstraintException;
import validation.exception.InvalidInputException;
import validation.utils.CommonUtils;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 8. 오후 1:12:55
 * </ul>
 *
 * @author dev
 * 
 *         Hanle this syntax: 1, [constraint]{from, to} 2, [constraint]{from,}
 *         -> to end 3, [constraint]{,to} -> from currentElement to to 4, [];[]
 * 
 */
public class CustomArrayTemplateValidation<K> extends CustomValidation<K> {

	private Collection<K> input;

	/**
	 * @param input
	 * @param constraints
	 */
	public CustomArrayTemplateValidation(Collection<K> input, String constraints) {
		super(constraints);
		this.input = input;

	}

	protected String[] splitConstraints() {
		assert (constraints != null && constraints.indexOf(this.VALIDATION_SEPARATION) > 0);
		String formatConstraints = this.constraints.replaceAll("\\s+", "");
		formatConstraints = formatConstraints.replaceAll(";{2,}", ";");

		List<String> constraintList = new ArrayList<>();
		int startIndex = 0;
		for (int index = 0; index < formatConstraints.length(); index++) {
			if (formatConstraints.charAt(index) == ';') {
				if ((index > 0
						&& (formatConstraints.charAt(index - 1) == '}' || formatConstraints.charAt(index - 1) == ']'))
						&& (index < formatConstraints.length() - 1 && formatConstraints.charAt(index + 1) == '[')) {
					constraintList.add(formatConstraints.substring(startIndex, index));
					startIndex = index + 1;
				}
			}
		}
		if (startIndex != formatConstraints.length() && formatConstraints.charAt(startIndex) == '[') {
			constraintList.add(formatConstraints.substring(startIndex, formatConstraints.length()));
		}
		return constraintList.toArray(new String[constraintList.size()]);
	}

	// TODO need to refactor and change some logic.
	/**
	 * @see validation.custom.CustomValidation#checkValidateConstraints()
	 */
	@Override
	public boolean checkValidateConstraints() throws Exception {
		assert (input != null);
		Object[] inputArr = this.input.toArray();
		List<ArrayTypeConstraintRule> constraintsRule = getConstraintsRule();

		for (ArrayTypeConstraintRule constraintRule : constraintsRule) {
			System.out.println("constraint " + constraintRule.toString());
			int valid = validateElement(inputArr, constraintRule.getConstraint(), constraintRule.getFrom(),
					constraintRule.getTo());
			if (valid >= 0) {
				throw new InvalidInputException("Invalid validation at element " + valid + ".");
			}
		}

		return true;
	}

	private List<ArrayTypeConstraintRule> getConstraintsRule() {
		String[] constraints = this.splitConstraints();
		Integer currentElement = 0;
		List<ArrayTypeConstraintRule> constraintsRule = new ArrayList<>();
		for (String constraint : constraints) {
			if (currentElement > this.input.size()) {
				break;
			}

			ArrayTypeConstraintRule constraintRule = new HandleArrayConstraint(constraint).getConstraintProperty();
			Integer from = constraintRule.getFrom();
			Integer to = constraintRule.getTo();
			if (CommonUtils.isNotNull(from) && CommonUtils.isNotNull(to)) {
				if (constraintRule.getFrom() >= constraintRule.getTo()) {
					throw new InvalidConstraintException("Invalid [from, to] constraint [" + from + ", " + to + "].");
				}
				if (to > this.input.size()) {
					constraintRule.setTo(this.input.size());
				}
				currentElement = to;
			}
			if (CommonUtils.isNull(from) && CommonUtils.isNull(to)) {
				constraintRule.setFrom(currentElement);
				constraintRule.setTo(currentElement + 1);
				currentElement++;
			}
			if (CommonUtils.isNull(from) && CommonUtils.isNotNull(to)) {
				constraintRule.setFrom(currentElement);
				constraintRule.setTo(to);
				currentElement = to;
			}
			if (CommonUtils.isNotNull(from) && CommonUtils.isNull(to)) {
				constraintRule.setFrom(from);
				constraintRule.setTo(this.input.size());
				currentElement = constraintRule.getTo();
			}
			constraintsRule.add(constraintRule);
		}

		return constraintsRule;
	}

	/**
	 * 
	 * @param constraint
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	private int validateElement(Object[] inputArr, String constraint, int from, int to) throws Exception {
		int valid = -1;
		int limitRange = inputArr.length < to ? inputArr.length : to;

		for (int index = from; index < limitRange; index++) {
			@SuppressWarnings("unchecked")
			boolean isValid = new CustomTemplateValidation<K>((K) inputArr[index], constraint)
					.checkValidateConstraints();
			if (!isValid) {
				valid = index;
				break;
			}
		}
		return valid;
	}
}