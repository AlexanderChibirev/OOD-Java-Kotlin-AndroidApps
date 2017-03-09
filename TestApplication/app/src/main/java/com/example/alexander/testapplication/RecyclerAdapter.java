package com.example.alexander.testapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private CustomItemClickListener mListener;


    public RecyclerAdapter(CustomItemClickListener listener) {
        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_thumbnail);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            itemDetail = (TextView) itemView.findViewById(R.id.item_detail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rrs_item_model_for_recycler_view, viewGroup, false);
        final int position = i;
        v.setOnClickListener(v1 -> mListener.onItemClick(v1, position));
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText("Name");
        viewHolder.itemDetail.setText("News");

        Picasso.with(viewHolder.itemImage.getContext())
                .load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error_fallback)
                .into(viewHolder.itemImage);
    }
}