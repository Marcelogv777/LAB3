package com.example.tel306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String [] palabras_concentrantes = {"No mires el celular", "Tu puedes concentrarte, Vamos!",
            "La perserverancia es el camino al exito"};
    String [] palabras_relajantes = {"Un descanso de mas energia", "Suficiente por ahora, toma agua",
            "para un segundo para volver con más ganas"};
    String [] datos_curiosos ={"Sabias que las arañas tienen 8 ojos",
            "Sabias que hay 151 pokemon en la primera generacion",
            "Sabias que Telecomunicaciones es la carrera mas demandada a nivel mundial",
            "Los pulpos tienen tres corazones y no solo ello, su sangre es azul",
            "Sabias que las tortugas pueden vivir mas de 100 años"};
    int ciclo=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ContadorViewModel contadorViewModel=new ViewModelProvider(this).get(ContadorViewModel.class);
        ImageView seting = (ImageView) findViewById(R.id.set);
        contadorViewModel.getTrabajo().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int fin = contadorViewModel.getFinTrabajo();
                String min= String.valueOf ((fin-integer)/60);
                int segi=(fin-integer)%60;
                String seg;
                if(segi<10)
                {
                     seg = "0"+String.valueOf(segi);
                }
                else
                {
                     seg = String.valueOf(segi);
                }

                TextView temporizador = findViewById(R.id.tiempo_trabajo);
                temporizador.setText(min + ":" + seg);
                if (integer==fin)
                {
                    contadorViewModel.cuentaDescanso();
                    int n=palabras_relajantes.length;
                    Random random = new Random();
                    int index =  random.nextInt(n);
                    String palabra = palabras_relajantes[index];
                    TextView textView = findViewById(R.id.mensajes);
                    textView.setText(palabra);
                }
            }
        });

        contadorViewModel.getDescanso().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int fin = contadorViewModel.getFinDescanso();
                String min= String.valueOf ((fin-integer)/60);
                int segi=(fin-integer)%60;
                String seg;
                if(segi<10)
                {
                    seg = "0"+String.valueOf(segi);
                }
                else
                {
                    seg = String.valueOf(segi);
                }

                TextView temporizador = findViewById(R.id.tiempo_descanso);
                temporizador.setText(min + ":" + seg);
                if(integer==fin)
                {
                    ciclo++;
                    TextView tv = findViewById(R.id.ciclo_pomodoro);
                    if (ciclo<=4)
                    {
                        tv.setText("ciclo pomodoro "+ String.valueOf(ciclo) +" de 4");
                        contadorViewModel.getDescanso().setValue(0);
                        contadorViewModel.getTrabajo().setValue(0);
                        contadorViewModel.cuentaTrabajo();
                        int n=palabras_concentrantes.length;
                        Random random = new Random();
                        int index =  random.nextInt(n);
                        String palabra = palabras_concentrantes[index];
                        TextView textView = findViewById(R.id.mensajes);
                        textView.setText(palabra);
                    }
                    else
                    {
                        //fin del programa
                    }
                }
            }
        });

        seting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView iv = (ImageView) v;
                if (contadorViewModel.getHilo()==null)
                {
                    iv.setImageResource(R.drawable.pause);
                    if (contadorViewModel.getActivo().equalsIgnoreCase("trabajo"))
                    {
                        contadorViewModel.cuentaTrabajo();
                        int n=palabras_concentrantes.length;
                        Random random = new Random();
                        int index =  random.nextInt(n);
                        String palabra = palabras_concentrantes[index];
                        TextView textView = findViewById(R.id.mensajes);
                        textView.setText(palabra);
                    }
                    else
                    {
                        contadorViewModel.cuentaDescanso();
                        int n=palabras_relajantes.length;
                        Random random = new Random();
                        int index =  random.nextInt(n);
                        String palabra = palabras_relajantes[index];
                        TextView textView = findViewById(R.id.mensajes);
                        textView.setText(palabra);
                    }


                }
                else
                {
                    contadorViewModel.detenerContador();
                    iv.setImageResource(R.drawable.play);
                }

            }
        });

        ImageView reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contadorViewModel.detenerContador();
                contadorViewModel.setActivo("trabajo");
                ImageView iv = findViewById(R.id.set);
                iv.setImageResource(R.drawable.play);
                contadorViewModel.getTrabajo().setValue(0);
                contadorViewModel.getDescanso().setValue(0);
                TextView textView = findViewById(R.id.mensajes);
                textView.setText(" ");
                ciclo=1;
                TextView tv = findViewById(R.id.ciclo_pomodoro);
                tv.setText("ciclo pomodoro "+ String.valueOf(ciclo) +" de 4");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.help_menu, menu);
        return true;
    }
    /*
    public void helpMenu(View view){
        Toast.makeText(this,"boton HELP",Toast.LENGTH_SHORT).show();
    }

     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help: {
                Toast.makeText(getApplicationContext(), "Abriendo menu de ayuda", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,HelpActivity.class));
                break;
            }

        }
        return true;
    }




}