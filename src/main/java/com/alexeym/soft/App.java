package com.alexeym.soft;

import com.alexeym.soft.model.PotatoBag;
import com.alexeym.soft.storage.PotatoStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.time.Instant;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */

@SpringBootApplication
public class App  {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
        PotatoStorage storage = ctx.getBean(PotatoStorage.class);
        storage.add(new PotatoBag("0-bag", 42, "Owel", Instant.now(), 99));
    }

}
