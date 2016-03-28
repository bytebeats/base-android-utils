package me.pc.mobile.helper.v14.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Pan Chen on 3/28/16 : 15:50.
 * Company : www.CreditEase.cn;
 * Email : happychinapc@gmail.cn;
 * Motto : If you can take it, you can make it.
 */
public class HomeActionReceiver extends BroadcastReceiver {
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";

    private HomeActionWatcher mHomeActionWatcher;

    public HomeActionWatcher getHomeActionWatcher() {
        return mHomeActionWatcher;
    }

    public void setHomeActionWatcher(HomeActionWatcher mHomeActionWatcher) {
        this.mHomeActionWatcher = mHomeActionWatcher;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            // android.intent.action.CLOSE_SYSTEM_DIALOGS
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                // 短按Home键
                if (mHomeActionWatcher != null) {
                    mHomeActionWatcher.onHomeClicked(context, intent);
                }
            } else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {
                // 长按Home键 或者 activity切换键
                if (mHomeActionWatcher != null) {
                    mHomeActionWatcher.onHomeLongClicked(context, intent);
                }
            } else if (SYSTEM_DIALOG_REASON_LOCK.equals(reason)) {
                // 锁屏
                if (mHomeActionWatcher != null) {
                    mHomeActionWatcher.onHomeLocked(context, intent);
                }
            } else if (SYSTEM_DIALOG_REASON_ASSIST.equals(reason)) {
                // Samsung 长按Home键
                if (mHomeActionWatcher != null) {
                    mHomeActionWatcher.onHomeLongClicked(context, intent);
                }
            }
        }
    }

    public interface HomeActionWatcher {
        void onHomeClicked(Context context, Intent intent);

        void onHomeLongClicked(Context context, Intent intent);

        void onHomeLocked(Context context, Intent intent);
    }
}
