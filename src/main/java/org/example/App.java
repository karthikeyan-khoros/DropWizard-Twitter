package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.example.Configuration.AppConfiguration;
import org.example.Configuration.CacheConfiguration;
import org.example.Resources.AppController;
import org.example.Services.AppService;
import org.example.Spring.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class App extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

        super.initialize(bootstrap);
//        bootstrap.addBundle(new AssetsBundle("/assets/html", "/twitter", "index.html"));
//        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
//        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));

        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/html", "/twitter", "index.html"));
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment)  {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfiguration.class);
        ctx.register(CacheConfiguration.class);
        ctx.register(AppService.class);
        ctx.register(AppController.class);
        ctx.refresh();

        AppController resource = ctx.getBean(AppController.class);
        AppService appService = ctx.getBean(AppService.class);
        CacheConfiguration cache = ctx.getBean(CacheConfiguration.class);

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");


        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");


        environment.jersey().register(resource);
    }
}