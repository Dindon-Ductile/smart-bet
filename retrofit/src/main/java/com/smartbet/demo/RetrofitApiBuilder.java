package com.smartbet.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbet.demo.objectmapping.ObjectMapperProvider;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.function.Function;

import static java.util.function.Function.identity;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;


@Service
public class RetrofitApiBuilder {
    private final ObjectMapperProvider objectMapperProvider;

    public RetrofitApiBuilder(ObjectMapperProvider objectMapperProvider) {
        this.objectMapperProvider = objectMapperProvider;
    }

    public static class RetrofitBuilder {
        private OkHttpClient.Builder okHttpClientBuilder;
        private Retrofit.Builder retrofitBuilder;

        private RetrofitBuilder(OkHttpClient.Builder okHttpClientBuilder, Retrofit.Builder retrofitBuilder) {
            this.okHttpClientBuilder = okHttpClientBuilder;
            this.retrofitBuilder = retrofitBuilder;
        }

        public RetrofitBuilder configureOkHttpClient(Function<OkHttpClient.Builder, OkHttpClient.Builder> configurer) {
            okHttpClientBuilder = configurer.apply(okHttpClientBuilder);
            return this;
        }

        private Retrofit build() {
            return retrofitBuilder.client(okHttpClientBuilder.build())
                    .build();
        }
    }

    public <ApiClass> ApiClass create(Class<ApiClass> apiClass, String baseUrl) {
        return create(apiClass, baseUrl, identity());
    }

    public <ApiClass> ApiClass create(Class<ApiClass> apiClass, String baseUrl,
                                      Function<RetrofitBuilder, RetrofitBuilder> retrofitBuilderConfigurer) {
        RetrofitBuilder retrofitBuilder = buildRetrofitBuilder(baseUrl);
        retrofitBuilder = retrofitBuilderConfigurer.apply(retrofitBuilder);
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(apiClass);
    }

    private RetrofitBuilder buildRetrofitBuilder(String baseUrl) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(buildRetrofitObjectMapper()));
        return new RetrofitBuilder(okHttpClientBuilder, retrofitBuilder);
    }

    private ObjectMapper buildRetrofitObjectMapper() {
        return objectMapperProvider.buildObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}

