/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.quiz;
 *
 */

package com.example.android.quiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quiz.R;

import java.text.NumberFormat;

//Quiz App

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //For Radio Button

    public void firstQuestionClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.first_A:
                if (checked)
                    break;
            case R.id.first_B:
                if (checked)
                    break;
        }
    }

     //Make sure that user can only choose the two of the answers not all of them.
    public void checkTwoBox(View view) {
        CheckBox firstcheck = (CheckBox) findViewById(R.id.fifth_A);
        CheckBox secondcheck = (CheckBox) findViewById(R.id.fifth_B);
        CheckBox thirdcheck = (CheckBox) findViewById(R.id.fifth_C);
        if (firstcheck.isChecked() && secondcheck.isChecked()) {
            thirdcheck.setChecked(false);
        }
        if (thirdcheck.isChecked() && secondcheck.isChecked()) {
            firstcheck.setChecked(false);
        }
        if (thirdcheck.isChecked() && firstcheck.isChecked()) {
            secondcheck.setChecked(false);
        }
    }


    //Show the result

    public void submitResult(View view) {
        //figure out if the user choose the right answer
        RadioButton firstRightBox = (RadioButton) findViewById(R.id.first_A);
        boolean hasClickedFirstA = firstRightBox.isChecked();

        RadioButton secondRightBox = (RadioButton) findViewById(R.id.second_B);
        boolean hasClickedSecondB = secondRightBox.isChecked();

        RadioButton thirdRightBox = (RadioButton) findViewById(R.id.third_B);
        boolean hasClickedThirdB = thirdRightBox.isChecked();

        RadioButton fourthRightBox = (RadioButton) findViewById(R.id.fourth_A);
        boolean hasClickedFourthA = fourthRightBox.isChecked();

        CheckBox fifthRightBox = (CheckBox) findViewById(R.id.fifth_A);
        boolean hasClickedFifthA = fifthRightBox.isChecked();

        CheckBox fifthSecondRightBox = (CheckBox) findViewById(R.id.fifth_B);
        boolean hasClickedFifthB = fifthSecondRightBox.isChecked();

        EditText answerText = (EditText) findViewById(R.id.answer);
        String LeoTolstoy =  answerText.getText().toString();

        //figure out if the user choose the wrong answer
        RadioButton firstWrongBox = (RadioButton) findViewById(R.id.first_B);
        boolean hasClickedFirstB = firstWrongBox.isChecked();

        RadioButton secondWrongBox = (RadioButton) findViewById(R.id.second_A);
        boolean hasClickedSecondA = secondWrongBox.isChecked();

        RadioButton thirdWrongBox = (RadioButton) findViewById(R.id.third_A);
        boolean hasClickedThirdA = thirdWrongBox.isChecked();

        RadioButton fourthWrongBox = (RadioButton) findViewById(R.id.fourth_B);
        boolean hasClickedFourthB = fourthWrongBox.isChecked();

        CheckBox fifthWrongBox = (CheckBox) findViewById(R.id.fifth_C);
        boolean hasClickedFifthC = fifthWrongBox.isChecked();

        // find user's name
        EditText nameEditText = (EditText) findViewById(R.id.name_view);
        String writtenName =  nameEditText.getText().toString();

        int correctAnswer = calculateCorrectAnswer(hasClickedFirstA,hasClickedSecondB,hasClickedThirdB,hasClickedFourthA,hasClickedFifthA,hasClickedFifthB,hasClickedFifthC,LeoTolstoy);
        int wrongAnswer = calculateWrongAnswer(hasClickedFirstB,hasClickedSecondA,hasClickedThirdA,hasClickedFourthB,hasClickedFifthC,hasClickedFifthA,hasClickedFifthB,LeoTolstoy);
        int emptyAnswer = calculateEmptyAnswer(hasClickedFirstA,hasClickedSecondB,hasClickedThirdB,hasClickedFourthA,hasClickedFifthA,hasClickedFifthB,hasClickedFifthC,LeoTolstoy,hasClickedFirstB,hasClickedSecondA,hasClickedThirdA,hasClickedFourthB);
        String quizMessage = createOrderSummary(writtenName, correctAnswer, wrongAnswer, emptyAnswer);

        // Toast Message
        String toast_1 = getString(R.string.toast_1);
        String toast_2 = getString(R.string.toast_2);
        String toast_3 = getString(R.string.toast_3);

        Toast.makeText(MainActivity.this,  toast_1 + " " + correctAnswer + " " + toast_2 + " \n" + toast_3, Toast.LENGTH_LONG).show();

        displayMessage(quizMessage);

        // Change the image.
        if (correctAnswer < 3 ) {
            ImageView newImageAdd = (ImageView) findViewById(R.id.android_goodluck_image_view);
            newImageAdd.setImageResource(R.drawable.sorry);
        } else if ((correctAnswer <= 4) && (correctAnswer>=3)) {
            ImageView newImageAdd = (ImageView) findViewById(R.id.android_goodluck_image_view);
            newImageAdd.setImageResource(R.drawable.goodbye4);
        } else {
            ImageView newImageAdd = (ImageView) findViewById(R.id.android_goodluck_image_view);
            newImageAdd.setImageResource(R.drawable.awesomme2);
        }
    }

    //For email intent

    public void sendButton(View view) {

        //figure out if the user choose the right answer
        RadioButton firstRightBox = (RadioButton) findViewById(R.id.first_A);
        boolean hasClickedFirstA = firstRightBox.isChecked();

        RadioButton secondRightBox = (RadioButton) findViewById(R.id.second_B);
        boolean hasClickedSecondB = secondRightBox.isChecked();

        RadioButton thirdRightBox = (RadioButton) findViewById(R.id.third_B);
        boolean hasClickedThirdB = thirdRightBox.isChecked();

        RadioButton fourthRightBox = (RadioButton) findViewById(R.id.fourth_A);
        boolean hasClickedFourthA = fourthRightBox.isChecked();

        CheckBox fifthRightBox = (CheckBox) findViewById(R.id.fifth_A);
        boolean hasClickedFifthA = fifthRightBox.isChecked();

        CheckBox fifthSecondRightBox = (CheckBox) findViewById(R.id.fifth_B);
        boolean hasClickedFifthB = fifthSecondRightBox.isChecked();

        EditText answerText = (EditText) findViewById(R.id.answer);
        String LeoTolstoy =  answerText.getText().toString();

        //figure out if the user choose the wrong answer
        RadioButton firstWrongBox = (RadioButton) findViewById(R.id.first_B);
        boolean hasClickedFirstB = firstWrongBox.isChecked();

        RadioButton secondWrongBox = (RadioButton) findViewById(R.id.second_A);
        boolean hasClickedSecondA = secondWrongBox.isChecked();

        RadioButton thirdWrongBox = (RadioButton) findViewById(R.id.third_A);
        boolean hasClickedThirdA = thirdWrongBox.isChecked();

        RadioButton fourthWrongBox = (RadioButton) findViewById(R.id.fourth_B);
        boolean hasClickedFourthB = fourthWrongBox.isChecked();

        CheckBox fifthWrongBox = (CheckBox) findViewById(R.id.fifth_C);
        boolean hasClickedFifthC = fifthWrongBox.isChecked();

        // find user's name
        EditText nameEditText = (EditText) findViewById(R.id.name_view);
        String writtenName =  nameEditText.getText().toString();

        int correctAnswer = calculateCorrectAnswer(hasClickedFirstA,hasClickedSecondB,hasClickedThirdB,hasClickedFourthA,hasClickedFifthA,hasClickedFifthB,hasClickedFifthC,LeoTolstoy);
        int wrongAnswer = calculateWrongAnswer(hasClickedFirstB,hasClickedSecondA,hasClickedThirdA,hasClickedFourthB,hasClickedFifthC,hasClickedFifthA,hasClickedFifthB,LeoTolstoy);
        int emptyAnswer = calculateEmptyAnswer(hasClickedFirstA,hasClickedSecondB,hasClickedThirdB,hasClickedFourthA,hasClickedFifthA,hasClickedFifthB,hasClickedFifthC,LeoTolstoy,hasClickedFirstB,hasClickedSecondA,hasClickedThirdA,hasClickedFourthB);
        String quizMessage = createOrderSummary(writtenName, correctAnswer, wrongAnswer, emptyAnswer);

        // Intent to Gmail
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz Results for " + writtenName);
        intent.putExtra(Intent.EXTRA_TEXT, quizMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the result
     * @param addName is the name written in the edit text.
     * @param correctAnswer is total number of correct answer
     * @param wrongAnswer is total number of wrong answer
     * @param emptyAnswer is total number of empty answer
     * @return text summary
     *
     */
    private String createOrderSummary(String addName, int correctAnswer, int wrongAnswer, int emptyAnswer) {
        String msg1 = getString(R.string.thank1);
        String msg2 = getString(R.string.thank2);
        String msg3 = getString(R.string.total_correct);
        String msg4 = getString(R.string.total_wrong);
        String msg5 = getString(R.string.total_empty1);
        String msg6 = getString(R.string.total_empty2);
        String msg7 = getString(R.string.final_msg1);
        String msg8 = getString(R.string.final_msg2);
        String msg9 = getString(R.string.final_msg3);

        String quizMessage = msg1 + " " + addName + " " + msg2;
        quizMessage += "\n" + msg3 + " " + correctAnswer;
        quizMessage += "\n" + msg4 + " " + wrongAnswer;
        quizMessage += "\n" + msg5 + " " + emptyAnswer + " " + msg6;
        if (correctAnswer <= wrongAnswer) {
            quizMessage += "\n" + msg7;
        } else {
            quizMessage += "\n" + msg8;
        }
        quizMessage += "\n" + msg9;
        return quizMessage;
    }

    //Calculates correct

    private int calculateCorrectAnswer(boolean firstA, boolean secondB, boolean thirdB, boolean fourthA, boolean fifthA, boolean fifthB, boolean fifthC, String LeoTolstoy) {
        int correct = 0;
        if (firstA) {
            correct = correct + 1;
        }
        if (secondB) {
            correct = correct + 1;
        }
        if (thirdB) {
            correct = correct + 1;
        }
        if (fourthA) {
            correct = correct + 1;
        }
        if (fifthA && fifthB && !fifthC) {
            correct = correct + 1;
        }
        if (LeoTolstoy.equals("Leo Tolstoy")) {
            correct = correct + 1;
        }
        int correctAnswer = correct;
        return correctAnswer;
    }

    //Calculates false

    private int calculateWrongAnswer(boolean firstB, boolean secondA, boolean thirdA, boolean fourthB, boolean fifthC,boolean fifthA, boolean fifthB, String LeoTolstoy) {
        int wrong = 0;
        if (firstB){
            wrong = wrong + 1;
        }
        if (secondA) {
            wrong =wrong + 1;
        }
        if (thirdA) {
            wrong =wrong + 1;
        }
        if (fourthB) {
            wrong =wrong + 1;
        }
        if ((fifthA && fifthC) || (fifthB && fifthC)) {
            wrong = wrong + 1;
        }
        if (!LeoTolstoy.equals("Leo Tolstoy") && !LeoTolstoy.equals("")) {
            wrong = wrong + 1;
        }
        int wrongAnswer = wrong;
        return wrongAnswer;
    }

    //calculate empty questions
    private int calculateEmptyAnswer(boolean firstA, boolean secondB, boolean thirdB, boolean fourthA, boolean fifthA, boolean fifthB, boolean fifthC, String LeoTolstoy, boolean firstB, boolean secondA, boolean thirdA, boolean fourthB) {
        int empty = 0;
        if (!firstA && !firstB) {
            empty = empty + 1;
        }
        if (!secondA && !secondB) {
            empty = empty + 1;
        }
        if (!thirdA && !thirdB) {
            empty = empty + 1;
        }
        if (!fourthA && !fourthB) {
            empty = empty + 1;
        }
        if ((!fifthA && !fifthB && !fifthC) || (fifthA && !fifthB && !fifthC) || (fifthB && !fifthA && !fifthC) || (fifthC && !fifthA && !fifthB)) {
            empty = empty + 1;
        }
        if (LeoTolstoy.equals("")) {
            empty = empty + 1;
        }
        int emptyAnswer = empty;
        return emptyAnswer;
    }

    //This method displays the given text on the screen.
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.result_text_view);
        orderSummaryTextView.setText(message);
    }

}