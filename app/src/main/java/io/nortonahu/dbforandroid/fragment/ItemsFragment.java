package io.nortonahu.dbforandroid.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.nortonahu.dbforandroid.R;
import io.nortonahu.dbforandroid.adapter.NormalRecyclerViewAdapter;
import io.nortonahu.dbforandroid.app.AppContext;
import io.nortonahu.dbforandroid.bean.TestItem;
import io.nortonahu.dbforandroid.db.ItemsDataHelper;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/29 16:52.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class ItemsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

//    private Ite
    @Bind(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private NormalRecyclerViewAdapter mAdapter;

    private ItemsDataHelper mDBHelper;

    public static ItemsFragment newInstance() {
        return new ItemsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBHelper = new ItemsDataHelper(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new NormalRecyclerViewAdapter(getActivity());
        mAdapter.setData(AppContext.stringArray(R.array.db_items));

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    private void loadItems() {
        String[] items = getActivity().getResources().getStringArray(R.array.db_items);
        ArrayList<TestItem> demoItems = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            demoItems.add(new TestItem(i, items[i]));
        }
        mDBHelper.bulkInsert(demoItems);
    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mDBHelper.getCursorLoader();
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {

            loadItems();
        } else {
//            mAdapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
//        mAdapter.changeCursor(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
