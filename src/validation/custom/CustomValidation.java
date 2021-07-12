package validation.custom;

public abstract class CustomValidation<K>{
	
	protected final String VALIDATION_SEPARATION = ";";
	
	protected String constraints;

    /**
     * @param constraints
     */
    public CustomValidation(String constraints) {
        super();
        this.constraints = constraints;
    }
    
    protected String[] splitConstraints(){
        assert(this.constraints != null && this.constraints.indexOf(VALIDATION_SEPARATION) > 0);
        return this.constraints.split(VALIDATION_SEPARATION);
    }
    
    public abstract boolean checkValidateConstraints() throws Exception;
}
