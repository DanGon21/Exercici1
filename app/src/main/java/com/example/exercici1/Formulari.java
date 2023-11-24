package com.example.exercici1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Formulari extends AppCompatActivity {
    private WordListOpenHelper mDB;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari);
        mDB = new WordListOpenHelper(this);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String opcionSeleccionada = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        Button buttonEnviar = findViewById(R.id.buttonEnviar);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesarYEnviarDatos();
            }
        });
    }

    public void selectDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
            String month_string = Integer.toString(month + 1);
            String day_string = Integer.toString(day);
            String year_string = Integer.toString(year);
            String dateMessage = (month_string + "/"
                    + day_string + "/" + year_string);
            TextView selectedDateTextView = findViewById(R.id.checkedTextData);
            selectedDateTextView.setText(getString(R.string.selected_date, dateMessage));
        }


    private void procesarYEnviarDatos() {
        String nombre = ((EditText) findViewById(R.id.editTextNom)).getText().toString();
        String correo = ((EditText) findViewById(R.id.editTextCorreu)).getText().toString();
        String contrasena = ((EditText) findViewById(R.id.editTextContrasenya)).getText().toString();
        String telefono = ((EditText) findViewById(R.id.editTextTelefon)).getText().toString();
        String edad = obtenerEdadSeleccionada();
        String sexo = obtenerSexoSeleccionado();
        String fechaNacimiento = obtenerFechaNacimiento();
        long newRowId = insertarDatosEnBaseDeDatos(nombre, correo, contrasena, telefono, edad, sexo, fechaNacimiento);
        Intent intent = new Intent(Formulari.this, DatosFormulari.class);
        intent.putExtra("ID_FILA", newRowId);
        Log.d("Datos", "ID: "+newRowId);

        startActivity(intent);
    }

    private String obtenerEdadSeleccionada() {
        int radioButtonId = ((RadioGroup) findViewById(R.id.grupEdat)).getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        return radioButton.getText().toString();
    }

    private String obtenerSexoSeleccionado() {
        Spinner spinner = findViewById(R.id.spinner);
        String sexoSeleccionado = spinner.getSelectedItem().toString();

        return sexoSeleccionado;
    }
    private String obtenerFechaNacimiento() {
        TextView selectedDateTextView = findViewById(R.id.checkedTextData);
        return selectedDateTextView.getText().toString();
    }

    private long insertarDatosEnBaseDeDatos(String nombre, String correo, String contrasena, String telefono,
                                            String edad, String sexo, String fechaNacimiento) {
        SQLiteDatabase db = mDB.getWritableDatabase();
        long newRowId = -1;

        try {
            ContentValues values = new ContentValues();
            values.put(WordListOpenHelper.KEY_NOMBRE, nombre);
            values.put(WordListOpenHelper.KEY_CORREO, correo);
            values.put(WordListOpenHelper.KEY_CONTRASENA, contrasena);
            values.put(WordListOpenHelper.KEY_TELEFON, telefono);
            values.put(WordListOpenHelper.KEY_EDAD, edad);
            values.put(WordListOpenHelper.KEY_SEXO, sexo);
            values.put(WordListOpenHelper.KEY_FECHA_NACIMIENTO, fechaNacimiento);

            newRowId = db.insert(WordListOpenHelper.TABLE_NAME, null, values);

            Log.d("Formulari", "Nuevo ID de fila: " + newRowId);
        } catch (Exception e) {
            Log.e("Formulari", "Error al insertar datos en la base de datos: " + e.getMessage());
        } finally {
            db.close();
        }

        return newRowId;
    }
}


