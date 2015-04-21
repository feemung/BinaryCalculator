package com.feemung.binarycalculator.ui.fragment;

import android.os.Bundle;
import android.text.AndroidCharacter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.feemung.binarycalculator.log.Logger;
import com.feemung.binarycalculator.R;
import com.feemung.binarycalculator.lib.Transform;
import com.feemung.binarycalculator.cache.Cache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class BinaryFragment extends BaseFragment {

    private Logger l= Logger.getLogger(BinaryFragment.class);



    public BinaryFragment() {
        // Required empty public constructor
    }
    public static String stringFilter(String str)throws PatternSyntaxException {

        String regEx = "[2-9]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        return m.replaceAll("");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_binary, container, false);
        binaryEdit=(EditText)view.findViewById(R.id.binary_customEditTextAsKeyboard);
        octalTV=(TextView)view.findViewById(R.id.binary_octalTextView);
        decimalTV=(TextView)view.findViewById(R.id.binary_DecimalTV);
        hexadecimalTV=(TextView)view.findViewById(R.id.binary_HexadecimalTV);

        binaryEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                l.d("onCreateView#beforeTextChanged");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                l.d("onCreateView#onTextChanged");

            }

            @Override
            public void afterTextChanged(Editable s) {
                l.d("onCreateView#afterTextChanged->text:%s",s.toString());

                if(Transform.checkIsEmpty(s.toString())){
                    transformAndUpdate("0");
                    l.d("binaryEdit checkisempty");
                    return;
                }
                    transformAndUpdate(Transform.binary2decimal(s.toString()));
            }
        });
        return view;
    }



    public void transformAndUpdate(String decimal){
        if(Transform.checkIsEmpty(decimal)){
            return;
        }
        Cache.getInstance().setInput(decimal);
        octalTV.setText(Transform.decimal2octal(decimal));
        decimalTV.setText((decimal));
        hexadecimalTV.setText(Transform.decimal2hexadecimal(decimal));
    }
    private void initData(String decimal){
        if(Transform.checkIsEmpty(decimal)){
            return;
        }
        binaryEdit.setText(Transform.decimal2binary(decimal));
        transformAndUpdate(decimal);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        l.d("onHiddenChanged#:%s",String.valueOf(hidden));
        if(hidden==false){
           String input= Cache.getInstance().getInput();

           initData(input);

        }
    }
}
