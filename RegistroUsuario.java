package com.example.sqlitecurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitecurso.utilidades.Utilidades;

public class RegistroUsuario extends AppCompatActivity implements View.OnClickListener {

    EditText user_id, user_name, user_telephone;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        user_id = findViewById(R.id.user_id);
        user_name = findViewById(R.id.user_name);
        user_telephone = findViewById(R.id.user_telephone);
        send = findViewById(R.id.send);

        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        registrarusuario();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void registrarusuario() {
        ConexionSQLHelpert con = new ConexionSQLHelpert(this, "db_usuarios", null, 1);
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID, user_id.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, user_name.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, user_telephone.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        Toast.makeText(this, "id Registro:" + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void registrarUsuariosSql() {
        ConexionSQLHelpert conn = new ConexionSQLHelpert(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')

        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO
                + " ( " + Utilidades.CAMPO_ID + "," + Utilidades.CAMPO_NOMBRE + "," + Utilidades.CAMPO_TELEFONO + ")" +
                " VALUES (" + user_id.getText().toString() + ", '" + user_name.getText().toString() + "','"
                + user_telephone.getText().toString() + "')";

        db.execSQL(insert);


        db.close();
    }
}