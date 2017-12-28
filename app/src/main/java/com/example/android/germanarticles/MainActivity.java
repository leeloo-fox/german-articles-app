package com.example.android.germanarticles;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int correctAnswers = 0;
    int wrongAnswers = 0;
    int currentQuestion = 0;

    String[] questions = new String[]{"Tisch", "Sessel", "Wasser", "Sofa", "Spiegel",
            "Bahnhof", "Apfel", "Schlüssel", "Monat", "Bus"};
    int[] answers = new int[]{R.id.button_der, R.id.button_der, R.id.button_das, R.id.button_das, R.id.button_der,
            R.id.button_der, R.id.button_der, R.id.button_der, R.id.button_der, R.id.button_der};

    Button buttonDer;
    Button buttonDie;
    Button buttonDas;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize buttons
        buttonDer = (Button) findViewById(R.id.button_der);
        buttonDie = (Button) findViewById(R.id.button_die);
        buttonDas = (Button) findViewById(R.id.button_das);
        buttonNext = (Button) findViewById(R.id.button_next);

        // set counts to 0
        display(getString(R.string.correct_count, correctAnswers), R.id.correct_count);
        display(getString(R.string.wrong_count, wrongAnswers), R.id.wrong_count);
        display(getString(R.string.total_count, currentQuestion, questions.length), R.id.total_count);
    }

    private void display(String what, int viewId) {
        TextView quantityTextView = (TextView) findViewById(viewId);
        quantityTextView.setText(what);
    }

    private void clickAnswer(int buttonClickedId) {
        // Disable buttons
        buttonDer.setEnabled(false);
        buttonDie.setEnabled(false);
        buttonDas.setEnabled(false);

        Button clickedButton = (Button) findViewById(buttonClickedId);
        Button correctButton = (Button) findViewById(answers[currentQuestion]);

        if (answers[currentQuestion] == buttonClickedId) {
            correctAnswers += 1;
            display(getString(R.string.correct_count, correctAnswers), R.id.correct_count);
            // color the button green
            clickedButton.setTextColor(getResources().getColor(R.color.colorCorrect));
            // create a toast "Correct"
            Toast.makeText(this, R.string.toast_corect, Toast.LENGTH_SHORT).show();
        } else {
            wrongAnswers += 1;
            display(getString(R.string.wrong_count, wrongAnswers), R.id.wrong_count);
            // color the button red
            clickedButton.setTextColor(getResources().getColor(R.color.colorWrong));
            // color the correct answer green
            correctButton.setTextColor(getResources().getColor(R.color.colorCorrect));
            // create a toast "Wrong"
            Toast.makeText(this, R.string.toast_wrong, Toast.LENGTH_SHORT).show();
        }
        currentQuestion += 1;
        display(getString(R.string.total_count, currentQuestion, questions.length), R.id.total_count);

        if (currentQuestion >= questions.length) {
            buttonNext.setText(getText(R.string.repeat_button));
            currentQuestion = 0;
        }
    }

    public void clickDer(View view) {
        clickAnswer(R.id.button_der);
    }

    public void clickDie(View view) {
        clickAnswer(R.id.button_die);
    }

    public void clickDas(View view) {
        clickAnswer(R.id.button_das);
    }

    public void clickNext(View view) {
        if (currentQuestion == 0) {
            // that's "REPEAT" button

            // reset counts
            display(getString(R.string.correct_count_initial), R.id.correct_count);
            display(getString(R.string.wrong_count_initial), R.id.wrong_count);
            display(getString(R.string.total_count_initial), R.id.total_count);

            correctAnswers = 0;
            wrongAnswers = 0;

            // change button text back to "NEXT"
            buttonNext.setText(getText(R.string.next_button));
        }

        // show new question
        display(questions[currentQuestion], R.id.question);

        // enable buttons
        buttonDer.setEnabled(true);
        buttonDie.setEnabled(true);
        buttonDas.setEnabled(true);

        // reset button text color
        buttonDer.setTextColor(Color.BLACK);
        buttonDie.setTextColor(Color.BLACK);
        buttonDas.setTextColor(Color.BLACK);
    }
}
