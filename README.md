# FlexToolBar
![image](https://github.com/tinybright/FlexToolBar/blob/master/sample.png?raw=false)
对有ToolBar的页面进行了封装，方便设置ToolBar。使用了标题居中的样式。

  >使用

直接继承BaseActivity或者自己参照BaseActivity实现一个基类：

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
单独使用FlexToolBar
    
            <com.tinylight.flextoolbar.FlexToolBar
            android:id="@+id/ftb_main"
            android:layout_width="match_parent"
            android:layout_height="44dp"
            android:background="@android:color/black"
            app:ftb_icon_center="@mipmap/ic_launcher"
            app:ftb_icon_left="@mipmap/ic_launcher"
            app:ftb_icon_right="@mipmap/ic_launcher"
            app:ftb_text_left="left"
            app:ftb_text_right="right"
            app:ftb_text_center="center">
