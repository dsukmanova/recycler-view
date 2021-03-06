package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import ru.yandex.yamblz.R;

public class ContentFragment extends BaseFragment {
    private boolean bordersOn = false;
    private int bordersWidth = 3;
    private MyItemDecoration myItemDecoration;
    private ItemsChangedDecorator itemsChangedDecorator;
    private GridLayoutManager gridLayoutManager;
    private int columnsNum = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            columnsNum = savedInstanceState.getInt("columnsNum");
        }
        setHasOptionsMenu(true);
    }

    @BindView(R.id.rv)
    RecyclerView rv;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.borders:
                switchBordersMode();
                break;
            case R.id.plusColumns:
                columnsNum ++;
                gridLayoutManager.setSpanCount(columnsNum);
                break;
            case R.id.minusColumns:
                columnsNum--;
                gridLayoutManager.setSpanCount(columnsNum);
                break;
        }
        rv.getAdapter().notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    private void switchBordersMode (){
        if (bordersOn){
            rv.removeItemDecoration(myItemDecoration);
            bordersOn = false;
        } else {
            rv.addItemDecoration(myItemDecoration);
            bordersOn = true;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ContentAdapter contentAdapter = new ContentAdapter();
        rv.setAdapter(contentAdapter);

        itemsChangedDecorator = new ItemsChangedDecorator();
        ItemTouchHelper.Callback callback = new MyItemTochHelperCallback(contentAdapter,itemsChangedDecorator);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);

        myItemDecoration = new MyItemDecoration(bordersWidth);
        if (bordersOn) rv.addItemDecoration(myItemDecoration);

        rv.addItemDecoration(itemsChangedDecorator);
        gridLayoutManager = new GridLayoutManager(getContext(), columnsNum);
        rv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("columnsNum",columnsNum);
    }
}
