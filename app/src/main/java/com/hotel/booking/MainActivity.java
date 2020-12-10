package com.hotel.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


  //  ArrayList<String> tmproomNames = new ArrayList<>();
    Spinner sproom;
    ViewFlipper rvf;
    Button btnbook,btnnext;
    SeekBar sb;
    RadioButton rb1,rb2,rb3,rb4;
    ImageView img1,img2;
    CheckBox cb1,cb2,cb3;
    TextView tvrpn,tvnights,tvamt;
    public static double originalPrice =0;

    ArrayList<String> roomNames = new ArrayList<>();
    ArrayList<Room> roomList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        //fillTempRoom(roomname[0]);

        sproom = findViewById(R.id.sproomtype);
        btnbook = findViewById(R.id.btnbook);
        sb = findViewById(R.id.sbnights2);
        rb1 = findViewById(R.id.rbnormal);
        rb2 = findViewById(R.id.rbsuper);
        rb3 = findViewById(R.id.rblux);
        rb4 = findViewById(R.id.rbsuite);
        cb1 = findViewById(R.id.cbpark);
        cb2 = findViewById(R.id.cbbreak);
        cb3 = findViewById(R.id.cbcancel);
        tvrpn = findViewById(R.id.txtratepernight);
        tvnights = findViewById(R.id.txtnights);
        img1 = findViewById(R.id.imgone);
        img2 = findViewById(R.id.imgtwo);
        tvamt = findViewById(R.id.txtamount);


        //fill the first spinner with room names

        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,roomNames);
        sproom.setAdapter(aa);
        sproom.setOnItemSelectedListener(this);

        rb1.setOnClickListener(new RadioButtonsAction());
        rb2.setOnClickListener(new RadioButtonsAction());
        rb3.setOnClickListener(new RadioButtonsAction());
        rb4.setOnClickListener(new RadioButtonsAction());
        btnbook.setOnClickListener(new RadioButtonsAction());


        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                double currentprice = Double.parseDouble(tvrpn.getText().toString());
                if (cb2.isChecked())
                    currentprice += 20;
                else
                    currentprice -= 20;
                tvrpn.setText(String.valueOf(currentprice));
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        //    double cp = Double.parseDouble(tvrpn.getText().toString());

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          //      cp*=progress;
                tvnights.setText(String.valueOf(progress));
         //       tvrpn.setText(String.valueOf(cp));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    

    private void fillData() {

       roomList.add(new Room(140,"room1","room2","Room one "));
        roomList.add(new Room(120,"room3","room4","Room two "));
        roomList.add(new Room(190,"room5","room6","Room three "));
        roomList.add(new Room(130,"room7","room8","Room four "));
        roomList.add(new Room(160,"room9","room10","Room five "));


        for(int i=0;i<roomList.size();i++)
        {
            roomNames.add(roomList.get(i).getRoomname());
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

        double p = roomList.get(i).getPernightprice();

        tvrpn.setText(String.valueOf(roomList.get(i).getPernightprice()));
        originalPrice=roomList.get(i).getPernightprice();

        int imgid1 = getResources().getIdentifier(roomList.get(i).getRimgone(),"drawable",getPackageName());
        int imgid2 = getResources().getIdentifier(roomList.get(i).getRimgtwo(),"drawable",getPackageName());
        img1.setImageResource(imgid1);
        img2.setImageResource(imgid2);
        rb1.setChecked(true);

        if(cb1.isChecked())
            p += 25;

        if(cb2.isChecked())
            p += 20;

        tvrpn.setText(String.valueOf(p));
      //  cb2.setChecked(false);
       // cb3.setChecked(false);
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class RadioButtonsAction implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            if(view.getId()==R.id.rbnormal)
                tvrpn.setText(String.valueOf(originalPrice));

            else if(view.getId()==R.id.rbsuper)
                tvrpn.setText(String.format("%.2f",originalPrice*1.25));
            else if(view.getId()==R.id.rblux)
                tvrpn.setText(String.format("%.2f",originalPrice*1.5));
            else if(view.getId()==R.id.rbsuite)
                tvrpn.setText(String.format("%.2f",originalPrice*2));
            else if(view.getId()==R.id.btnbook) {
                double total = Double.parseDouble(tvrpn.getText().toString());
            }


        }
    }

    private class CheckBoxActions implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            double currentPrice = Double.parseDouble(tvamt.getText().toString());

            if(compoundButton.getId()==R.id.cbpark)
                if(cb1.isChecked())
                    currentPrice+=25;
                else
                    currentPrice-=25;

            if(compoundButton.getId()==R.id.cbbreak)
                if(cb2.isChecked())
                    currentPrice+=20;
                else
                    currentPrice-=20;
            if(compoundButton.getId()==R.id.cbcancel)
                if(cb3.isChecked())
                    currentPrice+=0.0;
                else
                    currentPrice-=0.0;

            tvrpn.setText(String.valueOf(currentPrice));



        }
    }

}