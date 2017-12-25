package io.github.nullphantom.diagnosajerawat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.github.nullphantom.diagnosajerawat.R;
import io.github.nullphantom.diagnosajerawat.database.DatabaseAccess;

public class Soal extends AppCompatActivity {

    private String id;
    private DatabaseAccess d;
    private TextView soal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        soal = findViewById(R.id.soal);

        Intent i = getIntent();
        id = i.getStringExtra("kode_gejala");

        if(id.substring(0,1).equals("K")) {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            String nextSoal = databaseAccess.getData("kesimpulan",1, id);
            databaseAccess.close();
            Intent in =new Intent(getApplicationContext(),Kesimpulan.class);
            in.putExtra("kesimpulan", nextSoal);
            in.putExtra("id", id);
            startActivity(in);
        }

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String pertanyaan = databaseAccess.getData("gejala",1, id);
        databaseAccess.close();

        soal.setText(pertanyaan);


        Button ya = findViewById(R.id.btnYa);
        Button tidak = findViewById(R.id.btnTidak);

        ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahActivity(1);
            }
        });
        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahActivity(2);
            }
        });
    }

    public void pindahActivity(int j) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String nextSoal = databaseAccess.getData("keputusan",j, id);
        databaseAccess.close();
        Log.e("soal", nextSoal);
        Intent inte =new Intent(getApplicationContext(), Soal.class);
        inte.putExtra("kode_gejala", nextSoal);
        startActivity(inte);
    }
}
