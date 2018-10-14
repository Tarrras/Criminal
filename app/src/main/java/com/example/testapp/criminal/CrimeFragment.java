package com.example.testapp.criminal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID =
            "com.example.testapp.criminal.crime_id";
    public static final String DIALOG_DATE="date";
    public static final int REQUEST_DATE=0;

    private Crime crime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId=(UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        crime=CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_crime,container,false);
        mTitleField=(EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(crime.getMyTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                crime.setMyTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton=(Button)v.findViewById(R.id.crime_date);
        String currentDate=DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        mDateButton.setText(crime.getmDate().getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime()));
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getActivity()
                        .getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(crime.getDate());
                dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dialog.show(fm,DIALOG_DATE);
            }
        });

        mSolvedCheckBox=(CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(crime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                crime.setmSolved(b);
            }
        });
        return v;
    }

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle ars=new Bundle();
        ars.putSerializable(EXTRA_CRIME_ID,crimeId);
        CrimeFragment crimeFragment=new CrimeFragment();
        crimeFragment.setArguments(ars);
        return crimeFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode!=Activity.RESULT_OK) return;
        if(requestCode!=REQUEST_DATE){
            Date date=(Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            mDateButton.setText(crime.getDate().toString());
        }
    }
}
