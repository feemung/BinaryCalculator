package com.feemung.binarycalculator.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.feemung.binarycalculator.R;
import com.feemung.binarycalculator.cache.Cache;
import com.feemung.binarycalculator.lib.Transform;
import com.feemung.binarycalculator.log.Logger;


public class DecimalFragment extends BaseFragment {

    private Logger l= Logger.getLogger(DecimalFragment.class);



    public DecimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_decimal, container, false);
        decimalEdit=(EditText)view.findViewById(R.id.decimal_editText);
        octalTV=(TextView)view.findViewById(R.id.decimal_octalTextView);
        binaryTV=(TextView)view.findViewById(R.id.decimal_binaryTV);
        hexadecimalTV=(TextView)view.findViewById(R.id.decimal_HexadecimalTV);

        decimalEdit.addTextChangedListener(new TextWatcher() {

            private int editStart ;
            private int editEnd ;
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
                String str=s.toString();
                if(Transform.checkIsEmpty(str)){
                    str="0";
                }

                transformAndUpdate((str));

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
        binaryTV.setText((decimal));
        hexadecimalTV.setText(Transform.decimal2hexadecimal(decimal));
    }
    private void initData(String decimal){
        if(Transform.checkIsEmpty(decimal)){
            return;
        }
        decimalEdit.setText((decimal));
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
