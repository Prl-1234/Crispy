package com.example.crispy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Item_Adapter.OnItemClickListner {
    private RecyclerView mRecyclerView;
    private Item_Adapter mItem_adapter;
    private ArrayList<Item> mItemList;
    private RequestQueue mRequestQueue;
    private EditText page;
    private Button go;
    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKES="likeCount";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        page=(EditText)  findViewById(R.id.page);
        go=(Button) findViewById(R.id.go);
        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mItemList=new ArrayList<>();
        mRequestQueue= Volley.newRequestQueue(MainActivity.this);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemList.clear();
                String p=page.getText().toString();
                if(p==null){
                    parseJSON();
                }
                else{
                   // Toast.makeText(MainActivity.this, p, Toast.LENGTH_SHORT).show();
                        String url = "https://api.unsplash.com/photos/?client_id=jHMA7LvLREqKsTGN3xd2ZI3toZOE8JMeeIwlx36brw8&page="+p;
                        final JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null
                                , new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    for (int i=0;i<response.length();i++){
                                        JSONObject ob=response.getJSONObject(i);
                                        JSONObject urls=ob.getJSONObject("urls");
                                        String imageUrl=urls.getString("regular");
                                        int likeCount=ob.getInt("likes");
                                        JSONObject user=ob.getJSONObject("user");
                                        String creator_name=user.getString("name");
                                        mItemList.add(new Item(imageUrl,creator_name,likeCount));

                                    }
                                    mItem_adapter=new Item_Adapter(MainActivity.this,mItemList);
                                    mRecyclerView.setAdapter(mItem_adapter);
                                    mItem_adapter.setOnItemClickListener(MainActivity.this);


                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                               //    Toast.makeText(MainActivity.this, "lllll", Toast.LENGTH_SHORT).show();

                            }
                        });
                        mRequestQueue.add(request);
                }
            }
        });
    }
//    private void parseJSON(){
//        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=butterfly&image_type=photo&pretty=true";
//        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray=response.getJSONArray("hits");
//                            for(int i=0;i<jsonArray.length();i++){
//                                JSONObject hit=jsonArray.getJSONObject(i);
//                                String creator_name=hit.getString("user");
//                                String imageUrl=hit.getString("webformatURL");
//                                int likeCount=hit.getInt("likes");
//                                mItemList.add(new Item(imageUrl,creator_name,likeCount));
//
//
//                            }
//                            mItem_adapter=new Item_Adapter(MainActivity.this,mItemList);
//                            mRecyclerView.setAdapter(mItem_adapter);
//                            mItem_adapter.setOnItemClickListener(MainActivity.this);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mRequestQueue.add(request);
//
//    }
    private void parseJSON(){
    String url = "https://api.unsplash.com/photos/?client_id=jHMA7LvLREqKsTGN3xd2ZI3toZOE8JMeeIwlx36brw8";
        final JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(MainActivity.this, "pppp", Toast.LENGTH_SHORT).show();
                try {
                for (int i=0;i<response.length();i++){
                        JSONObject ob=response.getJSONObject(i);
                        JSONObject urls=ob.getJSONObject("urls");
                        String imageUrl=urls.getString("regular");
                        int likeCount=ob.getInt("likes");
                        JSONObject user=ob.getJSONObject("user");
                        String creator_name=user.getString("name");
//                            JSONObject hit=jsonArray.getJSONObject(i);
//                            String creator_name=hit.getString("user");
//                            String imageUrl=hit.getString("webformatURL");
//                            int likeCount=hit.getInt("likes");
                            mItemList.add(new Item(imageUrl,creator_name,likeCount));

                    }
                    mItem_adapter=new Item_Adapter(MainActivity.this,mItemList);
                    mRecyclerView.setAdapter(mItem_adapter);
                    mItem_adapter.setOnItemClickListener(MainActivity.this);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
             //   Toast.makeText(MainActivity.this, "lllll", Toast.LENGTH_SHORT).show();

            }
        });
//    JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
//            new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    try {
//                        JSONArray jsonArray=response.getJSONArray("hits");
//                        for(int i=0;i<jsonArray.length();i++){
//                            JSONObject hit=jsonArray.getJSONObject(i);
//                            String creator_name=hit.getString("user");
//                            String imageUrl=hit.getString("webformatURL");
//                            int likeCount=hit.getInt("likes");
//                            mItemList.add(new Item(imageUrl,creator_name,likeCount));
//
//
//                        }
//                        mItem_adapter=new Item_Adapter(MainActivity.this,mItemList);
//                        mRecyclerView.setAdapter(mItem_adapter);
//                        mItem_adapter.setOnItemClickListener(MainActivity.this);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            error.printStackTrace();
//        }
//    });
    mRequestQueue.add(request);

}

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        Item clickedItem=mItemList.get(position);
        intent.putExtra(EXTRA_URL,clickedItem.getmImageUrl());
        intent.putExtra(EXTRA_CREATOR,clickedItem.getmCreator());
        intent.putExtra(EXTRA_LIKES,clickedItem.getmLikes());
        startActivity(intent);
    }

}