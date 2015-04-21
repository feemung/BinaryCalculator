package com.feemung.binarycalculator.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.feemung.binarycalculator.R;
import com.feemung.binarycalculator.ui.fragment.BaseFragment;

import org.w3c.dom.Text;


public class MainActivity extends Activity {
    private Button selectBut[];
    private BaseFragment fragment[];
    private TextView titleTV;
    private Button aboutBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        init();
    }
    private void init(){
        aboutBut=(Button)findViewById(R.id.about_button);
        aboutBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        titleTV=(TextView)findViewById(R.id.title_textView);
        selectBut=new Button[4];
        selectBut[0]=(Button)findViewById(R.id.binaryBut);
        selectBut[1]=(Button)findViewById(R.id.octalBut);
        selectBut[2]=(Button)findViewById(R.id.decimalBut);
        selectBut[3]=(Button)findViewById(R.id.hexadecimalBut);
        selectBut[0].setOnClickListener(new SelectButOnClickListener());
        selectBut[1].setOnClickListener(new SelectButOnClickListener());
        selectBut[2].setOnClickListener(new SelectButOnClickListener());
        selectBut[3].setOnClickListener(new SelectButOnClickListener());
        fragment=new BaseFragment[4];
        fragment[0]=(BaseFragment)getFragmentManager().findFragmentById(R.id.binaryFragment);
        fragment[1]=(BaseFragment)getFragmentManager().findFragmentById(R.id.octalFragment);
        fragment[2]=(BaseFragment)getFragmentManager().findFragmentById(R.id.decimalFragment);
        fragment[3]=(BaseFragment)getFragmentManager().findFragmentById(R.id.hexadecimalFragment);
        show(0);
    }

    private void show(int which){
        selectBut[0].setTextColor(Color.BLACK);
        selectBut[1].setTextColor(Color.BLACK);
        selectBut[2].setTextColor(Color.BLACK);
        selectBut[3].setTextColor(Color.BLACK);
        selectBut[which].setTextColor(Color.GREEN);
        FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragment[0]).hide(fragment[1]).hide(fragment[2])
                .hide(fragment[3]).show(fragment[which]).commit();
    }
    private class SelectButOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.binaryBut:
                    show(0);
                    titleTV.setText("二进制");
                    break;
                case R.id.octalBut:
                    show(1);
                    titleTV.setText("八进制");
                    break;
                case R.id.decimalBut:
                    show(2);
                    titleTV.setText("十进制");
                    break;
                case R.id.hexadecimalBut:
                    show(3);
                    titleTV.setText("十六进制");
                    break;


            }
        }
    }


}
