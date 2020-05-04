package com.example.jungle.Logic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jungle.Logic.InnerLogic.Player;
import com.example.jungle.R;

import java.util.Random;

public class JungleBattle extends AppCompatActivity {

    private Player player;
    private String opponent;
    private int opponentHp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junglebattle);

        Intent intent = getIntent();
        player = (Player) intent.getExtras().getSerializable("player");
        opponent = intent.getExtras().get("opponent").toString();

        ImageView image = findViewById(R.id.jungleBattlePic);
        TextView opponentDescription = findViewById(R.id.opponentDescriptionJungle);

        if (opponent.contains("bandits")) {
            opponentHp = 15;
            image.setImageResource(R.drawable.bandits);
            opponentDescription.setText("Damage : (0, 3, 6) @ 33% Chance");
            opponentDescription.setGravity(Gravity.CENTER_HORIZONTAL);
        } else if (opponent.contains("leopard")) {
            opponentHp = 10;
            image.setImageResource(R.drawable.leopard);
            opponentDescription.setText("Damage : (0, 5) @ 50% Chance");
            opponentDescription.setGravity(Gravity.CENTER_HORIZONTAL);
        }

        Button move = findViewById(R.id.makeMoveJungleButton);
        move.setOnClickListener(v -> makeMove());

        Button tryRun = findViewById(R.id.tryrunButton);
        tryRun.setOnClickListener(v -> tryToRun());

        updateUI();


    }
    private void updateUI() {




        TextView opponentHP = findViewById(R.id.opponentHPJungle);
        TextView playerHP = findViewById(R.id.playerHPJungleBattle);
        RadioButton heal = findViewById(R.id.healradioJungle);
        RadioButton fireball = findViewById(R.id.fireballradioJungle);
        RadioButton shield = findViewById(R.id.shieldradioJungle);
        RadioButton doubleshot = findViewById(R.id.doubleshotradioJungle);
        RadioGroup specials = findViewById(R.id.specialGroupJungle);
        specials.clearCheck();

        if (opponent.contains("bandits")) {
            opponentHP.setText("Bandit HP : " + opponentHp);
        } else if (opponent.contains("leopard")) {
            opponentHP.setText("Leopard HP : " + opponentHp);

        }

        playerHP.setText("Your HP : " + player.getHp());
        if (player.getHeal() > 0) {
            heal.setVisibility(View.VISIBLE);
            heal.setText(player.getHeal() + " : Heal (+5 HP)");
        } else { heal.setVisibility(View.GONE); }
        if (player.getFireball() > 0) {
            fireball.setVisibility(View.VISIBLE);
            fireball.setText(player.getFireball() + " : Fireball (+5 Damage)");
        } else { fireball.setVisibility(View.GONE); }
        if (player.getShield() > 0) {
            shield.setVisibility(View.VISIBLE);
            shield.setText(player.getShield() + " : Shield (+5 Block)");
        } else { shield.setVisibility(View.GONE); }
        if (player.getDoubleShot() > 0) {
            doubleshot.setVisibility(View.VISIBLE);
            doubleshot.setText(player.getDoubleShot() + " : Double Shot (+3 Damage)");
        } else { doubleshot.setVisibility(View.GONE); }


        if(player.getHp() < 8) {
            TextView healthwarning = findViewById(R.id.healthwarningJungleBattle);
            healthwarning.setGravity(Gravity.CENTER_HORIZONTAL);
            healthwarning.setText("Your health is low! Visit the Inn to recover.");
            healthwarning.setTextColor(Color.RED);
        } else {
            TextView healthwarning = findViewById(R.id.healthwarningJungleBattle);
            healthwarning.setText("");
        }

    }
    public void tryToRun() {

        goBack();
    }


    private void makeMove() {


        RadioGroup moves = findViewById(R.id.movesradioJungle);
        RadioGroup specials = findViewById(R.id.specialGroupJungle);
        //heal
        if (specials.getCheckedRadioButtonId() == R.id.healradioJungle) {
            player.heal();
        }
        //player
        int opponentHPChange = 0;
        if (specials.getCheckedRadioButtonId() == R.id.fireballradioJungle) {
            opponentHPChange += 5;
            player.setFireball();
            checkWinLoss();
        }
        if (moves.getCheckedRadioButtonId() == R.id.attackradioJungle) {
            opponentHPChange += 3;
        }
        if (specials.getCheckedRadioButtonId() == R.id.doubleshotradioJungle) {
            opponentHPChange += 3;
            player.setDoubleShot();
        }
        opponentHp -= opponentHPChange;

        checkWinLoss();
        //opponent leopard
        int damage = 0;
        if (opponent.contains("leopard")) {
            int randomOpponent = new Random().nextInt(2);
            if (randomOpponent == 1) {
                if (moves.getCheckedRadioButtonId() == R.id.defendradioJungle) {
                    damage += 2;
                } else {
                    damage += 5;
                }
            }
        } else if (opponent.contains("bandits")) {
            int randomOpponent = new Random().nextInt(3);
            if (randomOpponent == 1) {
                if (!(moves.getCheckedRadioButtonId() == R.id.defendradioJungle)) {
                    damage += 3;
                }
            }
            if (randomOpponent == 2 && !(moves.getCheckedRadioButtonId() == R.id.defendradioJungle)) {
                damage += 6;
            } else {
                damage += 3;
            }

        }
        if (specials.getCheckedRadioButtonId() == R.id.shieldradioJungle) {
            damage -= 5;
            player.setShield();
        }
        if (damage > 0) {
            player.damage(damage);
        }
        checkWinLoss();
        TextView lastmove = findViewById(R.id.lastMoveJungle);
        lastmove.setText("Last move results \nDamage Dealt: " + opponentHPChange + " || Damage Taken: " + damage);
        updateUI();

    }
    private void checkWinLoss() {
        if (player.getHp() < 1) {
            goLoss();
        }
        if (opponentHp < 1) {
            //alert dialog to win
            goBack();
        }


    }
    private void goBack() {
        if (opponent.contains("leopard")) {
            player.getGold(10);
        } else if (opponent.contains("bandits")) {
            player.getGold(25);
        }
        Intent intent = new Intent(this, Jungle.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
    private void goLoss() {
        Intent intent = new Intent(this, Loss.class);
        startActivity(intent);
        finish();
    }
}
