/**   
 * @Title: BaseFrag.java 
 * @Package com.yeepay.mobile.tv.base 
 * @Description: TODO
 * @author SilentKnight || happychinapc[at]gmail[dot]com   
 * @date 2014 2014年12月26日 下午6:45:43 
 * @version V1.0.0   
 */
package me.pc.mobile.helper.v14.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName: BaseFrag
 * @Description: TODO
 * @author SilentKnight || happychinapc@gmail.com
 * @date 2014年12月26日 下午6:45:43
 * 
 */
public abstract class BaseFrag extends Fragment {
	/*
	 * (non-avadoc) <p>Title: onCreateView</p> <p>Description: </p>
	 * 
	 * @params @param inflater
	 * 
	 * @params @param container
	 * 
	 * @params @param savedInstanceState
	 * 
	 * @params @return
	 * 
	 * @overrided @see
	 * android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=initLayout(inflater);
		findViewById(view);
		return view;
	}

	public void onViewCreated(View view, Bundle savedInstanceState) {
		initWidget();
		loadData();
	};

	public abstract View initLayout(LayoutInflater inflater);

	public abstract void findViewById(View parent);

	public abstract void initWidget();

	public abstract void loadData();
}
