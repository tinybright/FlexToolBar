package com.tinylight.flextoolbar;

public class ToolBarUtils {
    public static void setupToolbar(FlexToolBar toolbar, int iconLeft, String textLeft, String textCenter, int iconRight, String textRight, ToolBarClick toolBarClick) {
        if(toolbar == null)return;
        toolbar.setIconLeftId(iconLeft);
        toolbar.setTextLeftStr(textLeft);
        toolbar.setTextCenterStr(textCenter);
        toolbar.setIconRightId(iconRight);
        toolbar.setTextRightStr(textRight);
        toolbar.setToolBarClick(toolBarClick);
    }
}
