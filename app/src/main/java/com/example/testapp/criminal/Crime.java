package com.example.testapp.criminal;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.UUID;

public class Crime extends Object {
    private UUID mId;
    private String myTitle;
    private DateFormat mDate;
    private Date date;
    private boolean mSolved;

    public Crime() {
        mId=UUID.randomUUID();
        date=new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString(){
        return myTitle;
    }

    public String getMyTitle() {
        return myTitle;
    }

    public void setMyTitle(String myTitle) {
        this.myTitle = myTitle;
    }

    public UUID getmId() {
        return mId;
    }

    public DateFormat getmDate() {
        return mDate;
    }

    public void setmDate(DateFormat mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
