package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="TownHallRole")
public class TownHallRoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "townhallRole_id_seq")
    private Long id;
    @ManyToOne
    private TownHall townHall;
    private Role role;
    @ManyToOne
    private User user;
}
