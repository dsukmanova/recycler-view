package ru.yandex.yamblz.ui.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dsukmanova on 31.07.16.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int offset;
    Paint paint;

    MyItemDecoration(int offset) {
        this.offset = offset;
        this.paint = new Paint();
        this.paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2 * offset);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            if (position % 2 == 0) {
                c.drawRect(layoutManager.getDecoratedLeft(child) + offset,
                        layoutManager.getDecoratedTop(child) + offset,
                        layoutManager.getDecoratedRight(child) - offset,
                        layoutManager.getDecoratedBottom(child) - offset,
                        paint
                );
            }
        }
    }
}
