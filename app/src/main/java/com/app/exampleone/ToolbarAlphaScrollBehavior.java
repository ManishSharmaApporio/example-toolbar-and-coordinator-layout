package com.app.exampleone;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class ToolbarAlphaScrollBehavior extends CoordinatorLayout.Behavior<android.support.v7.widget.Toolbar> {

    public ToolbarAlphaScrollBehavior() {

    }

    public ToolbarAlphaScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, Toolbar child, MotionEvent ev) {
        return ev == null || super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        if (dependency instanceof AppBarLayout) {
            float ratio = (float) getCurrentScrollValue(child, dependency) / getTotalScrollRange(child, dependency);
            float alpha = 1f - Math.min(1f, Math.max(0f, ratio));
            int drawableAlpha = (int) (alpha * 255);

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                child.getBackground().setAlpha(drawableAlpha);
            } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ViewGroup toolbarParent = (ViewGroup) child.getParent();
                if (toolbarParent.getChildCount() == 2) {
                    int count = toolbarParent.getChildCount();
                    for (int i = count - 1; i >= 0; i--) {
                        toolbarParent.getChildAt(i).getBackground().setAlpha(drawableAlpha);
                    }
                }
            } else {
                child.getBackground().setAlpha(drawableAlpha);
            }

            if (child.getChildCount() != 0)
                child.getChildAt(0).setAlpha(alpha);
        }

        return false;
    }

    private int getCurrentScrollValue(Toolbar child, View dependency) {
        return dependency.getBottom() - child.getTop();
    }

    private float getTotalScrollRange(Toolbar child, View dependency) {
        return Math.max(dependency.getHeight(), ((AppBarLayout) dependency).getTotalScrollRange()) - child.getTop();
    }
}