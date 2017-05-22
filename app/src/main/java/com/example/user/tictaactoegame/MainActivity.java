package com.example.user.tictaactoegame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; //0 for king
    int[] gameState = {2,2,2,2,2,2,2,2,2,}; // 2 means unplayed
    int[][] winningLocations = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8},
            {2,4,6}};
    boolean gameOver = false;


    public void gameLogic (View view){

        ImageView tappedView = (ImageView) view;

        int tappedLocation = Integer.parseInt(view.getTag().toString());
        if(gameState[tappedLocation] == 2 && !gameOver) {

            gameState[tappedLocation] = activePlayer;

            tappedView.setTranslationY(-3000f);

            if (activePlayer == 0) {

                tappedView.setImageResource(R.drawable.king);
                activePlayer = 1;

            } else if (activePlayer == 1) {

                tappedView.setImageResource(R.drawable.qeen);
                activePlayer = 0;

            }

            tappedView.animate().translationYBy(3000f).setDuration(500);


            String msg = "";



            for(int[] winningPosition : winningLocations){

               if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {

                 if (activePlayer == 0)
                    msg = "Qeen is Winner!";

                 if (activePlayer == 1)
                     msg = "King is Winner!";

                   LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                   winnerLayout.setVisibility(View.VISIBLE);

                   TextView winnerMsg = (TextView) findViewById(R.id.textView);
                   winnerMsg.setText(msg);

                gameOver = true;
            }

        }

        }
    }

    public void playAgain(View view){

        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);

        gameOver = false;
        activePlayer = 0;

        for(int i = 0; i < gameState.length; i++)
            gameState[i] = 2;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++)
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
}
