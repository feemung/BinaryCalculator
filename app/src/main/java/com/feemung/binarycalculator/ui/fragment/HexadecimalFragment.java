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

import com.feemung.binarycalculator.log.Logger;
import com.feemung.binarycalculator.R;
import com.feemung.binarycalculator.lib.Transform;
import com.feemung.binarycalculator.cache.Cache;


public class HexadecimalFragment extends BaseFragment {
    private Logger l= Logger.getLogger(OctalFragment.class);

    public HexadecimalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_hexadecimal, container, false);
        hexadecimalEdit=(EditText)view.findViewById(R.id.hexadecimal_customEditText);
        binaryTV=(TextView)view.findViewById(R.id.hexadecimal_binaryTextView);
        octalTV=(TextView)view.findViewById(R.id.hexadecimal_octalTextView);
        decimalTV=(TextView)view.findViewById(R.id.hexadecimal_DecimalTV);

        hexadecimalEdit.addTextChangedListener(new TextWatcher() {

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

                    transformAndUpdate(Transform.hexadecimal2decimal(s.toString()));

            }
        });
        return view;
    }

    private void transformAndUpdate(String decimal){
        if(Transform.checkIsEmpty(decimal)){
            return;
        }
        Cache.getInstance().setInput(decimal);
        binaryTV.setText(Transform.decimal2binary(decimal));
        octalTV.setText(Transform.decimal2octal(decimal));
        decimalTV.setText((decimal));
    }
    private void initData(String decimal){
        if(Transform.checkIsEmpty(decimal)){
            return;
        }
        hexadecimalEdit.setText(Transform.decimal2hexadecimal(decimal));
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
