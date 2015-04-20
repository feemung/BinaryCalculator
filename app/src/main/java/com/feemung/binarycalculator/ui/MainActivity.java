package com.feemung.binarycalculator.ui;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.feemung.binarycalculator.R;
import com.feemung.binarycalculator.ui.fragment.BaseFragment;


public class MainActivity extends ActionBarActivity {
    private Button selectBut[];
    private BaseFragment fragment[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                    break;
                case R.id.octalBut:
                    show(1);
                    break;
                case R.id.decimalBut:
                    show(2);
                    break;
                case R.id.hexadecimalBut:
                    show(3);
                    break;

            }
        }
    }
}
