package com.smartbet.demo.api.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class RetrofitConfiguration {
    public static final String FOOTBALL_API = "footballApi";

    @Bean(name = FOOTBALL_API)
    public Retrofit buildFootballApiRetrofit(FootballApiConfiguration footballApiConfiguration) {
        OkHttpClient okHttpClient = buildApiFootballOkHttpClient(footballApiConfiguration);
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api-football-v1.p.rapidapi.com/v2/")
                .addConverterFactory(JacksonConverterFactory.create(buildObjectMapper()))
                .build();
    }

    private OkHttpClient buildApiFootballOkHttpClient(FootballApiConfiguration footballApiConfiguration) {
        return new OkHttpClient.Builder()
                .addInterceptor(FootballApiInterceptor(footballApiConfiguration))
                .build();
    }

    private Interceptor FootballApiInterceptor(FootballApiConfiguration footballApiConfiguration) {
        return chain -> {
            Request originalRequest = chain.request();
            Request requestWithCredentials = originalRequest.newBuilder()
                    .header("x-rapidapi-host", footballApiConfiguration.getHost())
                    .header("x-rapidapi-key", footballApiConfiguration.getKey())
                    .build();
            return chain.proceed(requestWithCredentials);
        };
    }

    private ObjectMapper buildObjectMapper() {
        return new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new GuavaModule())
                .registerModule(new Jdk8Module());
    }
}
