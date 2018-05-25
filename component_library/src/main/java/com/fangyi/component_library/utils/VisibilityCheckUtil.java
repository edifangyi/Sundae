package com.fangyi.component_library.utils;

import android.graphics.Rect;
import android.view.View;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/24
 * 说    明：
 * ================================================
 */
public class VisibilityCheckUtil {
    public static final int ALL_INVISIBLE = 0;
    public static final int ALL_VISIBLE = 1;
    public static final int PART_VISIBLE = 2;

    public static int checkVisibility(View view) {

        Rect rect = new Rect();
        boolean b = view.getLocalVisibleRect(rect);
        if (b) {
            if (rect.width() == view.getMeasuredWidth() && rect.height() == view.getMeasuredHeight()) {// 这里用完全可见，也可调节比例
                return ALL_VISIBLE;
            }
            return PART_VISIBLE;

        }
        return ALL_INVISIBLE;
    }
}
