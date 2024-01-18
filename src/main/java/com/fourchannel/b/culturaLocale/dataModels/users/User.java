package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.Notification;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    private String id=null, username=null, fullName=null, email=null;
    private Date registrationDate=null;
    private PointOfInterestCategory preferredCategory=null;
    private List<Notification> notificationList=null;

}
