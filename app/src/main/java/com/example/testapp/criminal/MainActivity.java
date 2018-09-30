package com.example.testapp.criminal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
