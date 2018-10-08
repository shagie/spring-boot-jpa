package net.shagie.sb.jpa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .profiles("web")    // always web
                .sources(Application.class)
                .run(args);
    }
}
