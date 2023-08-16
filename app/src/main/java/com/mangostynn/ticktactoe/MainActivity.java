package com.mangostynn.ticktactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(  WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);

        playerOneTurn = findViewById(R.id.playerOneTurn);
        playerTwoTurn = findViewById(R.id.playerTwoTurn);

        boardTile1 = findViewById(R.id.tile1);
        boardTile2 = findViewById(R.id.tile2);
        boardTile3 = findViewById(R.id.tile3);
        boardTile4 = findViewById(R.id.tile4);
        boardTile5 = findViewById(R.id.tile5);
        boardTile6 = findViewById(R.id.tile6);
        boardTile7 = findViewById(R.id.tile7);
        boardTile8 = findViewById(R.id.tile8);
        boardTile9 = findViewById(R.id.tile9);

        combinationsList.add(new int[]{0,1,2});
        combinationsList.add(new int[]{3,4,5});
        combinationsList.add(new int[]{6,7,8});
        combinationsList.add(new int[]{0,3,6});
        combinationsList.add(new int[]{1,4,7});
        combinationsList.add(new int[]{2,5,8});
        combinationsList.add(new int[]{2,4,6});
        combinationsList.add(new int[]{0,4,8});

        String  getPlayerOneName = getIntent().getStringExtra("playerOne"),
                getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        playerOneTurn.setText(R.string.turnTrue);
        playerTwoTurn.setText(R.string.turnFalse);

        boardTile1.setOnClickListener(e->{
            if(isBoxSelected(0))
                performAction((ImageView)e,0);
        });
        boardTile2.setOnClickListener(e->{
            if(isBoxSelected(1))
                performAction((ImageView)e,1);
        });
        boardTile3.setOnClickListener(e->{
            if(isBoxSelected(2))
                performAction((ImageView)e,2);
        });
        boardTile4.setOnClickListener(e->{
            if(isBoxSelected(3))
                performAction((ImageView)e,3);
        });
        boardTile5.setOnClickListener(e->{
            if(isBoxSelected(4))
                performAction((ImageView)e,4);
        });
        boardTile6.setOnClickListener(e->{
            if(isBoxSelected(5))
                performAction((ImageView)e,5);
        });
        boardTile7.setOnClickListener(e->{
            if(isBoxSelected(6))
                performAction((ImageView)e,6);
        });
        boardTile8.setOnClickListener(e->{
            if(isBoxSelected(7))
                performAction((ImageView)e,7);
        });
        boardTile9.setOnClickListener(e->{
            if(isBoxSelected(8))
                performAction((ImageView)e,8);
        });

        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    private void performAction(ImageView imageView, int selectedBoxPosition){
        boxPositions[selectedBoxPosition] = playerTurn;

        if(playerTurn==1)
            playerOneAction(imageView);
        else
            playerTwoAction(imageView);
    }
    private void playerOneAction(ImageView imageView){
        imageView.setImageResource(R.drawable.cross);

        if(checkPlayerWin()){
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    playerOneName.getText()+" has won the match!",
                    MainActivity.this);
            winDialog.show();
        }
        else if(totalSelectedBoxes == 9){
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    "It is a Draw!",
                    MainActivity.this);
            winDialog.show();
        }
        else{
            changePlayerTurn(2);
            totalSelectedBoxes++;
        }
    }
    private void playerTwoAction(ImageView imageView){
        imageView.setImageResource(R.drawable.circle);
        if(checkPlayerWin()){
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    playerTwoName.getText()+" has won the match!",
                    MainActivity.this);
            winDialog.show();
        }
        else if(totalSelectedBoxes == 9){
            WinDialog winDialog = new WinDialog(
                    MainActivity.this,
                    "It is a Draw!",
                    MainActivity.this);
            winDialog.show();
        }
        else{
            changePlayerTurn(1);
            totalSelectedBoxes++;
        }
    }

    private void changePlayerTurn(int currentPlayerTurn){
        playerTurn = currentPlayerTurn;
        String  now="Your Turn!",
                wait="Wait for your Turn!";
        if(playerTurn == 1){
            playerOneTurn.setText(now);
            playerTwoTurn.setText(wait);
        }
        else{
            playerOneTurn.setText(wait);
            playerTwoTurn.setText(now);
        }
    }

    private boolean checkPlayerWin(){
        boolean response = false;
        for(int x=0; x<combinationsList.size(); x++){
            final int[] combination = combinationsList.get(x);
            if( boxPositions[combination[0]] == playerTurn &&
                boxPositions[combination[1]] == playerTurn &&
                boxPositions[combination[2]] == playerTurn)
                    response = true;
        }
        return response;
    }

    private boolean isBoxSelected(int boxPosition){
        return boxPositions[boxPosition] == 0;
    }

    public void restartMatch(){
        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=1;
        totalSelectedBoxes=1;
            boardTile1.setImageResource(R.drawable.tile_transparent);
            boardTile2.setImageResource(R.drawable.tile_transparent);
            boardTile3.setImageResource(R.drawable.tile_transparent);
            boardTile4.setImageResource(R.drawable.tile_transparent);
            boardTile5.setImageResource(R.drawable.tile_transparent);
            boardTile6.setImageResource(R.drawable.tile_transparent);
            boardTile7.setImageResource(R.drawable.tile_transparent);
            boardTile8.setImageResource(R.drawable.tile_transparent);
            boardTile9.setImageResource(R.drawable.tile_transparent);
    }

    private final List<int[]> combinationsList = new ArrayList<>();
    private int[] boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1, totalSelectedBoxes = 1;
    private TextView playerOneName, playerTwoName, playerOneTurn, playerTwoTurn;
    private ImageView   boardTile1,
                        boardTile2,
                        boardTile3,
                        boardTile4,
                        boardTile5,
                        boardTile6,
                        boardTile7,
                        boardTile8,
                        boardTile9;
}