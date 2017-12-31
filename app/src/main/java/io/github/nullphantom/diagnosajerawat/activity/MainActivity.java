package io.github.nullphantom.diagnosajerawat.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    public void about(View v) {

        Intent i =new Intent(getApplicationContext(),About.class);
        i.putExtra("kode_gejala","G15");
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Yakin ingin keluar?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
