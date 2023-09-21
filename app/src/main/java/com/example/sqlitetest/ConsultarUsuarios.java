package com.example.sqlitetest;

import static java.nio.file.Files.delete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitetest.Entidades.ConexionSqliteHelper;
import com.example.sqlitetest.Utilidades.Utilidades;

import java.io.IOException;

public class ConsultarUsuarios extends AppCompatActivity implements View.OnClickListener {

    EditText send_id, set_name, set_tlf;
    ConexionSqliteHelper con;
    Button search_btn,actualizar_btn,delete_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);
        con = new ConexionSqliteHelper(getApplicationContext(),"db_usuarios",null,1);
        send_id = findViewById(R.id.send_id);
        set_name = findViewById(R.id.set_name);
        set_tlf = findViewById(R.id.set_tlf);
        search_btn = findViewById(R.id.search_btn);
        actualizar_btn = findViewById(R.id.actualizar_btn);
        delete_btn = findViewById(R.id.delete_btn);

        search_btn.setOnClickListener(this);
        actualizar_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search_btn) {
            //buscar();
            buscarSql();
        } else if(view.getId() == R.id.actualizar_btn) {
            actualizar();
        }else if(view.getId() == R.id.delete_btn) {
            eliminar();
        }
    }
    private void buscarSql(){
        SQLiteDatabase db= con.getReadableDatabase();
        String[] id = {send_id.getText().toString()};

        try {
            //SELECT nombre,telefono FROM TABLE usuario where codigo=?
            Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
                    " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"=?",id);;
            cursor.moveToFirst();
            set_name.setText(cursor.getString(0));
            set_tlf.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            e.printStackTrace(System.out);
            Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void buscar() {
        SQLiteDatabase db= con.getReadableDatabase();
        String[] id = {send_id.getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try{
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",id,null,null,null);
            cursor.moveToFirst();
            set_name.setText(cursor.getString(0));
            set_tlf.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            e.printStackTrace(System.out);
            Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void actualizar() {
        SQLiteDatabase db= con.getReadableDatabase();
        String[] id = {send_id.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,set_name.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,set_tlf.getText().toString());
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",id);
        Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void eliminar() {
        SQLiteDatabase db= con.getReadableDatabase();
        String[] id = {send_id.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",id);
        Toast.makeText(this, "Se ha eliminado el usuario", Toast.LENGTH_SHORT).show();
        send_id.setText("");
        limpiar();
        db.close();
    }

    private void limpiar() {
        set_tlf.setText("");
        set_name.setText("");
    }
}