package ru.yandex.yamblz.ui.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dsukmanova on 31.07.16.
 */

public class ItemsChangedDecorator extends RecyclerView.ItemDecoration {
    private int fromPos = -1;
    private int toPos = -1;
    private int CIRCLE_RADIUS = 15;
    private int CIRCLE_OFFSET = 30;

    private Paint paint;

    ItemsChangedDecorator() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
    }

    public void setFromPos(int fromPos) {
        this.fromPos = fromPos;
    }

    public void setToPos(int toPos) {
        this.toPos = toPos;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (fromPos > -1) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

            View child = layoutManager.findViewByPosition(fromPos);
            if (child != null) {
                drawCircle(c, layoutManager, child);
            }
            child = layoutManager.findViewByPosition(toPos);
            if (child != null) {
                drawCircle(c, layoutManager, child);
            }
        }
    }

    private void drawCircle(Canvas c, RecyclerView.LayoutManager lm, View child) {
        int heigth = Math.abs(lm.getDecoratedTop(child) - lm.getDecoratedBottom(child));
        c.drawCircle(lm.getDecoratedRight(child) - CIRCLE_OFFSET, lm.getDecoratedTop(child) + heigth / 2, CIRCLE_RADIUS, paint);
    }
}
