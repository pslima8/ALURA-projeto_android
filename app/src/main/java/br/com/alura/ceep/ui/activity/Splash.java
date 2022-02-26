package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        int delay;
        if (preferences.contains("ja_abriu_app")){
            delay = 500;
        } else {
            adicionarPreferenceJaAbriu(preferences);
            delay = 2000;
        }
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, ListaNotasActivity.class);
                startActivity(i);
                finish();
            }
        }, delay);
    }

    private void adicionarPreferenceJaAbriu(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ja_abriu_app", true);
        editor.commit();
    }
}
