package cn.liyujie.mvvm.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import javax.inject.Inject;

import cn.liyujie.mvvm.BaseApplication;
import cn.liyujie.mvvm.R;
import cn.liyujie.mvvm.databinding.ActivityMainBinding;
import cn.liyujie.mvvm.remotedata.ViewModelFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Inject
    public ViewModelFactory viewModelFactory;

    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().getAppComponent().doInjectionMain(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);
        mainViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.mainText.setText(s);
            }
        });
    }
}
