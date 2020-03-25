package cn.liyujie.mvvm.ui;

import androidx.lifecycle.ViewModel;

import cn.liyujie.mvvm.remotedata.Repository;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    public Repository repository;
    public CompositeDisposable disposables = new CompositeDisposable();

    public BaseViewModel(Repository repository) {
        this.repository = repository;
    }
}
