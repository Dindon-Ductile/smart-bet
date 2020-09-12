package com.smartbet.demo.api.footballapi.fixtures;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import static com.smartbet.demo.api.retrofit.RetrofitConfiguration.FOOTBALL_API;

@Configuration
public class FootballApiFixturesConfiguration {
    @Bean
    public FootballApiFixturesWebService footballApiFixturesWebService(@Qualifier(FOOTBALL_API) Retrofit retrofit) {
        return retrofit.create(FootballApiFixturesWebService.class);
    }
}
