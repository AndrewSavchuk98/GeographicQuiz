package com.example.geograficquiz;

public class Question {
    private boolean mIsAnswerTrue;
    private int mTextResultId;

    public Question(boolean isAnswerTrue, int textResultId) {
        mIsAnswerTrue = isAnswerTrue;
        mTextResultId = textResultId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }

    public int getTextResultId() {
        return mTextResultId;
    }

    public void setTextResultId(int textResultId) {
        mTextResultId = textResultId;
    }
}
