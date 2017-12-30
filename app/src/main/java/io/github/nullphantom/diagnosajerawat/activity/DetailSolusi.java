package io.github.nullphantom.diagnosajerawat.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import io.github.nullphantom.diagnosajerawat.R;
import io.github.nullphantom.diagnosajerawat.database.DatabaseAccess;

public class DetailSolusi extends AppCompatActivity {
    String id_kes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_solusi);

        TextView detailsolusi = findViewById(R.id.detailSolusi);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        id_kes = i.getStringExtra("id_kesimpulan");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
            String detail_solusi = databaseAccess.getData("solusi",2, id);
        databaseAccess.close();

        Typeface font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Regular.ttf");
        detailsolusi.setTypeface(font);
        detailsolusi.setText(detail_solusi);
    }
    public void goHome(View v) {
        Intent inte =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(inte);
    }
    public void goBack(View v) {
        Intent inte =new Intent(getApplicationContext(),Detail.class);
        inte.putExtra("id", id_kes);
        startActivity(inte);
    }

    @Override
    public void onBackPressed() {
        Intent inte =new Intent(getApplicationContext(),Detail.class);
        inte.putExtra("id", id_kes);
        startActivity(inte);
    }
}
