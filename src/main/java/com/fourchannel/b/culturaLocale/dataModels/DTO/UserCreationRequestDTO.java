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
    private String username;
    private String fullName;
    private String email;
    private Date registrationDate;
    private Long townhall;
    private int role;

    public User toUser()
    {
        return new User(this.username, this.fullName, this.email, this.registrationDate);
    }
}
