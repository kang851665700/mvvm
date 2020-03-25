package cn.liyujie.mvvm;

import android.app.Application;

import cn.liyujie.mvvm.remotedata.AppComponent;
import cn.liyujie.mvvm.remotedata.AppModule;
import cn.liyujie.mvvm.remotedata.DaggerAppComponent;
import cn.liyujie.mvvm.remotedata.systemapi.SystemApiServiceModule;

public class BaseApplication extends Application {


    private static BaseApplication application;
    // 单例模式中获取唯一的MyApplication实例
    public static BaseApplication getInstance() {
        return application;
    }

    private AppComponent appComponent;
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initializeComponent();
    }

    private void initializeComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).systemApiServiceModule(new SystemApiServiceModule(this)).build();
    }
}
