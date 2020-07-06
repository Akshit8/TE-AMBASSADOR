package com.adgvit.teambassadoradmin;

public class TaskNodeObjects
{
    private String Description;
    private String Status;
    private String DaysLeft;
    private String ImageName;
    private long Points;

    public TaskNodeObjects(String description, String status, String daysLeft, String imageName, long points)
    {
        Description = description;
        Status = status;
        DaysLeft = daysLeft;
        ImageName = imageName;
        Points = points;
    }

    public TaskNodeObjects()
    {
    }

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDaysLeft() {
        return DaysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        DaysLeft = daysLeft;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public long getPoints(){ return Points;}

    public void setPoints(long points) { Points = points; }
}
