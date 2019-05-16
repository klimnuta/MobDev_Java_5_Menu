package com.example.menudemojava;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Элементы экрана
    TextView tv;
    CheckBox chb;
    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int max,main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button);
        etInput.setOnClickListener(this);

        //Switch switch1 =  findViewById(R.id.level);
        //if (switch1 != null) {
        ///    switch1.setOnCheckedChangeListener(this);
        //}
        max = 100;
        main = (int)(Math.random() * max);


        // находим элементы
        tv = (TextView) findViewById(R.id.textView1);
        chb = (CheckBox) findViewById(R.id.chbExtMenu);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                String text = etInput.getText().toString();
                if(text.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("ВНИМАНИЕ")
                            .setMessage("Напишите хоть что-то")
                            .setCancelable(false)
                            .setNegativeButton("Ладно",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    int value = Integer.parseInt(etInput.getText().toString());
                    if(value < 1 || value > max){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("ВНИМАНИЕ")
                                .setMessage("Это число не подходит")
                                .setCancelable(false)
                                .setNegativeButton("Ладно",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else{
                        if(value < main ) {
                            tvInfo.setText(getResources().getString(R.string.behind));
                        }
                        if(value > main ) {
                            tvInfo.setText(getResources().getString(R.string.ahead));
                        }
                        if(value == main ) {
                            tvInfo.setText(getResources().getString(R.string.hit));
                        }
                    }
                }
                break;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // обновление меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        // пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
        menu.setGroupVisible(1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        //int id=item.getItemId();

        switch (item.getItemId()) {
            case R.id.menu_new:

            StringBuilder sb = new StringBuilder();
            etInput.setText("");
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            // Выведем в TextView информацию о нажатом пункте меню
            //sb.append("Item Menu");
           // sb.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
            //sb.append("\r\n itemId: " + String.valueOf(item.getItemId()));
            //sb.append("\r\n order: " + String.valueOf(item.getOrder()));
            sb.append("\r\n title: " + item.getTitle());
            tv.setText(sb.toString());

                return true;



            case R.id.menu_exit:
                this.finish();
                return true;


       // else return false;

            case R.id.menu_l1:
                max = 100;
                tvInfo.setText(getResources().getString(R.string.try_to_guess));
                main = (int)(Math.random() * max);
                return true;


            case R.id.menu_l2:
                max = 200;
                tvInfo.setText(getResources().getString(R.string.try_to_guess2));
                main = (int)(Math.random() * max);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
