package io.futurestud.retrofit1.api.proxy;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProxyBuilder {
    private static final String SERVER_URL = "http://10.0.2.2:8080/";


    public static WGServerProxy getProxy() {
        // Build Retrofit proxy object for server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WGServerProxy.class);
    }



    /**
     * Interface for simplifying the callbacks from the server.
     */
    public interface SimpleCallback<T> {
        void callback(T ans);
    };


    /**
     * Simplify the calling of the "Call"
     * - Handle error checking in one place and put up toast & log on failure.
     * - Callback to simplified interface on success.
     * @param context   Current activity for showing toast if there's an error.
     * @param caller    Call object returned by the proxy
     * @param callback  Client-code to execute when we have a good answer for them.
     * @param <T>       The type of data that Call object is expected to fetch
     */
    public static <T extends Object> void callProxy(final Context context, Call<T> caller, final SimpleCallback<T> callback) {
        caller.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {

                // Process the response
                if (response.errorBody() == null) {
//                    // Check for authentication token:
//                    String tokenInHeader = response.headers().get("Authorization");
//                    if (tokenInHeader != null) {
//                        if (receivedTokenCallback != null) {
//                            receivedTokenCallback.callback(tokenInHeader);
//                        } else {
//                            // We got the token, but nobody wanted it!
//                            Log.w("ProxyBuilder", "WARNING: Received token but no callback registered for it!");
//                        }
//                    }

                    if (callback != null) {
                        T body = response.body();
                        callback.callback(body);
                    }
                } else {
                    String message;
                    try {
                        int code = response.code();
                        message = "CALL TO SERVER FAILED:\n" + response.errorBody().string() + "\n"
                                + "With HTTP code: " + code;
                    } catch (IOException e) {
                        e.printStackTrace();
                        message = "Unable to decode response (body or error's body).";
                    }
                    showFailure(message);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                String message = "Error communicating with server: " + t.getMessage();
                showFailure(message);
            }
            private void showFailure(String message) {
                Log.e("ProxyBuilder", message);
                if (context != null) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
