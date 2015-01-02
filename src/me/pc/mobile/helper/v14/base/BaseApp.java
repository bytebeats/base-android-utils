/**   
 * @Title: BaseApp.java 
 * @Package me.pc.mobile.helper.v14 
 * @Description: TODO
 * @author SilentKnight || happychinapc[at]gmail[dot]com   
 * @date 2014 2014年12月18日 下午4:51:30 
 * @version V1.0.0   
 */
package me.pc.mobile.helper.v14.base;

import java.util.Stack;

import android.app.Activity;
import android.app.Application;

/**
 * @ClassName: BaseApp
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014年12月18日 下午4:51:30
 * 
 */
public class BaseApp extends Application {

	private static Stack<Activity> activities = new Stack<Activity>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onTerminate()
	 */
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		exitAppCompletely();
	}

	public static void push(Activity activity) {
		activities.push(activity);
	}

	public static void pop() {
		Activity activity = activities.pop();
		if (activity != null && !activity.isFinishing()) {
			activity.finish();
		}
	}

	public static void exitAppCompletely() {
		while (!activities.isEmpty()) {
			pop();
		}
	}
}
