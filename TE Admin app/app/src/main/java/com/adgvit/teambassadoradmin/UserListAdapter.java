package com.adgvit.teambassadoradmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<UserListObjects>
{
    private List<UserListObjects> userlist;
    private Context context;
    private LayoutInflater layoutInflater;
    private int layoutResource;

    public static class ViewHolder
    {
        TextView userTextViewRecyclerView;
        ImageView userImageViewRecyclerView;
        ViewHolder (View view)
        {
            this.userTextViewRecyclerView=view.findViewById(R.id.userTextViewRecyclerView);
            this.userImageViewRecyclerView=view.findViewById(R.id.userImageViewRecyclerView);
        }
    }

    public UserListAdapter(@NonNull Context context, int resource, List<UserListObjects> templist)
    {
        super(context,R.layout.userlist_row_layout);
        this.context=context;
        this.userlist=templist;
        this.layoutInflater=LayoutInflater.from(context);
        this.layoutResource=resource;
    }

    @Override
    public int getCount()
    {
        return userlist.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent)
    {
        View view=layoutInflater.inflate(layoutResource,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        UserListObjects tempdata=userlist.get(position);
        viewHolder.userTextViewRecyclerView.setText(tempdata.UserName);
        if(tempdata.UserImageUrl!=null)
        {
            Glide.with(context).load(tempdata.UserImageUrl).into(viewHolder.userImageViewRecyclerView);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder.userImageViewRecyclerView.setImageResource(R.mipmap.ic_user);
        }
        return view;
    }
}
