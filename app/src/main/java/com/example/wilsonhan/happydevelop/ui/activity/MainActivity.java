package com.example.wilsonhan.happydevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.security.Md5Security;
import com.example.wilsonhan.happydevelop.R;

/**
 * Created by ggg on 2018/8/11.
 */

public class MainActivity extends AppCompatActivity {
    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_test=findViewById(R.id.tv_test);
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result=Md5Security.getMD5("0FECC6AC302AF97E41534830156060239792f28d6ff1f34ec702c08626d454b39");
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
