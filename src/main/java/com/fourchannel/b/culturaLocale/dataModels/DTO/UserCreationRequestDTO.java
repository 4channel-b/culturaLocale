package com.fourchannel.b.culturaLocale.dataModels.DTO;

import com.fourchannel.b.culturaLocale.dataModels.users.User;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserCreationRequestDTO
{
    //private Long Id;
    private String username=null;
    private String fullName=null, email=null;
    private Date registrationDate=null;
    private Long townhall;
    private int role;

    public User toUser()
    {
        return new User(this.username, this.fullName, this.email, this.registrationDate);
    }
}
