package br.unicamp.students.Retrofit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3333")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Service getService() {
        return this.retrofit.create(Service.class);
    }
}
