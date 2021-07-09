package validation.custom;

public class ArrayTypeConstraintRule {
	private String constraint;
	private Integer from;
	private Integer to;
	
	public ArrayTypeConstraintRule() {}
	
	public ArrayTypeConstraintRule(String constraint, Integer from, Integer to) {
		super();
		this.constraint = constraint;
		this.from = from;
		this.to = to;
	}
	
	public String getConstraint() {
		return constraint;
	}

	@Override
	public String toString() {
		return "ArrayTypeConstraintRule [constraint=" + constraint + ", from=" + from + ", to=" + to + "]";
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

}
