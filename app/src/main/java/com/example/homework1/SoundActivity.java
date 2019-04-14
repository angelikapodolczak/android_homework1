package com.example.homework1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SoundActivity extends AppCompatActivity {

    private int selected_sound = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound);

        Intent received_intent = getIntent();
        int sound_id = received_intent.getIntExtra(MainActivity.SOUND_ID,0);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.sound1:
                    selected_sound = 0;
                    break;
                case R.id.sound2:
                    selected_sound = 1;
                    break;
                case R.id.sound3:
                    selected_sound = 2;
                    break;
                case R.id.sound4:
                    selected_sound = 3;
                    break;
                case R.id.sound5:
                    selected_sound = 4;
                    break;
            }
        }
    }

    public void setSoundClick(View view) {
        Intent data = new Intent();
        data.putExtra(MainActivity.SOUND_ID,selected_sound);
        setResult(RESULT_OK, data);
        finish();
    }

    public void cancelButt (View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
