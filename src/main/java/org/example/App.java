package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;

import org.example.Configuration.AppConfiguration;
import org.example.Resources.AppController;
import org.example.Spring.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

        super.initialize(bootstrap);
        bootstrap.addBundle(new AssetsBundle("/assets/","/"));

    }

    @Override
    public void run(AppConfiguration configuration, Environment environment)  {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        final AppController resource = context.getBean(AppController.class);

        environment.jersey().register(resource);
    }
}