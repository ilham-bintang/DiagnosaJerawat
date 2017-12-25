package io.github.nullphantom.diagnosajerawat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.nullphantom.diagnosajerawat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void mulai(View v) {
        Intent i =new Intent(getApplicationContext(),Soal.class);
        i.putExtra("kode_gejala","G15");
        startActivity(i);
    }
}
