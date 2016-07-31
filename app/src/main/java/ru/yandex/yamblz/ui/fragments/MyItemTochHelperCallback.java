package ru.yandex.yamblz.ui.fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


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
}
