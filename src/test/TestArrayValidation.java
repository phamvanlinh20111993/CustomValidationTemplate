package test;

import java.util.ArrayList;
import java.util.List;

import validation.custom.CustomArrayTemplateValidation;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : Jul 9, 2021 9:41:17 AM
 * </ul>
 *
 * @author dev
 */
public class TestArrayValidation {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		List<String> inputs = new ArrayList<>();
		inputs.add("ph");
		inputs.add("pham van fsdfsdfsfsd linh");
		inputs.add("pham van fsdfs");
		inputs.add("20044444s4444444444");
		inputs.add("20044444s4444444444");
		inputs.add("20044444s4444444444");
		// String constraints = "[max:{100}];[max:{7}]";
		String constraints = "[max:{100};min:{10}]{1,2};[min:{1};range:{10,19}];[max:{40}];[pattern:{^\\d+$}]";
		CustomArrayTemplateValidation<String> customArrayTemplateValidation = new CustomArrayTemplateValidation<>(
				inputs, constraints);

		System.out.println("res " + customArrayTemplateValidation.checkValidateConstraints());
	}

}