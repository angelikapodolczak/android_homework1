package com.example.homework1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String SOUND_ID = "sound id";
    public static final int SOUND_REQUEST = 0;
    private int current_sound = 0;

    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;
    private boolean pause = false;

    public static final String CONTACT_ID = "contact id";
    private String current_contact = "John Doe";
    public static final int CONTACT_REQUEST = 0;
    public static final String AVATAR_ID = "avatar id";
    private int current_avatar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button openContact = findViewById(R.id.button1);
        openContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent contact = new Intent(getApplicationContext(), ContactActivity.class);
                contact.putExtra(CONTACT_ID, current_contact);
                contact.putExtra(AVATAR_ID, current_avatar);
                startActivityForResult(contact, CONTACT_REQUEST);
            }
        });

        TextView contact = findViewById(R.id.name);
        contact.setText(current_contact);

        final ImageView imView = (ImageView) findViewById(R.id.imageView);
        imView.setImageResource(R.drawable.avatar);

        Button openSound = findViewById(R.id.button2);
        openSound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent sound = new Intent(getApplicationContext(), SoundActivity.class);
                sound.putExtra(SOUND_ID, current_sound);
                startActivityForResult(sound, CONTACT_REQUEST);
            }
        });

        sounds = new Uri[5];
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mario);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring01);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring02);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring03);
        sounds[4] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring04);

        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pause) {
                    buttonPlayer.pause();
                    pause = false;
                } else {
                    buttonPlayer = MediaPlayer.create(getApplicationContext(),sounds[current_sound]);
                    buttonPlayer.start();
                    pause = true;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // Make sure the request was successful
            if(requestCode == SOUND_REQUEST) {
                current_sound = data.getIntExtra(SOUND_ID,0);
            }

            if (requestCode == CONTACT_REQUEST){
                current_contact = data.getStringExtra(CONTACT_ID);
                current_avatar = data.getIntExtra(AVATAR_ID,0);
                TextView contact_change  = findViewById(R.id.name);
                contact_change.setText(current_contact);

                final ImageView imView = (ImageView) findViewById(R.id.imageView);
                switch (current_avatar) {
                    case 1:
                        imView.setImageResource(R.drawable.avatar1); break;
                    case 2:
                        imView.setImageResource(R.drawable.avatar2); break;
                    case 3:
                        imView.setImageResource(R.drawable.avatar3); break;
                    case 4:
                        imView.setImageResource(R.drawable.avatar4); break;
                    case 5:
                        imView.setImageResource(R.drawable.avatar5); break;
                    case 6:
                        imView.setImageResource(R.drawable.avatar6); break;
                    case 7:
                        imView.setImageResource(R.drawable.avatar7); break;
                    case 8:
                        imView.setImageResource(R.drawable.avatar8); break;
                    default:
                        imView.setImageResource(R.drawable.avatar);
                }
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        buttonPlayer.pause();
        pause=false;
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        buttonPlayer.release();
    }
}
