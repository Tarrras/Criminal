package com.example.testapp.criminal;

import android.support.v4.app.Fragment;

import java.util.ResourceBundle;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
