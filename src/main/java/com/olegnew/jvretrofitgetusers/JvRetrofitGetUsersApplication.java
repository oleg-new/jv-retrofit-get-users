package com.olegnew.jvretrofitgetusers;

import com.olegnew.jvretrofitgetusers.service.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JvRetrofitGetUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvRetrofitGetUsersApplication.class, args);
        Parser parser = new Parser();
        System.out.println(parser.getUsers());
    }

}
