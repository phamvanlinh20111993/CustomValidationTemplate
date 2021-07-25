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

		// test 1
		String constraints = "min:{10};max:{100};pattern:{.+};range:{0,100};notnull;notempty";
		String input = "afasfdasfasfasfasfas fasfasfasfasfasfasf";

		CustomValidation<Object> customealidation = new CustomTemplateValidation(input, constraints);
		System.out.println(customealidation.checkValidateConstraints());
		// test 2
		String constraints1 = "notempty";
		String input1 = " ";
		CustomValidation<Object> customealidation1 = new CustomTemplateValidation(input1, constraints1);
		System.out.println(customealidation1.checkValidateConstraints());
		// test 3
		String constraints3 = "pattern:{\\d{10,104}};range:{10, 101}";
		String input3 = "10000000000000000000000000";

		CustomValidation<Object> customealidation2 = new CustomTemplateValidation(input3, constraints3);
		System.out.println(customealidation2.checkValidateConstraints());

		// test 3
		String constraints4 = "pattern:{\\d{10,104}};range:{1, 101}";
		List<String> input4 = new ArrayList<>();
		input4.add("10000000000000000000000000");

		CustomValidation<Object> customealidation4 = new CustomTemplateValidation(input4, constraints4);
		System.out.println(customealidation4.checkValidateConstraints());

		String constraints5 = "size:{4}";
		List<String> input5 = new ArrayList<>();
		input5.add("1323");
		input5.add("1323");
		input5.add("1323");
		input5.add("1323");
		input5.add("1323");
		CustomValidation<Object> customealidation5 = new CustomTemplateValidation(input5, constraints5);
		System.out.println(customealidation5.checkValidateConstraints());
	}

}
