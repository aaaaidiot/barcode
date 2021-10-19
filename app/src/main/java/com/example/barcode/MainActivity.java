package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ZXingのインスタンス作成
        IntentIntegrator integrator = new IntentIntegrator(this);
        //横から縦向きに,ここからZxingの設定が行える
        // 詳細は→https://github.com/journeyapps/zxing-android-embedded
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        TextView textView = findViewById(R.id.code);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                //読み取った値がresult.getContents()になる
                textView.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }



    }
}
