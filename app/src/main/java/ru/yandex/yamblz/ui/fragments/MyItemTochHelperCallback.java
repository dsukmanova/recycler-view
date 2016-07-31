package ru.yandex.yamblz.ui.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;


/**
 * Created by dsukmanova on 31.07.16.
 */

public class MyItemTochHelperCallback extends ItemTouchHelper.Callback {
    MyItemTouchHelperAdapter myItemTouchHelperAdapter;

    MyItemTochHelperCallback(MyItemTouchHelperAdapter myItemTouchHelperAdapter) {
        this.myItemTouchHelperAdapter = myItemTouchHelperAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int moveFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.RIGHT;
        return makeMovementFlags(moveFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        myItemTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        myItemTouchHelperAdapter.onItemDissmiss(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            View itemView = viewHolder.itemView;
            int itemViewWidth = itemView.getWidth();
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            int maxAlpha = 255;
            double kAlpha = 0.75;
            paint.setAlpha(Math.min(maxAlpha, (int) (kAlpha * maxAlpha * Math.abs(dX) / itemViewWidth)));
            float rectLeft = dX > 0 ? itemView.getLeft() : (itemView.getLeft() + dX);
            float rectRight = dX > 0 ? dX : itemView.getRight();
            c.drawRect(rectLeft, itemView.getTop(), rectRight, itemView.getBottom(), paint);

        }
    }
}
