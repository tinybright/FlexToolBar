package com.tinylight.flextoolbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public abstract class ToolBarActivity extends AppCompatActivity {
    public Toolbar toolbar ;

    @Override
    public void setContentView(int layoutResID) {
        ToolBarHelper mToolBarHelper = new ToolBarHelper(this, layoutResID);
        toolbar = mToolBarHelper.getToolBar() ;
        setContentView(mToolBarHelper.getContentView());
        setSupportActionBar(toolbar);
        onCreateCustomToolBar(toolbar) ;
    }

    public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }
}