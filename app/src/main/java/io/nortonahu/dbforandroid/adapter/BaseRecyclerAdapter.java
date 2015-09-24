package io.nortonahu.dbforandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/24 16:03.
 * Description: 数据库基本适配器
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final String TAG = getClass().getSimpleName();
    protected final Context _context;
    protected final LayoutInflater _layoutInflater;
    protected List<T> _dataList = new ArrayList<>();

    public BaseRecyclerAdapter(Context context, LayoutInflater layoutInflater) {
        _context = context;
        _layoutInflater = layoutInflater;
    }

    public List<T> getDataList() {
        return _dataList;
    }

    public T getItemData(int position) {
        return position < _dataList.size() ? _dataList.get(position) : null;
    }


    /**
     * 增加一条记录
     *
     * @param item
     */
    public void addItem(T item) {
        addItemByPosition(item, _dataList.size());
    }

    /**
     * 在某个位置增加一条记录
     *
     * @param item
     * @param position
     */
    public void addItemByPosition(T item, int position) {
        if (position <= _dataList.size()) {
            _dataList.add(position, item);
            notifyItemInserted(position);
        }
    }

    /**
     * 根据position删除对象
     *
     * @param position
     */
    public void removeItem(int position) {
        if (position < _dataList.size()) {
            _dataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 批量插入数据
     *
     * @param items
     */
    public void addItems(List<T> items, int position) {
        if (position <= _dataList.size() && items != null && items.size() > 0) {
            _dataList.addAll(position, items);
            notifyItemRangeChanged(position, items.size());
        }
    }

    /**
     * 清除数据元素
     */
    public void clearList() {
        int size = _dataList.size();
        if (size > 0) {
            _dataList.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    /**
     * 批量添加记录
     *
     * @param data 需要加入的数据结构
     */
    public void addItems(List<T> data) {
        addItems(data, _dataList.size());
    }


    @Override
    public int getItemCount() {
        return _dataList == null ? 0 : _dataList.size();
    }
}
