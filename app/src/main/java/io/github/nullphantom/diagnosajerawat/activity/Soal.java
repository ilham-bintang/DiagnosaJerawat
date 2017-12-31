package io.github.nullphantom.diagnosajerawat.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.github.nullphantom.diagnosajerawat.R;
import io.github.nullphantom.diagnosajerawat.database.DatabaseAccess;

public class Soal extends AppCompatActivity {

    private String id, prev;
    private DatabaseAccess d;
    private TextView soal, judul;
    private ImageView g;
    int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);


        g = findViewById(R.id.gambar);
        soal = findViewById(R.id.soal);
        judul = findViewById(R.id.judul);

        Intent i = getIntent();
        id = i.getStringExtra("kode_gejala");
        prev = i.getStringExtra("prev");
        count = i.getIntExtra("hitung",1);
        //kalau kesimpulan, pindah ke activity kesimpulan
        if(id.substring(0,1).equals("K")) {
            Intent in =new Intent(getApplicationContext(),Kesimpulan.class);
            in.putExtra("id", id);
            startActivity(in);
        }

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String pertanyaan = databaseAccess.getData("gejala",1, id);
        String gambar = databaseAccess.getData("gejala",2,id);
        databaseAccess.close();

        Typeface font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Regular.ttf");
        soal.setTypeface(font);
        soal.setText(pertanyaan);

        g.setImageResource(getImageId(this, gambar));

        judul.setText("Soal "+count);


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
        Intent inte =new Intent(getApplicationContext(), Soal.class);
        inte.putExtra("kode_gejala", nextSoal);
        inte.putExtra("prev", id);
        inte.putExtra("hitung", count+1);
        startActivity(inte);
    }

    @Override
    public void onBackPressed() {
        if (id.equals("G15")) {
            Intent inte = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(inte);
        } else {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            String nextSoal = databaseAccess.getData("keputusan",0, prev);
            databaseAccess.close();
            Intent inte =new Intent(getApplicationContext(), Soal.class);
            inte.putExtra("kode_gejala", nextSoal);
            inte.putExtra("prev", id);
            inte.putExtra("hitung", count-1);
            startActivity(inte);
        }
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
