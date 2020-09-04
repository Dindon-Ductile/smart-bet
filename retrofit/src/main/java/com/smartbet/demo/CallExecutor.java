package com.smartbet.demo;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class CallExecutor {
    public <T> T execute(Call<T> call) throws CallExecutionException, IOException {
        Response<T> response = call.execute();
        if (!response.isSuccessful()) {
            throw CallExecutionException.build(call.request(), response);
        }
        return response.body();
    }
}
