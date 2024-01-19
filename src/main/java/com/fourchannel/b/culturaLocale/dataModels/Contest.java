package com.fourchannel.b.culturaLocale.dataModels;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="contest")
public class Contest
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contest_id_seq")
    private Long id;
    private String name;
    private String description;
    private Date initialDate;
    private Date endDate;
    private String rules;
    private String type;
    @OneToMany
    private List<PointOfInterest> contents;

    public String getType() {
        return type;
    }

    //add content in given list

    /**
     * Add the given content in a specific list.
     *
     * @param content
     * @return true if anything went well
     */
    public boolean subscribe(PointOfInterest content)
    {
        if(content == null)
        {
            throw new NullPointerException("| ERROR | Content cannot be null :(");
        }
        else
        {
            return this.contents.add(content);
        }
    }

    /**
     * Remove the given content in a specific list.
     *
     * @param content
     * @return
     */
    public boolean unSubscribe(PointOfInterest content)
    {
        if(content == null)
        {
            throw new NullPointerException("| ERROR | Content cannot be null :(");
        }
        else
        {
            return this.contents.remove(content);
        }
    }

    //Notify the contest's result to every user that has applied
    //TODO Da implementare
    public void notifyAll(Content winner)
    {}
}
