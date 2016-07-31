package ru.yandex.yamblz.ui.fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;

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
        return makeMovementFlags(0, RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        myItemTouchHelperAdapter.onItemDissmiss(position);
    }
}
