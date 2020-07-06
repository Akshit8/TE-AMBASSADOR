package com.adgvit.teambassadoradmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wang.avi.AVLoadingIndicatorView;

public class ViewTaskActivity extends AppCompatActivity {

    //String userName = "Akshit Sadana";
    //String taskName  = "cpp";
    //String days = "40";

    private TextView userNameTextView, daysLeftTextView, taskNameTextView, taskStatusTextView;
    private Button acceptButton, rejectButton;
    private String daysLeft,folderName,imgName,userName,userMail,taskName,taskStatus;
    private int temp;
    private long userProgress,userLevel,taskXp;
    private ImageView taskImageView;
    private StorageReference storage,storageRef;
    private DatabaseReference dataBase;
    private AVLoadingIndicatorView avi;
    private boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        taskName = getIntent().getStringExtra("TaskName");
        taskStatus = getIntent().getStringExtra("TaskStatus");
        userMail = getIntent().getStringExtra("UserId");
        userName = getIntent().getStringExtra("Name");
        userLevel = getIntent().getLongExtra("Level",1);
        userProgress = getIntent().getLongExtra("UserProgress",0);
        taskXp = getIntent().getLongExtra("Points",0);
        daysLeft = getIntent().getStringExtra("DaysLeft");


        dataBase = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance().getReference();

        userNameTextView = findViewById(R.id.userNameTextView);
        taskStatusTextView = findViewById(R.id.statusTextView);
        daysLeftTextView = findViewById(R.id.daysLeftTextView);
        taskNameTextView = findViewById(R.id.taskNameTextView);
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);
        taskImageView = findViewById(R.id.taskImageView);
        avi = findViewById(R.id.avi);

        userNameTextView.setText(userName);

        /*daysLeft = days+" days left";*/
        daysLeftTextView.setText(daysLeft);

        setTaskStatus();

        taskNameTextView.setText(taskName);

        folderName = userMail + "/";
        imgName = userMail + "_" + taskName + ".jpg";
        //Toast.makeText(this, imgName, Toast.LENGTH_SHORT).show();
        getTaskImage(folderName,imgName);


        acceptButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    if(temp == 0) {
                        try {
                            dataBase.child("Task")
                                    .child(userMail)
                                    .child(taskName)
                                    .child("Status").setValue("Accepted");
                        } catch (Exception e) {
                            Toast.makeText(ViewTaskActivity.this, "Database access failed", Toast.LENGTH_SHORT).show();
                        }
                        userProgress = userProgress + taskXp;
                        setLevel();
                        try {
                            dataBase.child("Users")
                                    .child(userMail)
                                    .child("Level").setValue(userLevel);
                            dataBase.child("Users")
                                    .child(userMail)
                                    .child("Progress").setValue(userProgress);


                        } catch (Exception e) {
                            Toast.makeText(ViewTaskActivity.this, "Database access failed", Toast.LENGTH_SHORT).show();
                        }
                        taskStatusTextView.setText("Accepted ");
                        taskStatusTextView.setTextColor(getColor(R.color.colorGreen));
                        temp = 1;
                    }else if(temp == 1){

                        Toast.makeText(ViewTaskActivity.this, "Task already accepted", Toast.LENGTH_SHORT).show();
                    }else if(temp == 2){

                        Toast.makeText(ViewTaskActivity.this, "Task already rejected", Toast.LENGTH_SHORT).show();
                    }else if(temp==3){

                        Toast.makeText(ViewTaskActivity.this, "Task Yet to upload", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(temp == 0) {
                    try {
                        dataBase.child("Task")
                                .child(userMail)
                                .child(taskName)
                                .child("Status").setValue("Rejected");
                    } catch (Exception e) {
                        Toast.makeText(ViewTaskActivity.this, "Database access failed", Toast.LENGTH_SHORT).show();
                    }
                    taskStatusTextView.setText("Rejected ");
                    taskStatusTextView.setTextColor(getColor(R.color.colorRed));
                    temp = 2;
                }else if(temp == 1){

                    Toast.makeText(ViewTaskActivity.this, "Task already accepted", Toast.LENGTH_SHORT).show();
                }else if(temp == 2){

                    Toast.makeText(ViewTaskActivity.this, "Task already rejected", Toast.LENGTH_SHORT).show();
                }else if(temp == 3){

                    Toast.makeText(ViewTaskActivity.this, "Task Yet to Upload", Toast.LENGTH_SHORT).show();
                }
            }
        });
        taskImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    taskImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    taskImageView.setAdjustViewBounds(true);
                    //Toast.makeText(ViewTaskActivity.this, "if", Toast.LENGTH_SHORT).show();
                }else{
                    isImageFitToScreen=true;
                    taskImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    taskImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //Toast.makeText(ViewTaskActivity.this, "else", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
    void getTaskImage(String folderName, String imgName)
    {
        storageRef = storage.child(folderName).child(imgName);
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                //Toast.makeText(ViewTaskActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
                Glide.with(getApplicationContext())
                        .load(uri)
                        .into(taskImageView);

                avi.smoothToHide();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(ViewTaskActivity.this, "Image for the task not uploaded", Toast.LENGTH_LONG).show();
                avi.smoothToHide();

            }
        });
    }
    void setLevel()
    {
        long pTotal = userLevel * 1000;
        if((userProgress-pTotal) > 0 && userLevel <= 10){

            userLevel += 1;
        }

    }
    void setTaskStatus()
    {
        if(taskStatus.equals("Pending for Approval")){

            taskStatusTextView.setText("Pending for Approval ");
            taskStatusTextView.setTextColor(getColor(R.color.colorYellow));
            temp = 0;
        }
        if(taskStatus.equals("Accepted")){

            taskStatusTextView.setText("Task Accepted ");
            taskStatusTextView.setTextColor(getColor(R.color.colorGreen));
            temp = 1;
        }
        if(taskStatus.equals("Rejected")){

            taskStatusTextView.setText("Task Rejected ");
            taskStatusTextView.setTextColor(getColor(R.color.colorRed));
            temp = 2;
        }
        if(taskStatus.equals("Yet to Upload"))
        {
            taskStatusTextView.setText("Yet to Upload ");
            taskStatusTextView.setTextColor(getColor(R.color.colorBlue));
            temp = 3;
        }


    }
}
