package com.example.exercici1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.exercici1.databinding.ActivityProductosBinding;

public class Productos extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityProductosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        Intent intent = getIntent();
        String nom = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView titulo = findViewById(R.id.textview_first);
        titulo.setText("Bienvenido "+nom);

        ImageView movil = findViewById(R.id.imageMovil);
        movil.setImageResource(R.drawable.movil);


        ImageView pantalla = findViewById(R.id.imagePantalla);
        pantalla.setImageResource(R.drawable.pantalla);

        ImageView teclado = findViewById(R.id.imageTeclado);
        teclado.setImageResource(R.drawable.teclado);

        ImageView raton = findViewById(R.id.imageRaton);
        raton.setImageResource(R.drawable.raton);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_productos);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void showOrderMovil(View view) {
        displayToast(getString(R.string.movil_order_message));
    }
    public void showOrderPantalla(View view) {
        displayToast(getString(R.string.pantalla_order_message));
    }
    public void showOrderTeclado(View view) {
        displayToast(getString(R.string.teclado_order_message));
    }
    public void showOrderRaton(View view) {
        displayToast(getString(R.string.raton_order_message));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.action_settings){
            Intent intent = new Intent(this, Formulari.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_productos);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}