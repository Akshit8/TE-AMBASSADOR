package com.adgvit.teambassadoradmin;

public class UserNodeObjects
{
    private String DOB;
    private String Email;
    private long Level;
    private String Name;
    private String PhoneNo;
    private long Progress;

    public UserNodeObjects(String DOB, String email, long level, String name, String phoneNo,long progress)
    {
        this.DOB = DOB;
        this.Email = email;
        this.Level = level;
        this.Name = name;
        this.PhoneNo = phoneNo;
        this.Progress = progress;
    }

    public UserNodeObjects()
    {
    }


    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getLevel() {
        return Level;
    }

    public void setLevel(long level) {
        Level = level;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public long getProgress() {
        return Progress;
    }

    public void setProgress(long progress) {
        Progress = progress;
    }
}
