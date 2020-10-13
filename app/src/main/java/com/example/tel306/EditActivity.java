package com.example.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Button b = findViewById(R.id.buttonEdit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                EditText inputTrabajo = findViewById(R.id.trabajoId);
                int trabajo = Integer.parseInt(inputTrabajo.getText().toString());
                EditText inputDescanso = findViewById(R.id.descansoId);
                int descanso = Integer.parseInt(inputDescanso.getText().toString());
                EditText inputCiclos = findViewById(R.id.ciclosId);
                int ciclos = Integer.parseInt(inputCiclos.getText().toString());
                intent.putExtra("trabajoNew",trabajo);
                intent.putExtra("descansoNew",descanso);
                intent.putExtra("ciclosNew",ciclos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }


}
