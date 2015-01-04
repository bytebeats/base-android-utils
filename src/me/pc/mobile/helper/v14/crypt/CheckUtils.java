package me.pc.mobile.helper.v14.crypt;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 通用检查
 * 
 * @company YeePay
 * @author xingwei.bi
 * @since 2010-9-9
 * @version 1.0
 */
public class CheckUtils {

	public static final String COMMON_FIELD = "flowID,initiator,";

	/**
	 * 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
	 * 
	 * @param obj
	 *            被验证的对象
	 * @param message
	 *            异常信息
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof String && obj.toString().trim().length() == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Map && ((Map) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		}
	}

}
