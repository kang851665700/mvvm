package cn.liyujie.mvvm.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

import cn.liyujie.mvvm.remotedata.Repository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public MainViewModel(Repository repository) {
        super(repository);
        mText = new MutableLiveData<>();
        mText.setValue("成功");
        Map<String, Object> paramsa = new HashMap<>();
        disposables.add(repository.crtVersion(paramsa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {


                    @Override
                    public void onNext(String s) {
                        mText.setValue(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }


    public LiveData<String> getText() {
        return mText;
    }
}
