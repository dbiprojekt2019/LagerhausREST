package com.dbiprojekt.lagerhausrest;

import com.dbiprojekt.lagerhausrest.manager.database.CollumnsSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean(name = "collumns")
    public CollumnsSingleton createCollumnSingleton() {return new CollumnsSingleton();}
}
