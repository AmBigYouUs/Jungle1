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

public class Loss extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winloss);

        ImageView picture = findViewById(R.id.winlossDisplay);
        picture.setImageResource(R.drawable.crossbones);

        TextView text = findViewById(R.id.winlossText);
        text.setGravity(Gravity.CENTER_HORIZONTAL);
        text.setText(R.string.loss);

        Button newGame = findViewById(R.id.winlossButton);
        newGame.setText("Start New Game");
        newGame.setOnClickListener(v -> toNewGame());

    }
    private void toNewGame() {
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
        finish();
    }
}
