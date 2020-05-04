package com.example.jungle.Logic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jungle.Logic.InnerLogic.Player;
import com.example.jungle.R;

public class NewGame extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);

        ImageView picture = findViewById(R.id.newDisplay);
        picture.setImageResource(R.drawable.welcome);

        TextView text = findViewById(R.id.welcomeText);
        text.setText(R.string.this_is_the_introduction);
        text.setGravity(Gravity.CENTER_HORIZONTAL);

        Button newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(v -> makeNewGame());

    }
    public void makeNewGame() {


        Intent intent = new Intent(this, PlayerHub.class);
        Player player = new Player(20,1);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
}
