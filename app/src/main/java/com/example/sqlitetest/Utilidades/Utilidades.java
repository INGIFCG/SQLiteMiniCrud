package com.example.sqlitetest.Utilidades;

//variables constantes utilizadas para referirce a las tablas o campos contenidos en la base de datos

public class Utilidades {
    //query para crear la tabla en la base de datos
    //CONTASTANTES CAMPOS TABLA USUARIOS
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE= "nombre";
    public static final String CAMPO_TELEFONO= "telefono";
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+
            "("+CAMPO_ID+" INTEGER,"+CAMPO_NOMBRE+" STRING,"+CAMPO_TELEFONO+" STRING)";
}
