/**   
 * @Title: AsyncHttpUtil.java
 * @Package me.pc.mobile.helper.http
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014 2014-11-19 下午4:50:14
 * @version V1.0.0 
 */
package me.pc.mobile.helper.v14.http;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

/**
 * @author SilentKnight
 * 
 */
public class AsyncHttpUtil {

	private static final AsyncHttpClient mClient = new AsyncHttpClient();
	private static PersistentCookieStore mCookie;

	private AsyncHttpUtil() {
	}

	public static void addCookie(Context context) {
		mCookie = new PersistentCookieStore(context);
		mClient.setCookieStore(mCookie);
	}

	public static void setSessionId(String sessionId) {
		addHeader("Cookie", "JSESSIONID=" + sessionId);
	}

	public static void addHeader(String header, String value) {
		mClient.addHeader(header, value);
	}

	public static RequestHandle get(String url,
			AsyncHttpResponseHandler responseHandler) {
		return get(url, null, responseHandler);
	}

	public static RequestHandle get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		return mClient.get(url, params, responseHandler);
	}

	public static RequestHandle post(String url,
			AsyncHttpResponseHandler responseHandler) {
		return post(url, null, responseHandler);
	}

	public static RequestHandle post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		return mClient.post(url, params, responseHandler);
	}
}
