package com.example.vitalykulyk.simplequiz;

/**
 * Created by Vitaly Kulyk on 29.03.2016.
 */
public class Question {
    public final String mQuestion;
    public final String mFirstVariant;
    public final String mSecondVariant;
    public final String mThirdVariant;
    public final String mFourthVariant;

    public Question(String question, String firstVariant, String secondVariant,
                    String thirdVariant, String fourthVariant){
        mQuestion = question;
        mFirstVariant = firstVariant;
        mSecondVariant = secondVariant;
        mThirdVariant = thirdVariant;
        mFourthVariant = fourthVariant;
    }
}
