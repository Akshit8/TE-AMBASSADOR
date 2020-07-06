package com.adgvit.teambassadoradmin;

import java.net.URL;

public class UserListObjects
{
    String UserName;

    public UserListObjects(String userName, URL userImageUrl) {
        UserName = userName;
        UserImageUrl = userImageUrl;
    }

    URL UserImageUrl;
}
