package com.adgvit.teambassadoradmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private int position = 0;
    private LinearLayout userListView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        final List<UserNodeObjects> userList=new ArrayList<>();
        final List<UserListObjects> adapterList=new ArrayList<>();
        final UserListAdapter adapter = new UserListAdapter(getApplicationContext(), R.layout.userlist_row_layout,adapterList);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userListView=findViewById(R.id.userRecyclerView);
        userRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(userListView.getChildCount() > 0)
                {
                    userListView.removeAllViews();
                }
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    //key
                    final String key=ds.getKey();

                    //UserNodeList
                    System.out.println(ds.getValue());
                    final UserNodeObjects tempdat=ds.getValue(UserNodeObjects.class);
                    userList.add(tempdat);
                    assert tempdat != null;

                    //AdapterList
                    final UserListObjects listdata=new UserListObjects(tempdat.getName(),null);
                    adapterList.add(listdata);

                    //Adapter
                    final View item = adapter.getView(position++, null, null);
                    item.setTag(position);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {
                            Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                            intent.putExtra("UserID",key);
                            intent.putExtra("Name",tempdat.getName());
                            intent.putExtra("Level",tempdat.getLevel());
                            intent.putExtra("UserProgress",tempdat.getProgress());
                            startActivity(intent);
                        }
                    });
                    userListView.addView(item);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });
    }
}
