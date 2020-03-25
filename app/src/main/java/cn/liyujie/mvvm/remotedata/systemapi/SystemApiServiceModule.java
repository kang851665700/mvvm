package cn.liyujie.mvvm.remotedata.systemapi;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cn.liyujie.mvvm.remotedata.BaseApiModule;
import cn.liyujie.mvvm.remotedata.Repository;
import cn.liyujie.mvvm.remotedata.ViewModelFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class SystemApiServiceModule extends BaseApiModule {
    private Context context;
    private Retrofit retrofit = null;

    public SystemApiServiceModule(Context context) {
        this.context = context.getApplicationContext();
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.hostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        okHttpClientBuilder.cookieJar(new CookieJar() {
            //这里一定一定一定是HashMap<String, List<Cookie>>,是String,不是url.
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });
        //设置连接超时
        OkHttpClient client = okHttpClientBuilder
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(SYSTEM_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    SystemApiService getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(SystemApiService.class);
    }

    @Provides
    @Singleton
    Repository getRepository(SystemApiService apiCallInterface) {
        return new Repository(apiCallInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(Repository myRepository) {
        return new ViewModelFactory(myRepository);
    }
}
