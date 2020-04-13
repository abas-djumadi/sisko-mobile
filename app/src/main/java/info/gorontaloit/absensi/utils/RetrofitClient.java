package info.gorontaloit.absensi.utils;

import android.app.Application;
import android.util.Log;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import info.gorontaloit.absensi.MainActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static info.gorontaloit.absensi.MainActivity.BASE_URL;

public class RetrofitClient {

    private static SharedPreference sp;
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstance(Application application, @Nullable String uri) {
        sp = new SharedPreference(application.getApplicationContext());
        Log.d("Token Saved", sp.getToken());
        String finalUrl = BASE_URL;
        if (uri != null) {
            finalUrl = finalUrl + uri;
        }
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type","application/x-www-form-urlencoded")
                        .header("Authorization", "Bearer "+sp.getToken())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        return new Retrofit.Builder()
                .baseUrl(finalUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
