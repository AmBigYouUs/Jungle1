package com.example.jungle.Logic;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jungle.Logic.InnerLogic.Player;
import com.example.jungle.R;

import java.util.Random;

public class Jungle extends AppCompatActivity {

    private Player player;
    TextView jungleText;
    Button first;
    Button second;
    ImageView junglePic;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jungle);

        Intent intent = getIntent();
        player = (Player) intent.getExtras().getSerializable("player");

        jungleText = findViewById(R.id.eventtextJungle);
        jungleText.setText("Welcome to the jungle! Explore at your own risk...");
        junglePic = findViewById(R.id.junglePic);
        junglePic.setImageResource(R.drawable.jungle1);

        first = findViewById(R.id.firstJungleButton);
        first.setText("Explore the Jungle");
        first.setOnClickListener(v -> jungleEvent());

        second = findViewById(R.id.secondJungleButton);
        second.setVisibility(View.GONE);

        updateUI();

        Button leave = findViewById(R.id.leaveJungleButton);
        leave.setOnClickListener(v -> leaveJungle());

    }
    private void jungleEvent() {
        int jungle = new Random().nextInt(100);
        if (jungle == 0) {
            win();
        } else if (jungle < 5) {
            //Shaman
            jungleText.setText(R.string.shaman_jungle);
            junglePic.setImageResource(R.drawable.shaman);
            player.shaman();
            player.getGold(1);
            updateUI();

        } else if (jungle < 10) {
            //Oasis
            jungleText.setText(R.string.oasis_jungle);
            junglePic.setImageResource(R.drawable.theoasis);
            player.setHp(20);
            player.getGold(1);
            updateUI();

        } else if (jungle < 20) {
            //snake
            jungleText.setText(R.string.snake_jungle);
            junglePic.setImageResource(R.drawable.snake);
            player.damage(2);
            player.getGold(1);
            updateUI();

        } else if (jungle < 30) {
            //spiders
            jungleText.setText(R.string.spiders_jungle);
            junglePic.setImageResource(R.drawable.spiders);
            player.damage(2);
            player.getGold(1);
            updateUI();

        } else if (jungle < 35) {
            //Tent
            jungleText.setText(R.string.tent_jungle);
            junglePic.setImageResource(R.drawable.tent);
            player.addRandomSpecial();
            player.getGold(1);
            updateUI();

        } else if (jungle < 40) {
            //Dead body
            jungleText.setText(R.string.body_jungle);
            junglePic.setImageResource(R.drawable.grave);
            player.addRandomSpecial();
            player.getGold(1);
            updateUI();

        } else if (jungle < 48) {
            //Bandits
            player.getGold(1);
            jungleText.setText(R.string.bandits_jungle);
            junglePic.setImageResource(R.drawable.bandits);
            first.setText("Fight them!");
            first.setOnClickListener(v -> banditsBattle());
            Button leave = findViewById(R.id.leaveJungleButton);
            leave.setVisibility(View.GONE);

        } else if (jungle < 56) {
            //Bandits + Leopard
            player.getGold(1);
            jungleText.setText(R.string.leopard_jungle);
            junglePic.setImageResource(R.drawable.leopard);
            first.setText("Fight it!");
            first.setOnClickListener(v -> leopardBattle());
            Button leave = findViewById(R.id.leaveJungleButton);
            leave.setVisibility(View.GONE);
        //} else if (jungle < 45) {
            //pit
            //jungleText.setText();
            //player.removeRandomSpecial();
            //updateUI();
        } else if (jungle < 67) {
            player.getGold(1);
            jungleText.setText(R.string.nothing_jungle1);
            junglePic.setImageResource(R.drawable.jungle1);
            updateUI();
        } else if (jungle < 78) {
            player.getGold(1);
            jungleText.setText(R.string.nothing_jungle2);
            junglePic.setImageResource(R.drawable.jungle2);
            updateUI();
        } else if (jungle < 86) {
            player.getGold(1);
            jungleText.setText(R.string.nothing_jungle1);
            junglePic.setImageResource(R.drawable.jungle3);
            updateUI();
        } else if (jungle < 92) {
            player.getGold(15);
            jungleText.setText("It not be the treasure you seek, but a few gold nuggets ain't bad");
            junglePic.setImageResource(R.drawable.gold);
            updateUI();
        } else {
            player.getGold(1);
            jungleText.setText(R.string.nothing_jungle2);
            junglePic.setImageResource(R.drawable.jungle4);
            updateUI();
        }
    }
    private void updateUI() {

        if (player.getHp() < 1) {
            goLoss();
        }
        TextView playerHP = findViewById(R.id.playerHPJungle);
        TextView heal = findViewById(R.id.healJungle);
        TextView fireball = findViewById(R.id.fireballJungle);
        TextView shield = findViewById(R.id.shieldJungle);
        TextView doubleshot = findViewById(R.id.doubleshotJungle);
        TextView gold = findViewById(R.id.goldJungle);

        playerHP.setText("Your HP : " + player.getHp());
        gold.setText("Gold : " + player.getGoldCount());
        heal.setText(player.getHeal() + " : Heal");
        fireball.setText(player.getFireball() + " : Fireball");
        shield.setText(player.getShield() + " : Shield");
        doubleshot.setText(player.getDoubleShot() + " : Double Shot");

        second.setVisibility(View.GONE);

        if(player.getHp() < 8) {
            TextView healthwarning = findViewById(R.id.healthwarningJungle);
            healthwarning.setGravity(Gravity.CENTER_HORIZONTAL);
            healthwarning.setText("Your health is low! Visit the Inn to recover.");
            healthwarning.setTextColor(Color.RED);
        } else {
            TextView healthwarning = findViewById(R.id.healthwarningJungle);
            healthwarning.setText("");
        }

        if (player.getHp() < 16 && player.getHeal() > 0) {
            second.setVisibility(View.VISIBLE);
            second.setText("Use Heal");
            second.setOnClickListener(v -> healHub());
        }

    }
    private void win() {
        player.newTreasure();
        Intent intent = new Intent(this, Win.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
    private void leaveJungle() {
        Intent intent = new Intent(this, PlayerHub.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
    private void banditsBattle() {
        Intent intent = new Intent(this, JungleBattle.class);
        intent.putExtra("player", player);
        intent.putExtra("opponent", "bandits");
        startActivity(intent);
        finish();
    }
    private void leopardBattle() {
        Intent intent = new Intent(this, JungleBattle.class);
        intent.putExtra("player", player);
        intent.putExtra("opponent", "leopard");
        startActivity(intent);
        finish();
    }
    private void healHub() {
        player.heal();
        updateUI();

    }
    private void goLoss() {
        Intent intent = new Intent(this, Loss.class);
        startActivity(intent);
        finish();
    }
}
