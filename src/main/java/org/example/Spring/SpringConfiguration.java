package org.example.Spring;

import org.example.Resources.AppController;
import org.example.Services.AppService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
public class SpringConfiguration {

    @Bean
    public AppService appService()
    {
        return new AppService();
    }

    @Bean
    public AppController appController()
    {
        AppController controller = new AppController();

        controller.setAppService(appService());
        return controller;
    }

}
