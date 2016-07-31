package ru.yandex.yamblz.ui.fragments;

/**
 * Created by dsukmanova on 31.07.16.
 */

public interface MyItemTouchHelperAdapter {
    void onItemDissmiss(int position);

    void onItemMove(int fromPosition, int toPosition);
}
