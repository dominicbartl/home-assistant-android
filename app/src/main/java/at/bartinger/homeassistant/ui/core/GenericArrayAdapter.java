package at.bartinger.homeassistant.ui.core;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class GenericArrayAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH>{

    private List<T> mItems = new ArrayList<>();

    public T getItem(int position) {
        return mItems.get(position);
    }

    public void add(T item) {
        mItems.add(item);
        onItemSizeChanged();
    }

    public void add(int position, T item) {
        mItems.add(position, item);
        onItemSizeChanged();
    }

    public void addAll(Collection<? extends T> items) {
        mItems.addAll(items);
        onItemSizeChanged();
    }

    public void addAll(int location, Collection<? extends T> items) {
        mItems.addAll(location, items);
        onItemSizeChanged();
    }

    public void remove(T item) {
        mItems.remove(item);
        onItemSizeChanged();
    }

    public void remove(int position) {
        mItems.remove(position);
        onItemSizeChanged();
    }

    public void clear() {
        mItems.clear();
        onItemSizeChanged();
    }

    public int getPosition(T item) {
        return mItems.indexOf(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public List<T> getItems() {
        return mItems;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void sort(Comparator<T> comparator) {
        Collections.sort(mItems, comparator);
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    protected View inflate(@LayoutRes int layout, ViewGroup parent) {
        return LayoutInflater.from(
                parent.getContext()).inflate(layout, parent, false);
    }

    protected void onItemSizeChanged() {}
}