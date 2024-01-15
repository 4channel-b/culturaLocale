package com.fourchannel.b.culturaLocale.dataModels;


import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Contest {
    private Long Id;
    private String name;
    private String description;
    private Date initialDate;
    private Date endDate;
    private String rules;
    private String type;
    private List<Content> contents;

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
    public boolean subscribe(Content content)
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
    public boolean unSubscribe(Content content)
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
