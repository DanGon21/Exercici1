package com.example.exercici1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DatosFormulari extends AppCompatActivity {
    private long rowId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_formulari);

         rowId = getIntent().getLongExtra("ID_FILA", rowId);
         Log.d("Datos", "ID: "+rowId);

        if (rowId != -1) {
            WordListOpenHelper dbHelper = new WordListOpenHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {
                    WordListOpenHelper.KEY_NOMBRE,
                    WordListOpenHelper.KEY_CORREO,
                    WordListOpenHelper.KEY_CONTRASENA,
                    WordListOpenHelper.KEY_TELEFON,
                    WordListOpenHelper.KEY_EDAD,
                    WordListOpenHelper.KEY_SEXO,
                    WordListOpenHelper.KEY_FECHA_NACIMIENTO,

            };

            String selection = WordListOpenHelper.KEY_ID + " = ?";
            String[] selectionArgs = { String.valueOf(rowId) };

            Cursor cursor = db.query(
                    WordListOpenHelper.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(WordListOpenHelper.KEY_NOMBRE));
                String correo = cursor.getString(cursor.getColumnIndexOrThrow(WordListOpenHelper.KEY_CORREO));
                String contrasena = cursor.getString(cursor.getColumnIndexOrThrow(WordListOpenHelper.KEY_CONTRASENA));
                String telefon = cursor.getString(cursor.getColumnIndexOrThrow(WordListOpenHelper.KEY_TELEFON));
                String edad = cursor.getString(cursor.getColumnIndexOrThrow(WordListOpenHelper.KEY_EDAD));
                String sexo = cursor.getString(cursor.getColumnIndexOrThrow(WordListOpenHelper.KEY_SEXO));
                String nacimiento = cursor.getString(cursor.getColumnIndexOrThrow(WordListOpenHelper.KEY_FECHA_NACIMIENTO));

                String detalles = "Nombre: " + nombre + "\nCorreo: " + correo + "\nContraseña: " + contrasena + "\nTelefon: " + telefon + "\nEdad: " + edad+ "\nSexo: " + sexo + "\nFecha de Nacimiento: " + nacimiento;

                TextView detallesTextView = findViewById(R.id.textViewDetalles);
                detallesTextView.setText(detalles);
            }

            cursor.close();
            db.close();
        } else {

            Toast.makeText(this, "ID de fila no válido", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void atras(View view){
        Intent intent = new Intent(DatosFormulari.this,Productos.class);
        startActivity(intent);
    }
}
