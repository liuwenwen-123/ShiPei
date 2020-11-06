package com.lww.shipei;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lww.shipei.PX_SHIPEI.PxActivity;
import com.lww.shipei.baifenbi.BaiFenBiMainActivity;
import com.lww.shipei.baifenbi.MyPercentActivity;
import com.lww.shipei.density.DestityActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void px(View view) {
        startActivity(new Intent(this, PxActivity.class));
    }

    public void baifenbi(View view) {

//        startActivity(new Intent(this, BaiFenBiMainActivity.class));
        startActivity(new Intent(this, MyPercentActivity.class));
    }

    public void desity(View view) {
        startActivity(new Intent(this, DestityActivity.class));
    }
}