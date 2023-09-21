package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitetest.Entidades.ConexionSqliteHelper;
import com.example.sqlitetest.Utilidades.Utilidades;

public class RegistroUsuariosActivity extends AppCompatActivity {

    Button send_btn;
    EditText send_name, send_tlf, send_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        send_name = findViewById(R.id.set_name);
        send_tlf = findViewById(R.id.set_tlf);
        send_id = findViewById(R.id.send_id);
        send_btn = findViewById(R.id.search_btn);


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //registrarUsuarios();//
                registrarUsuariosSql(); // metodo con sql
            }

            private void registrarUsuariosSql() {
                ConexionSqliteHelper con = new ConexionSqliteHelper(getApplicationContext(), "db_usuarios", null, 1);
                SQLiteDatabase db = con.getWritableDatabase();
                try {
                    //insert into  usuario(id,nombre,telefono) values (1,"carlos","gonzalez");
                    String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO + "( " + Utilidades.CAMPO_ID +
                            "," + Utilidades.CAMPO_NOMBRE + " , " + Utilidades.CAMPO_TELEFONO +
                            ") values (" + send_id.getText().toString() + ",'" + send_name.getText().toString() + "','" + send_tlf.getText().toString() + "')";
                    db.execSQL(insert);
                    send_name.setText("");
                    send_tlf.setText("");
                    send_id.setText("");
                    db.close();
                    Toast.makeText(RegistroUsuariosActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                    Toast.makeText(RegistroUsuariosActivity.this, "Error al insertar datos", Toast.LENGTH_SHORT).show();
                }
            }

            private void registrarUsuarios() {
                ConexionSqliteHelper con = new ConexionSqliteHelper(getApplicationContext(), "db_usuarios", null, 1);

                SQLiteDatabase db = con.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_ID, send_id.getText().toString());
                values.put(Utilidades.CAMPO_NOMBRE, send_name.getText().toString());
                values.put(Utilidades.CAMPO_TELEFONO, send_tlf.getText().toString());
                Long idresultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);
                Toast.makeText(RegistroUsuariosActivity.this, "ID Registro" + idresultante, Toast.LENGTH_SHORT).show();
                send_name.setText("");
                send_tlf.setText("");
                send_id.setText("");
                db.close();
            }
        });
    }


}