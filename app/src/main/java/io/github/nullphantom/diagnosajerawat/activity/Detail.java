package io.github.nullphantom.diagnosajerawat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import io.github.nullphantom.diagnosajerawat.R;
import io.github.nullphantom.diagnosajerawat.database.DatabaseAccess;

public class Detail extends AppCompatActivity {

    private ListView listView;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView detail = findViewById(R.id.detail);
        this.listView = findViewById(R.id.listView);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        Log.e("idnya adalah :" , id);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String detail_kesimpulan = databaseAccess.getData("kesimpulan",2, id);
        String x = "SELECT * FROM kesimpulan_solusi where kode_kesimpulan_solusi = \"" + id + "\"";
        final List<String> daftar_kode_solusi = databaseAccess.getListData("kesimpulan_solusi",1, id);

        List<String> daftar_solusi = databaseAccess.getListData("solusi",1, daftar_kode_solusi);
        Log.e("kesimpulannya " , daftar_kode_solusi.get(0));
        databaseAccess.close();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar_solusi);
        this.listView.setAdapter(adapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent inte =new Intent(getApplicationContext(),DetailSolusi.class);
                String kode_solusi = daftar_kode_solusi.get(i);
                inte.putExtra("id", kode_solusi);
                inte.putExtra("id_kesimpulan", id);
                startActivity(inte);
            }
        });

        detail.setText(detail_kesimpulan);

    }
    public void goBack(View v) {
        Intent inte =new Intent(getApplicationContext(),Kesimpulan.class);
        inte.putExtra("id", id);
        startActivity(inte);
    }
    public void goHome(View v) {
        Intent inte =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(inte);
    }
}
