package io.github.nullphantom.diagnosajerawat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.github.nullphantom.diagnosajerawat.R;
import io.github.nullphantom.diagnosajerawat.database.DatabaseAccess;

public class Kesimpulan extends AppCompatActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesimpulan);

        TextView simpulan = (findViewById(R.id.kesimpulan));

        Intent i = getIntent();
        String kesimpulan = i.getStringExtra("kesimpulan");
        id = i.getStringExtra("id");

        simpulan.setText(kesimpulan);
    }
    public void goHome(View v) {
        Intent inte = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(inte);
    }
    public void goDetail(View v) {
        Log.e("sudah di detail", "iya sudah");
        Intent inte = new Intent(getApplicationContext(),Detail.class);

        Log.e("Kode kesimpulan : " , id);

        inte.putExtra("id", id);
        startActivity(inte);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Harus mulai dari awal!",Toast.LENGTH_LONG);
    }
}