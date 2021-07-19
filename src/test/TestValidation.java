package test;

import java.util.ArrayList;
import java.util.List;

import validation.custom.CustomTemplateValidation;
import validation.custom.CustomValidation;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 7. 오전 10:25:38
 * </ul>
 *
 * @author dev
 */
public class TestValidation {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// String constraints =
		// "min:{10};max:{100};pattern:{.+};range:{0,100};notnull;notempty";
		// String input = "afasfdasfasfasfasfas fasfasfasfasfasfasf";
		//
		// String constraints = "notempty";
		// String input = " ";
		//
		// String constraints = "pattern:{\\d{10,104}};range:{10, 101}";
		// String input = "10000000000000000000000000";

		String constraints = "pattern:{\\d{10,104}};range:{10, 101}";
		List<String> input = new ArrayList<>();
		input.add("10000000000000000000000000");

		CustomValidation<String> customealidation = new CustomTemplateValidation<>(input, constraints);
		System.out.println(customealidation.checkValidateConstraints());
	}

}
