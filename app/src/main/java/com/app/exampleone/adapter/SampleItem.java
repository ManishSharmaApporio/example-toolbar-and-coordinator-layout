package com.app.exampleone.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.app.exampleone.R;
import com.mikepenz.fastadapter.items.AbstractItem;

public class SampleItem extends AbstractItem<SampleItem, SampleItem.ViewHolder> {
    public String name;

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.adapter_sample_item_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.adapter_sample_item;
    }

    public SampleItem withName(String name) {
        this.name = name;
        return this;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);

        viewHolder.name.setText(name);
    }

    //The viewHolder used for this item. This viewHolder is always reused by the RecyclerView so scrolling is blazing fast
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.item_name);
        }
    }
}