/**   
 * @Title: BaseActivity.java 
 * @Package me.pc.mobile.helper.v14 
 * @Description: TODO
 * @author SilentKnight || happychinapc[at]gmail[dot]com   
 * @date 2014 2014年12月18日 下午4:49:03 
 * @version V1.0.0   
 */
package me.pc.mobile.helper.v14.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * @ClassName: BaseActivity
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014年12月18日 下午4:49:03
 * 
 */
public abstract class BaseActivity extends Activity {
	/*
	 * (non-avadoc) <p>Title: onCreate</p> <p>Description: </p>
	 * 
	 * @params @param savedInstanceState
	 * 
	 * @overrided @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		BaseApp.push(this);
		initContentView();
		findViews();
		initWidgets();
		loadData();
	}

	protected abstract void initContentView();

	protected abstract void findViews();

	protected abstract void initWidgets();

	protected abstract void loadData();

	/*
	 * (non-avadoc) <p>Title: onDestroy</p> <p>Description: </p>
	 * 
	 * @params
	 * 
	 * @overrided @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		BaseApp.pop();
	}
}
