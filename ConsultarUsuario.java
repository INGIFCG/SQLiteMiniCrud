package com.example.sqlitecurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitecurso.utilidades.Utilidades;

public class ConsultarUsuario extends AppCompatActivity implements View.OnClickListener {

    Button buscar_btn, eliminar_btn, actualizar_btn;

    EditText documento, nombre, telefono;

    ConexionSQLHelpert con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        documento = findViewById(R.id.documento);

        con = new ConexionSQLHelpert(getApplicationContext(), "db_usuarios", null, 1);

        nombre = findViewById(R.id.nombre);
        telefono = findViewById(R.id.telefono);
        buscar_btn = findViewById(R.id.buscar_btn);
        eliminar_btn = findViewById(R.id.eliminar_btn);
        actualizar_btn = findViewById(R.id.actualizar_btn);

        buscar_btn.setOnClickListener(this);
        eliminar_btn.setOnClickListener(this);
        actualizar_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buscar_btn) {
            consultar();
        } else if (view.getId() == R.id.eliminar_btn) {
            eliminarUsuario();
        } else if (view.getId() == R.id.actualizar_btn) {
            actualizarUsuario();
        }
    }

    private void consultar() {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] parametros = {documento.getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            telefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            telefono.setText("");

        }

    }

    private void consultarSql() {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] parametros = {documento.getText().toString()};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_NOMBRE + "," + Utilidades.CAMPO_TELEFONO +
                    " FROM " + Utilidades.TABLA_USUARIO + " WHERE " + Utilidades.CAMPO_ID + "=? ", parametros);

            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            telefono.setText(cursor.getString(1));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            nombre.setText("");
            telefono.setText("");
        }
    }

    private void eliminarUsuario() {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {documento.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Ya se Eliminó el usuario", Toast.LENGTH_LONG).show();
        documento.setText("");
        nombre.setText("");
        telefono.setText("");
        db.close();
    }

    private void actualizarUsuario() {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {documento.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, nombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, telefono.getText().toString());

        db.update(Utilidades.TABLA_USUARIO, values, Utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Ya se actualizó el usuario", Toast.LENGTH_LONG).show();
        db.close();

    }
}