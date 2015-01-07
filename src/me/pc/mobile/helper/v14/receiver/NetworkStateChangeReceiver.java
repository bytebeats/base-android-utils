/**   
 * @Title: NetworkStateChangeReceiver.java
 * @Package me.pc.mobile.helper.receiver
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014 2014-11-19 上午11:30:21
 * @version V1.0.0 
 */
package me.pc.mobile.helper.v14.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import me.pc.mobile.helper.v14.util.LogUtil;

/**
 * @author SilentKnight < !-- Needed to check when the network connection
 *         changes -->
 * 
 *         < uses-permission
 *         android:name="android.permission.ACCESS_NETWORK_STATE"/> < receiver
 *         android
 *         :name="com.blackboard.androidtest.receiver.ConnectionChangeReceiver"
 *         android:label="NetworkConnection"> < intent-filter> < action
 *         android:name="android.net.conn.CONNECTIVITY_CHANGE"/> <
 *         /intent-filter> < /receiver>
 * 
 * 
 *         <uses-permission
 *         android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */
public class NetworkStateChangeReceiver extends BroadcastReceiver {
	private static final String TAG = NetworkStateChangeReceiver.class
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
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
			LogUtil.info(TAG, "Network Connection State changes");
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = connectivityManager.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				String name = info.getTypeName();
				LogUtil.info(TAG, "当前网络名称：" + name);
			} else {
				LogUtil.info(TAG, "没有可用网络");
			}
		}
	}

}
