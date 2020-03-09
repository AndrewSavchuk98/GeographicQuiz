package com.example.geograficquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public final static String EXTRA_ANSWER_IS_TRUE = "com.example.geograficquiz.EXTRA_ANSWER_IS_TRUE";
    public final static String EXTRA_ANSWER_SHOWN = "com.example.geograficquiz.EXTRA_ANSWER_SHOWN";
    private final static String KEY_INDEX_OF_CHEAT = "com.example.geographicQuiz.index_of_cheat";

    private Button mAnswerButton;
    private TextView mTrueAnswer;
    private TextView mVersionView;
    private TextView mTextQuestion;
    private Button mNoButton;
    private boolean mAnswer = true;

    private boolean mAnswerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mTextQuestion = findViewById(R.id.text_question);
        mAnswerButton = findViewById(R.id.answer_button);
        mTrueAnswer = findViewById(R.id.true_answer);
        mNoButton = findViewById(R.id.no_button);
        mVersionView = findViewById(R.id.version_view);
        mVersionView.setText("API Level " + Build.VERSION.SDK_INT);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        if(savedInstanceState != null){
            mAnswer = savedInstanceState.getBoolean(KEY_INDEX_OF_CHEAT);
        }

    }

    public static Intent newIntent (Context context, boolean isAnswerTrue){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, isAnswerTrue);
        return intent;
    }
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.answer_button:
                mTrueAnswer.setText(String.valueOf(mAnswerIsTrue));
                setAnswerShowResult(mAnswer);
                break;
            case R.id.no_button:
                finish();
                break;
        }
    }
    private void setAnswerShowResult(boolean isAnswerShow){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShow);
        setResult(RESULT_OK,intent);
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_INDEX_OF_CHEAT,mAnswer);

    }
}
