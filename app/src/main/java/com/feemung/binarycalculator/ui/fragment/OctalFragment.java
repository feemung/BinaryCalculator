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


public class OctalFragment extends BaseFragment {
    private Logger l= Logger.getLogger(OctalFragment.class);



    public OctalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_octal, container, false);
        octalEdit=(EditText)view.findViewById(R.id.octal_editText);
        binaryTV=(TextView)view.findViewById(R.id.octal_binaryTextView);
        hexadecimalTV=(TextView)view.findViewById(R.id.octal_hexadecimalView);
        decimalTV=(TextView)view.findViewById(R.id.octal_decimalTV);

        octalEdit.addTextChangedListener(new TextWatcher() {

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

                if(Transform.checkIsEmpty(s.toString())){
                    transformAndUpdate("0");
                    l.d("binaryEdit checkisempty");
                    return;
                }
                editStart = octalEdit.getSelectionStart();
                editEnd = octalEdit.getSelectionEnd();

                if ((!Transform.checkIsOctal(s.toString()))) {

                    Toast.makeText(getActivity(),
                            "你输入的字数错误，请输入0到7的任意数字", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    octalEdit.setText(s);
                    octalEdit.setSelection(octalEdit.length());


                }else {
                    transformAndUpdate(Transform.octal2decimal(s.toString()));
                }
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
        decimalTV.setText(decimal);
        hexadecimalTV.setText(Transform.decimal2hexadecimal(decimal));
    }
    private void initData(String decimal){
        if(Transform.checkIsEmpty(decimal)){
            return;
        }
        octalEdit.setText(Transform.decimal2octal(decimal));
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
