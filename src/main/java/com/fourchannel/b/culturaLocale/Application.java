package com.fourchannel.b.culturaLocale;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })  //momentaneamente bloccata la ricerca di un datasource
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
