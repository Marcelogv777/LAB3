package com.example.tel306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(getApplicationContext(), "hace calor", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,HelpActivity.class));
                break;
            }

        }
        return true;
    }


}