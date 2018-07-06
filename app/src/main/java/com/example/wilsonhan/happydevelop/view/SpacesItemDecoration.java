package com.example.wilsonhan.happydevelop.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wilsonhan on 2018/3/10.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    public static final int VERTICAL = 0x110;
    public static final int HORIZONTAL = 0x111;
    private int space;
    private int orientation = VERTICAL;

    public SpacesItemDecoration(int space, int orientation) {
        this.space = space;
        this.orientation = orientation;
    }

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
//        outRect.left = space;
//        outRect.right = space;
//        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) != 0) {
            if (orientation == VERTICAL) {
                outRect.top = space;
            } else if (orientation == HORIZONTAL) {
                outRect.left = space;
            }
        }
    }

}
