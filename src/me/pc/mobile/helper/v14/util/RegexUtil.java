/**   
 * @Title: RegexUtil.java 
 * @Package me.pc.mobile.helper.util 
 * @Description: TODO
 * @author SilentKnight || happychinapc[at]gmail[dot]com   
 * @date 2014 2014年12月4日 下午4:45:45 
 * @version V1.0.0   
 */
package me.pc.mobile.helper.v14.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegexUtil
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014年12月4日 下午4:45:45
 * 
 */
public final class RegexUtil {
	private RegexUtil() {
	}

	public static String compile(String source, String regex, String newValue) {
		Pattern patterns = Pattern.compile(regex);
		Matcher matcher = patterns.matcher(source);
		if (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				String group = matcher.group(i);
				source.replace(group, newValue);
			}
		}
		return source;
	}
}
