package cn.liyujie.mvvm.remotedata;

import java.util.Map;

import cn.liyujie.mvvm.remotedata.systemapi.SystemApiService;
import io.reactivex.Observable;


public class Repository {
    private SystemApiService apiCallInterface;

    public Repository(SystemApiService apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public Observable<String> crtVersion(Map<String, Object> paramsa) {
        return apiCallInterface.getcrtVersion(paramsa);
    }



}
