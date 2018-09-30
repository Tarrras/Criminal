package com.example.testapp.criminal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes;
    public static final String TAG ="TAG";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime_title);
        mCrimes=CrimeLab.get(getActivity()).getmCrimes();

        CrimeAdapter adapter=new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, c.getMyTitle() + " was clicked");
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,crimes);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,null);
            }

            Crime crime=getItem(position);

            TextView titleTextView=(TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            TextView dateTextView=(TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            CheckBox solvedCheckBox =(CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            titleTextView.setText(crime.getMyTitle());
            dateTextView.setText(crime.getDate().toString());
            solvedCheckBox.setChecked(crime.ismSolved());
            return convertView;
        }
    }


}
