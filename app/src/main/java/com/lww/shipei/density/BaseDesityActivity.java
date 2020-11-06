package com.lww.shipei.density;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BaseDesityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        必须在setContentView  之前  或者在applicatio 中处理
//        DesityUtils.setDensity(getApplication(), this);
    }
}