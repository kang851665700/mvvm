package cn.liyujie.mvvm.remotedata.systemapi;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SystemApiService {
    //IOS控制登录类型
    @GET("mobile/v/crtVersion")
    Observable<String> getcrtVersion(@QueryMap Map<String, Object> params);
}
