package io.github.nullphantom.diagnosajerawat.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.github.nullphantom.diagnosajerawat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView judul = findViewById(R.id.judul);

        Typeface bebas = Typeface.createFromAsset(getAssets(),  "fonts/BebasNeue.otf");

        judul.setTypeface(bebas);
    }

    public void mulai(View v) {

        Intent i =new Intent(getApplicationContext(),Soal.class);
        i.putExtra("kode_gejala","G15");
        startActivity(i);

    }
}
