package validation.custom;

public interface ValidateSegment<T> {

	boolean isValid(T input) throws Exception;

	String[] getInfor(T input);
}
