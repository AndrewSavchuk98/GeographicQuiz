package com.example.geograficquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private final static String KEY_INDEX = "com.example.geographicQuiz.index";
    private final static String KEY_INDEX_OF_CHEAT = "com.example.geographicQuiz.index_of_cheat";
    public static String TAG = "com.example.geographicQuiz.TAG";
    private static final int REQUEST_CODE_CHEAT = 0;

    private TextView mTextQuiz;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mCheatButton;

    private boolean mIsCheater;
    private int counter = 0;


    private Question[] mQuestions = new Question[]{
            new Question(true, R.string.question_Austria),
            new Question(false, R.string.question_Azerbaijan),
            new Question(true, R.string.question_Brazil),
            new Question(true, R.string.question_Bulgaria),
            new Question(true, R.string.question_Canada),
            new Question(false, R.string.question_China),
            new Question(true, R.string.question_Colombia),
            new Question(false, R.string.question_CostaRica),
            new Question(true, R.string.question_Egypt),
            new Question(false, R.string.question_Estonia),
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreviousButton = findViewById(R.id.previous_button);
        mTextQuiz = findViewById(R.id.textQuiz);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mCheatButton = findViewById(R.id.cheat_button);
        updateQuestion();

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(KEY_INDEX_OF_CHEAT,false);
        }
    }
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.true_button:
                checkAnswer(true);
                mTrueButton.setClickable(false);
                break;
            case R.id.false_button:
                checkAnswer(false);
                mFalseButton.setClickable(false);
                break;
            case R.id.next_button:
                mCurrentIndex = mCurrentIndex + 1;
                updateQuestion();
                mTrueButton.setClickable(true);
                mFalseButton.setClickable(true);
                mIsCheater = false;
                break;
            case R.id.previous_button:
                mCurrentIndex = mCurrentIndex - 1;
                updateQuestion();
                break;
            case R.id.cheat_button:
                boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                //startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);
                break;
        }
    }

    private void updateQuestion() {
        int indexNull = 0;
        if (mCurrentIndex < indexNull) {
            mCurrentIndex = 0;
            Toast.makeText(this, "It's a first question!", Toast.LENGTH_SHORT).show();
        } else if (mCurrentIndex >= mQuestions.length) {
            mCurrentIndex = mQuestions.length;
            Toast.makeText(this, "You right Answer is " + counter, Toast.LENGTH_SHORT).show();
            counter = 0;
        } else {
            int question = mQuestions[mCurrentIndex].getTextResultId();
            mTextQuiz.setText(question);
        }
    }

    private void checkAnswer(boolean userPressed) {
        boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
        int massageResultId;
        if (mIsCheater) {
            Toast.makeText(this, R.string.cheater, Toast.LENGTH_SHORT).show();
        } else {
            if (userPressed == answerIsTrue) {
                counter++;
                massageResultId = R.string.correct_toast;
            } else {
                massageResultId = R.string.incorrect_toast;
            }
            Toast.makeText(this, massageResultId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "OnStart() created");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "OnResume() created");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "OnPause() created");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(KEY_INDEX_OF_CHEAT,mIsCheater);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "OnStop() created");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "OnDestroy() created");
        super.onDestroy();
    }
}
