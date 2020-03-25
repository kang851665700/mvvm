package cn.liyujie.mvvm.remotedata;


import javax.inject.Singleton;

import cn.liyujie.mvvm.remotedata.systemapi.SystemApiServiceModule;
import cn.liyujie.mvvm.ui.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SystemApiServiceModule.class})
public interface AppComponent {

    void doInjectionMain(MainActivity mainActivity);
}
