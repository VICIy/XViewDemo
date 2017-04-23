package com.viciy.xviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XView viewById = (XView) findViewById(R.id.xv_1);
        viewById.setPos(1);
        viewById.invalidate();
        XView viewById2 = (XView) findViewById(R.id.xv_2);
        viewById2.setPos(2);
        viewById2.invalidate();
        XView viewById3 = (XView) findViewById(R.id.xv_3);
        viewById3.setPos(3);
        viewById3.invalidate();
    }
}
