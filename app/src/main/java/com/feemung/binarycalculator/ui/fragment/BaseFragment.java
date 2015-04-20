package com.feemung.binarycalculator.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by laocow on 2015/4/19.
 */
public class BaseFragment extends Fragment{
    public EditText binaryEdit;
    public EditText hexadecimalEdit;
    public EditText decimalEdit;
    public EditText octalEdit;
    public TextView octalTV;
    public TextView decimalTV;
    public TextView hexadecimalTV;
    public TextView binaryTV;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public BaseFragment() {
        super();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
