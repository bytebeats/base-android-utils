/**   
 * @Title: BaseJsonParser.java 
 * @Package me.pc.mobile.helper.v14.base.abs 
 * @Description: TODO
 * @author SilentKnight || happychinapc[at]gmail[dot]com   
 * @date 2014 2014年12月28日 下午8:36:43 
 * @version V1.0.0   
 */
package me.pc.mobile.helper.v14.base.abs;

import org.json.JSONObject;

/** 
 * @ClassName: BaseJsonParser 
 * @Description: TODO 
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014年12月28日 下午8:36:43 
 *  
 */
public interface BaseJsonParser {
	Object parse(JSONObject obj);
}
