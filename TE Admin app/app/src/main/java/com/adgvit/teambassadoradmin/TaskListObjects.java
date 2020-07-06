package com.adgvit.teambassadoradmin;

public class TaskListObjects
{
    String TutorialTitle;
    String DaysLeft;
    int DrawColor;

    public TaskListObjects(String homeTutorialTitle, String homeDaysLeft,int homeColor)
    {
        TutorialTitle = homeTutorialTitle;
        DaysLeft = homeDaysLeft;
        DrawColor=homeColor;
    }
}
