package com.example.crispy;

public class Item {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public Item(String ImageUrl, String Creator, int Likes) {
        mImageUrl = ImageUrl;
        mCreator = Creator;
        mLikes = Likes;
    }
    public Item() {

    }

    public String getmImageUrl() {
        return mImageUrl;
    }

//    public void setmImageUrl(String mImageUrl) {
//        this.mImageUrl = mImageUrl;
//    }

    public String getmCreator() {
        return mCreator;
    }

//    public void setmCreator(String mCreator) {
//        this.mCreator = mCreator;
//    }

    public int getmLikes() {
        return mLikes;
    }

//    public void setmLikes(int mLikes) {
//        this.mLikes = mLikes;
//    }
}
