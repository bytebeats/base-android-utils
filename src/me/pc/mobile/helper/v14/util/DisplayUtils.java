/**   
 * @Title: DisplayUtils.java 
 * @Package me.pc.mobile.helper.util 
 * @Description: TODO
 * @author SilentKnight || happychinapc[at]gmail[dot]com   
 * @date 2014 2014年12月4日 下午3:55:29 
 * @version V1.0.0   
 */
package me.pc.mobile.helper.v14.util;

import android.content.Context;

/**
 * @ClassName: DisplayUtils
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014年12月4日 下午3:55:29
 * 
 */
public final class DisplayUtils {
	private DisplayUtils() {
	}

	public static int px2Dp(Context ctx, float pxValue) {
		final float density = ctx.getResources().getDisplayMetrics().density;
		return (int) (pxValue / density + 0.5f);
	}

	public static int dp2Px(Context ctx, float dpValue) {
		final float density = ctx.getResources().getDisplayMetrics().density;
		return (int) (dpValue * density + 0.5f);
	}

	public static int sp2Px(Context ctx, float spValue) {
		final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * scaledDensity + 0.5f);
	}

	public static int px2Sp(Context ctx, float pxValue) {
		final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / scaledDensity + 0.5f);
	}

	public static int dp2Sp(Context ctx, float dpValue) {
		final float density = ctx.getResources().getDisplayMetrics().scaledDensity;
		final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
		return (int) (dpValue * density / scaledDensity + 0.5f);
	}

	public static int sp2Dp(Context ctx, float spValue) {
		final float density = ctx.getResources().getDisplayMetrics().scaledDensity;
		final float scaledDensity = ctx.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * scaledDensity / density + 0.5f);
	}
}
