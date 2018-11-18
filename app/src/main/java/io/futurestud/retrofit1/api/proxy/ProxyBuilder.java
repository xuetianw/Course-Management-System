package io.futurestud.retrofit1.api.proxy;

import io.futurestud.retrofit1.api.service.GitHubClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProxyBuilder {
    private static final String SERVER_URL = "http://10.0.2.2:8080/";


    public static WGServerProxy getProxy() {
        // Build Retrofit proxy object for server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WGServerProxy.class);
    }
}
