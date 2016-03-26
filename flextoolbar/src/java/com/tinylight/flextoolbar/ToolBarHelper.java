package com.tinylight.flextoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;



public class ToolBarHelper {

    /*上下文，创建view的时候需要用到*/
    private final Context mContext;

    /*base view*/
    private FrameLayout mContentView;

    /*tb_base*/
    private Toolbar mToolBar;

    /*视图构造器*/
    private final LayoutInflater mInflater;

    /*
    * 两个属性
    * 1、toolbar是否悬浮在窗口之上
    * 2、toolbar的高度获取
    * */
//    16843492
    private static final int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public ToolBarHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化整个内容*/
        initContentView();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
        /*初始化toolbar*/
        initToolBar();
    }

    private void initContentView() {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setFitsSystemWindows(true);
        mContentView.setLayoutParams(params);
    }

    private void initToolBar() {
        View toolbar = mInflater.inflate(R.layout.stool_tb_base_v1, mContentView);
        mToolBar = (Toolbar) toolbar.findViewById(R.id.id_tool_bar);
    }

    private void initUserView(int id) {
        View mUserView = mInflater.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        boolean overly = typedArray.getBoolean(0, false);
        int toolBarSize = mContext.getResources().getDimensionPixelOffset(R.dimen.stool_tb_h);
        typedArray.recycle();
        params.topMargin = overly ? 0 : toolBarSize;
        mContentView.addView(mUserView, params);
    }

    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
}
