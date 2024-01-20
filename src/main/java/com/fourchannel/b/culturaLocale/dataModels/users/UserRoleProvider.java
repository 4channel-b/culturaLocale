package com.fourchannel.b.culturaLocale.dataModels.users;


import java.util.Map;

public class UserRoleProvider {
    Map<User, TownHallRole> pairList;
    public UserRoleProvider(){

    }
    public TownHallRole getUserRole(User user){
        return pairList.get(user);
    }
}
