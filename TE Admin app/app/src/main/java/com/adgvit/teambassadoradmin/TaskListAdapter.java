package com.adgvit.teambassadoradmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<TaskListObjects>
{
    private List<TaskListObjects> mData;
    private Context context;
    private LayoutInflater layoutInflater;
    private int layoutResource;

    public static class ViewHolder
    {
        TextView taskTextViewRecyclerView1;
        TextView taskTextViewRecyclerView2;
        TextView taskTextViewRecyclerView3;
        CardView taskCardViewRecyclerView;
        ViewHolder (View view)
        {
            this.taskTextViewRecyclerView1=view.findViewById(R.id.taskTextViewRecyclerView1);
            this.taskTextViewRecyclerView2=view.findViewById(R.id.taskTextViewRecyclerView2);
            this.taskTextViewRecyclerView3=view.findViewById(R.id.taskTextViewRecyclerView3);
            this.taskCardViewRecyclerView=view.findViewById(R.id.taskCardViewRecyclerView);
        }
    }

    // data is passed into the constructor
    TaskListAdapter(Context context, int resource, List<TaskListObjects> data)
    {
        super(context, R.layout.tasklist_row_layout);
        this.mData = data;
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
        this.layoutResource=resource;
    }

    @Override
    public int getCount()
    {
        return mData.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent)
    {
        View view=layoutInflater.inflate(layoutResource,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        TaskListObjects data=mData.get(position);
        String Temp1=data.TutorialTitle + " Tutorial";
        viewHolder.taskTextViewRecyclerView1.setText(Temp1);
        viewHolder.taskTextViewRecyclerView2.setText(data.DaysLeft);
        viewHolder.taskTextViewRecyclerView3.setText(data.TutorialTitle);
        viewHolder.taskCardViewRecyclerView.setCardBackgroundColor(data.DrawColor);
        view.setTag(viewHolder);
        return view;
    }
}