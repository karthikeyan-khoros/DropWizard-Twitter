package org.example;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.Resources.AppController;
import org.example.Spring.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(Configuration configuration, Environment environment)  {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        final AppController resource = context.getBean(AppController.class);

        environment.jersey().register(new AppController());
    }
}