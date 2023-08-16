package com.mangostynn.ticktactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayers extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(  WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_players);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final EditText  playerOne = findViewById(R.id.player1Name),
                        playerTwo = findViewById(R.id.player2Name);
        final AppCompatButton startBtn = findViewById(R.id.startBtn);
        final AppCompatButton creditBtn = findViewById(R.id.creditBtn);

        startBtn.setOnClickListener(v -> {
            final String
                    playerOneName = playerOne.getText()+"",
                    playerTwoName = playerTwo.getText()+"";
            if(playerOneName.isEmpty()||playerTwoName.isEmpty()){
                Toast.makeText(AddPlayers.this, "Please enter your names", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(AddPlayers.this,MainActivity.class);
                intent.putExtra("playerOne",playerOneName);
                intent.putExtra("playerTwo",playerTwoName);
                startActivity(intent);
            }
        });

        creditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPlayers.this.openCredits();
            }
        });
    }
    public void openCredits(){
        Intent intent = new Intent(this, credits.class);
        startActivity(intent);
    }

}