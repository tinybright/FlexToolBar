package com.tinylight.flextoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class FlexToolBar extends Toolbar implements View.OnClickListener{
    private String textLeftStr = "";
    private String textRightStr = "";
    private String textCenterStr = "";
    private int iconLeftId = 0;
    private int iconRightId = 0;
    private int iconCenterId = 0;
    private int drawableH = 0;
    private int drawablePadding = 0;
    private boolean dLeftIsLeft = true;
    private boolean dRightIsLeft = true;
    private boolean dCenterIsLeft = true;

    private TextView tvLeft,tvRight,tvCenter;
    private ImageView ivLeft,ivRight,ivCenter;

    public FlexToolBar(Context context) {
        super(context);
        initView(null);
    }

    public FlexToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public FlexToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public void initView(AttributeSet attrs){
        if(isInEditMode())return;
        if(attrs == null)return;
        setContentInsetsRelative(0,0);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FlexToolBar);
        try {
            textLeftStr = typedArray.getString(R.styleable.FlexToolBar_ftb_text_left);
            textRightStr = typedArray.getString(R.styleable.FlexToolBar_ftb_text_right);
            textCenterStr = typedArray.getString(R.styleable.FlexToolBar_ftb_text_center);

            iconLeftId = typedArray.getResourceId(R.styleable.FlexToolBar_ftb_icon_left,0);
            iconRightId = typedArray.getResourceId(R.styleable.FlexToolBar_ftb_icon_right,0);
            iconCenterId = typedArray.getResourceId(R.styleable.FlexToolBar_ftb_icon_center,0);
            int defaultH = (int) (getResources().getDimension(R.dimen.stool_tb_h) - 2*getResources().getDimension(R.dimen.stool_tb_pad_icon));
            drawableH = (int) typedArray.getDimension(R.styleable.FlexToolBar_ftb_drawable_h,defaultH);
            drawablePadding = (int) typedArray.getDimension(R.styleable.FlexToolBar_ftb_drawable_padding,defaultH);
        }catch (Throwable e){
            Log.e("test-stool","initView Error",e);
        }finally {
            typedArray.recycle();
        }
        createLayout();
    }

    private void createLayout() {
        ViewGroup childView = (ViewGroup) View.inflate(getContext(),R.layout.stool_toolbar_flex,this);
        ivLeft = (ImageView) childView.findViewById(R.id.stool_iv_left);
        ivRight = (ImageView) childView.findViewById(R.id.stool_iv_right);
        ivCenter = (ImageView) childView.findViewById(R.id.stool_iv_center);
        tvLeft = (TextView) childView.findViewById(R.id.stool_tv_left);
        tvRight = (TextView) childView.findViewById(R.id.stool_tv_right);
        tvCenter = (TextView) childView.findViewById(R.id.stool_tv_center);
        try {
            customLayout();
        }catch (Throwable e){
            Log.e("test-stool","customLayout Error",e);
        }
        if(ivLeft != null) ivLeft.setOnClickListener(this);
        if(tvLeft != null) tvLeft.setOnClickListener(this);
        if(ivRight != null) ivRight.setOnClickListener(this);
        if(tvRight != null) tvRight.setOnClickListener(this);
    }

    private void customLayout() {
        customLeft();
        customRight();
        customCenter();
    }

    private void customCenter() {
        if(!TextUtils.isEmpty(textCenterStr)){
            if(iconCenterId!=0){
                setDrawableText(tvCenter,textCenterStr,iconCenterId,dCenterIsLeft);
            }else{
                tvCenter.setText(textCenterStr);
            }
            tvCenter.setVisibility(VISIBLE);
            ivCenter.setVisibility(GONE);
        }else if(iconCenterId!=0){
            ivCenter.setImageResource(iconCenterId);
            tvCenter.setVisibility(GONE);
            ivCenter.setVisibility(VISIBLE);
        }
    }

    private void customRight() {
        if(!TextUtils.isEmpty(textRightStr)){
            if(iconRightId!=0){
                setDrawableText(tvRight,textRightStr,iconRightId,dRightIsLeft);
            }else{
                tvRight.setText(textRightStr);
            }
            tvRight.setVisibility(VISIBLE);
            ivRight.setVisibility(GONE);
        }else if(iconRightId!=0){
            ivRight.setImageResource(iconRightId);
            tvRight.setVisibility(GONE);
            ivRight.setVisibility(VISIBLE);
        }
    }

    private void customLeft() {
        if(!TextUtils.isEmpty(textLeftStr)){
            if(iconLeftId!=0){
                setDrawableText(tvLeft,textLeftStr,iconLeftId,dLeftIsLeft);
            }else{
                tvLeft.setText(textLeftStr);
            }
            tvLeft.setVisibility(VISIBLE);
            ivLeft.setVisibility(GONE);
        }else if(iconLeftId!=0){
            ivLeft.setImageResource(iconLeftId);
            tvLeft.setVisibility(GONE);
            ivLeft.setVisibility(VISIBLE);
        }

    }

    private void setDrawableText(TextView tvRight, String textRightStr, int iconRightId ,boolean left) {
        tvRight.setText(textRightStr);
        Drawable drawable = getContext().getResources().getDrawable(iconRightId);
        int drawableHOld = drawable.getIntrinsicHeight();
        int drawableWOld = drawable.getIntrinsicWidth();
        int drawableW = Math.round(drawableWOld * drawableH * 1f/drawableHOld);
        drawable.setBounds(0, 0,drawableW , drawableH);
        tvRight.setCompoundDrawables(left?drawable:null, null,left? null:drawable, null);
//        tvRight.setCompoundDrawablePadding(drawablePadding);
    }

    public FlexToolBar builder(Context context){
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isInEditMode())return;
        super.onDraw(canvas);
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public TextView getTvCenter() {
        return tvCenter;
    }

    public ImageView getIvLeft() {
        return ivLeft;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public ImageView getIvCenter() {
        return ivCenter;
    }

    public String getTextLeftStr() {
        return textLeftStr;
    }

    public void setTextLeftStr(String textLeftStr) {
        this.textLeftStr = textLeftStr;
        customLeft();
    }

    public String getTextRightStr() {
        return textRightStr;
    }

    public void setTextRightStr(String textRightStr) {
        this.textRightStr = textRightStr;
        customRight();
    }

    public String getTextCenterStr() {
        return textCenterStr;
    }

    public void setTextCenterStr(String textCenterStr) {
        this.textCenterStr = textCenterStr;
        customCenter();
    }

    public int getIconLeftId() {
        return iconLeftId;
    }

    public void setIconLeftId(int iconLeftId) {
        this.iconLeftId = iconLeftId;
        customLeft();
    }

    public int getIconRightId() {
        return iconRightId;
    }

    public void setIconRightId(int iconRightId) {
        this.iconRightId = iconRightId;
        customRight();
    }

    public int getIconCenterId() {
        return iconCenterId;
    }

    public void setIconCenterId(int iconCenterId) {
        this.iconCenterId = iconCenterId;
        customCenter();
    }

    public int getDrawableH() {
        return drawableH;
    }

    public boolean isdLeftIsLeft() {
        return dLeftIsLeft;
    }

    public void setdLeftIsLeft(boolean dLeftIsLeft) {
        this.dLeftIsLeft = dLeftIsLeft;
    }

    public boolean isdRightIsLeft() {
        return dRightIsLeft;
    }

    public void setdRightIsLeft(boolean dRightIsLeft) {
        this.dRightIsLeft = dRightIsLeft;
    }

    public boolean isdCenterIsLeft() {
        return dCenterIsLeft;
    }

    public void setdCenterIsLeft(boolean dCenterIsLeft) {
        this.dCenterIsLeft = dCenterIsLeft;
    }

    public ToolBarClick mToolBarBackClick;

    public ToolBarClick getToolBarClick() {
        return mToolBarBackClick;
    }

    public void setToolBarClick(ToolBarClick toolBarBackClick) {
        mToolBarBackClick = toolBarBackClick;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == 0)return;
        if(viewId == R.id.stool_iv_left || viewId == R.id.stool_tv_left){
            if(mToolBarBackClick!=null) mToolBarBackClick.onLeftClick(v);
        }else if(viewId == R.id.stool_iv_right || viewId == R.id.stool_tv_right){
            if(mToolBarBackClick!=null) mToolBarBackClick.onRightClick(v);
        }
    }
}
