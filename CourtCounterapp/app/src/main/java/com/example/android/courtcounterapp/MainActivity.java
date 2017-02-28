package com.example.android.courtcounterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int teamA = 0;
    int teamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void resetButton(View v) {
        teamA = 0;
        teamB = 0;
        displayForTeamA(teamA);
        displayForTeamB(teamB);
    }

    public void plus1A(View view) {
        teamA += +1;
        displayForTeamA(teamA);
    }

    public void plus2A(View view) {
        teamA += +2;
        displayForTeamA(teamA);
    }

    public void plus3A(View view) {
        teamA += +3;
        displayForTeamA(teamA);
    }

    public void plus1B(View view) {
        teamB += 1;
        displayForTeamB(teamB);
    }

    public void plus2B(View view) {
        teamB += 2;
        displayForTeamB(teamB);
    }

    public void plus3B(View view) {
        teamB += 3;
        displayForTeamB(teamB);
    }


    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamAscore);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamBscore);
        scoreView.setText(String.valueOf(score));
    }
}
