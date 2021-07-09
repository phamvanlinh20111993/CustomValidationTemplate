package validation.custom;

public abstract class CustomValidation<K> implements ValidateSegment<K> {
	
	protected final String VALIDATION_SEPARATION = ";";
	
	protected String constraints;

	public CustomValidation(String constraints) {
		super();
		this.constraints = constraints;
	}
}
