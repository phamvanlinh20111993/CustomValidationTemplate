package validation.utils;

/**
 * <ul>
 * <li>Created by : dev
 * <li>Created Date : 2021. 7. 9. 오후 2:59:01
 * </ul>
 *
 * @author dev
 */
public final class CommonUtils {
	public static final String STRING_EMPTY = "";

	public static final boolean isNull(Object object) {
		return object == null;
	}

	public static final boolean isNotNull(Object object) {
		return !isNull(object);
	}

	public static final boolean isEmpty(Object object) {
		return isNull(object) || (isNotNull(object) && object.toString().equals(STRING_EMPTY));
	}

	public static final boolean isNotEmpty(Object object) {
		return !isEmpty(object);
	}
}
