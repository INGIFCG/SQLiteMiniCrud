package com.example.sqlitecurso;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitecurso.entidades.Usuario;
import com.example.sqlitecurso.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaCombo extends AppCompatActivity {

    Spinner comboPersonas;
    TextView txtNombre, txtDocumento, txtTelefono;
    ArrayList<String> listapersonas;
    ArrayList<Usuario> personaLists;
    ConexionSQLHelpert con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);
        con = new ConexionSQLHelpert(getApplicationContext(), "db_usuarios", null, 1);

        comboPersonas = findViewById(R.id.comboPersonas);
        txtDocumento = findViewById(R.id.txtDocumento);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);

        consultarListaPersonas();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listapersonas);
        comboPersonas.setAdapter(adapter);


    }

    private void consultarListaPersonas() {

        SQLiteDatabase db = con.getReadableDatabase();

        Usuario persona = null;
        personaLists = new ArrayList<Usuario>();
        //SELECT *FROM usuario
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);
        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            personaLists.add(persona);
        }
        construirLista();
    }

    private void construirLista() {
        listapersonas = new ArrayList<String>();
        listapersonas.add("Seleccione");
        for (int i = 0; i < personaLists.size(); i++) {
            listapersonas.add(personaLists.get(i).getId() + " - " + personaLists.get(i).getNombre());
        }
    }
}