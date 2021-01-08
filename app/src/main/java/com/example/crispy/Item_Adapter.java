package com.example.crispy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Item_Adapter extends RecyclerView.Adapter<Item_Adapter.ItemHolder> {
    private Context mContext;
    private ArrayList<Item> mItemList;
    private OnItemClickListner mListener;
    public interface OnItemClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListner listener){
        mListener=listener;
    }

    public Item_Adapter(Context context,ArrayList<Item> itemList){
        mContext=context;
        mItemList=itemList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position){
        Item currentItem=mItemList.get(position);
        String imageUrl=currentItem.getmImageUrl();
        String creatorName=currentItem.getmCreator();
        int likeCount=currentItem.getmLikes();
        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Likes: "+likeCount);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image_view);
            mTextViewCreator=itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes=itemView.findViewById(R.id.text_view_likes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
