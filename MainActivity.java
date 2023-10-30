package com.example.sqlitecurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView insertar, consultar, consultar_spiner, consulta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instancia de la base de datos para crearla al iniciar la actividad principal
        ConexionSQLHelpert con = new ConexionSQLHelpert(this, "db_usuarios", null, 1);

        insertar = findViewById(R.id.insertar);
        consultar = findViewById(R.id.consultar);
        consultar_spiner = findViewById(R.id.consultar_spiner);
        consulta4 = findViewById(R.id.consulta4);

        insertar.setOnClickListener(this);
        consultar.setOnClickListener(this);
        consultar_spiner.setOnClickListener(this);
        consulta4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.insertar) {
            intent = new Intent(getApplicationContext(), RegistroUsuario.class);
            startActivity(intent);
        } else if (view.getId() == R.id.consultar) {
            intent = new Intent(getApplicationContext(), ConsultarUsuario.class);
            startActivity(intent);
        } else if (view.getId() == R.id.consultar_spiner) {
            intent = new Intent(getApplicationContext(), ConsultaCombo.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Actividad" + view.getId(), Toast.LENGTH_SHORT).show();
        }
    }
}