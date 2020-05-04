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

public class PlayerHub extends AppCompatActivity {

    private Player player;
    private ImageView hubImage;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerhub);

        hubImage = findViewById(R.id.hubDisplay);
        hubImage.setImageResource(R.drawable.campsite);

        Intent intent = getIntent();
        player = (Player) intent.getExtras().getSerializable("player");

        Button quit = findViewById(R.id.quitGame);
        quit.setOnClickListener(v -> quitGame());
        Button explore = findViewById(R.id.exploreJungle);
        explore.setOnClickListener(v -> exploreJungle());
        Button visitInn = findViewById(R.id.goToInn);
        visitInn.setOnClickListener(v -> goInn());



        updateUI();

    }
    public void updateUI() {
        TextView treasures = findViewById(R.id.treasuresHub);
        TextView daysSince = findViewById(R.id.dayssinceHub);
        TextView playerHP = findViewById(R.id.playerHPHub);
        TextView heal = findViewById(R.id.healHub);
        TextView fireball = findViewById(R.id.fireballHub);
        TextView shield = findViewById(R.id.shieldHub);
        TextView doubleshot = findViewById(R.id.doubleshotHub);
        TextView gold = findViewById(R.id.goldHub);

        treasures.setText("Treasures found : " + player.getTreasures());
        daysSince.setText("Days since last treasure : " + player.getDaysSinceTreasure());
        gold.setText("Gold : " + player.getGoldCount());

        playerHP.setText("Your HP : " + player.getHp());
        heal.setText(player.getHeal() + " : Heal");
        fireball.setText(player.getFireball() + " : Fireball");
        shield.setText(player.getShield() + " : Shield");
        doubleshot.setText(player.getDoubleShot() + " : Double Shot");

    }
    public void quitGame() {
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
        finish();
    }
    public void exploreJungle() {
        Intent intent = new Intent(this, Jungle.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
    public void goInn() {
        player.goToInn();
        updateUI();
        hubImage.setImageResource(R.drawable.tavern);
        TextView innDisplay = findViewById(R.id.innDisplay);
        innDisplay.setGravity(Gravity.CENTER_HORIZONTAL);
        String display = getResources().getString(R.string.inn_display);
        innDisplay.setText(display);

    }
}
