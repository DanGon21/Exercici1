package com.example.exercici1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Nom;
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nom = findViewById(R.id.editTextUsuari);

    }

    public void launchSecondActivity(View view){
            Intent intent = new Intent(MainActivity.this,Productos.class);
            String nom = Nom.getText().toString();
            intent.putExtra(EXTRA_MESSAGE,nom);
            startActivity(intent);
        }
    }
