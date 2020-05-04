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

public class Win extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winloss);

        Intent intent = getIntent();
        player = (Player) intent.getExtras().getSerializable("player");

        ImageView picture = findViewById(R.id.winlossDisplay);
        picture.setImageResource(R.drawable.treasure);

        TextView text = findViewById(R.id.winlossText);
        text.setGravity(Gravity.CENTER_HORIZONTAL);
        text.setText(R.string.treasure);

        Button newGame = findViewById(R.id.winlossButton);
        newGame.setText("Yay I did it!");
        newGame.setOnClickListener(v -> toPlayerHub());

    }
    private void toPlayerHub() {
        Intent intent = new Intent(this, PlayerHub.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
}
