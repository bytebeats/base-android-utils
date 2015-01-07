/**   
 * @Title: BatteryStateReceiver.java 
 * @Package me.pc.mobile.helper.receiver 
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com   
 * @date 2014 2014-11-18 下午2:23:32 
 * @version V1.0.0   
 */
package me.pc.mobile.helper.v14.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import me.pc.mobile.helper.v14.util.LogUtil;

/**
 * @author SilentKnight 
 * declare this in your AndroidManifest.xml: {@code}
 *         <receiver android:name="com.home.receiver.BatteryReceiver">
 *         <intent-filter> 
 *         <action android:name="android.intent.action.BATTERY_CHANGED" /> 
 *         <action android:name="android.intent.action.BATTERY_OKAY"/> 
 *         <action android:name="android.intent.action.BATTERY_LOW"/> 
 *         </intent-filter>
 *         </receiver>{@code}
 */
public class BatteryStateReceiver extends BroadcastReceiver {

	private static final String TAG = BatteryStateReceiver.class
			.getSimpleName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())) {
			// to do
		} else if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())) {
			// to do
		} else if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
			Bundle bundle = intent.getExtras();
			int current = bundle.getInt("level");
			int total = bundle.getInt("scale");
			LogUtil.info(TAG, "Current Battery is: " + (current * 100 / total));
		}
	}

}
