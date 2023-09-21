package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlitetest.Entidades.ConexionSqliteHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button register_btn, select_btn, select_spiner_btn, select_listview_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSqliteHelper con = new ConexionSqliteHelper(this, "db_usuarios", null, 1);

        register_btn = findViewById(R.id.register_btn);
        select_btn = findViewById(R.id.select_btn);
        select_spiner_btn = findViewById(R.id.select_spiner_btn);
        select_listview_btn = findViewById(R.id.select_listview_btn);

        register_btn.setOnClickListener(this);
        select_btn.setOnClickListener(this);
        select_spiner_btn.setOnClickListener(this);
        select_listview_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_btn) {
            Intent intent = new Intent(this, RegistroUsuariosActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.select_btn){
            Intent intent = new Intent(this, ConsultarUsuarios.class);
            startActivity(intent);
        }else if(view.getId() == R.id.select_spiner_btn){
            Toast.makeText(this, "R.id.select_spiner_btn ", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, RegistroUsuariosActivity.class);
//            startActivity(intent);
        }else if(view.getId() == R.id.select_listview_btn){
            Toast.makeText(this, "R.id.select_listview_btn", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, RegistroUsuariosActivity.class);
//            startActivity(intent);
           }
    }
}