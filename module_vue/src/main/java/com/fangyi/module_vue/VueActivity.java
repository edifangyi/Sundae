package com.fangyi.module_vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangyi.component_library.config.AppPahts;

@Route(path = AppPahts.VUE_ACTIVITY_PAHT)
public class VueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue);
    }
}
