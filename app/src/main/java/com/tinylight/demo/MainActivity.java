package com.tinylight.demo;

import android.view.View;
import android.widget.Toast;

import com.tinylight.flextoolbar.BaseActivity;
import com.tinylight.flextoolbar.FlexToolBar;
import com.tinylight.flextoolbar.SimpleToolBarClick;
import com.tinylight.flextoolbar.ToolBarUtils;
import com.tinylight.toolbaractivitysample.R;

/**
 * Created by tinylight on 2016/3/26.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void initToolBar() {
        FlexToolBar toolBar = (FlexToolBar) findViewById(R.id.id_tool_bar);
        ToolBarUtils.setupToolbar(toolBar, R.mipmap.ic_launcher, "back", "title", 0, "action", new SimpleToolBarClick() {
            @Override
            public void onLeftClick(View v) {
                Toast.makeText(MainActivity.this,"click left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick(View v) {
                Toast.makeText(MainActivity.this,"click right",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initStage() {
        //初始化页面
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
