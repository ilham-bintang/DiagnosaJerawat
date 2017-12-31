package io.github.nullphantom.diagnosajerawat.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.github.nullphantom.diagnosajerawat.R;
import io.github.nullphantom.diagnosajerawat.database.DatabaseAccess;

public class Kesimpulan extends AppCompatActivity {
//contoh commit
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesimpulan);

        TextView simpulan = (findViewById(R.id.kesimpulan));
        TextView judul = (findViewById(R.id.judul_kesimpulan));
        ImageView g = findViewById(R.id.gambar);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        String kesimpulan = databaseAccess.getData("kesimpulan",1, id);
        String gambar = databaseAccess.getData("kesimpulan",3, id);
        databaseAccess.close();

        Typeface font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Regular.ttf");
        judul.setTypeface(font);
        g.setImageResource(getImageId(this, gambar));
        simpulan.setTypeface(font);
        simpulan.setText(kesimpulan);
    }
    public void goHome(View v) {
        Intent inte = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(inte);
    }
    @SuppressLint("ShowToast")
    public void goDetail(View v) {
        if (id.equals("K09")) {
            Toast.makeText(this,"Anda tidak sakit jerawat",Toast.LENGTH_SHORT).show();
        } else {
            Intent inte = new Intent(getApplicationContext(), Detail.class);
            inte.putExtra("id", id);
            startActivity(inte);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Harus mulai dari awal!",Toast.LENGTH_SHORT).show();
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}