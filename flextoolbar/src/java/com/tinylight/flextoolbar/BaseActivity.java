package com.tinylight.flextoolbar;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public abstract class BaseActivity extends ToolBarActivity{

    protected abstract void initToolBar();
    protected abstract void initStage();
    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolBar();
        initStage();
    }

    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.hideOverflowMenu();
        toolbar.setNavigationIcon(null);
    }

}
