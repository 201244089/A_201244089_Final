package sampleandroid.a_201244089_final;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.*;
import java.io.*;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    Switch S_start;
    Chronometer chronometer;
    EditText et_adult,et_youth,et_child;
    RadioGroup dcgroup, timegroup;
    RadioButton r_normaldc, r_cashdc, r_memberdc,r_date,r_time;
    Button b_Pdone, b_gotime, b_tdone, b_back;
    TextView tv_total,tv_dc,tv_cost;
    ImageView imageView;
    TimePicker timePicker;
    CalendarView calendarView;
    LinearLayout timelayout,mainlayout;
    int dcCheck = 0;
    int year,month,day,hour,min;
    boolean personCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        S_start = (Switch)findViewById(R.id.s_start);
        chronometer = (Chronometer)findViewById(R.id.c_meter);
        et_adult = (EditText)findViewById(R.id.txtE_adult);
        et_youth = (EditText)findViewById(R.id.txtE_youth);
        et_child = (EditText)findViewById(R.id.txtE_child);
        dcgroup = (RadioGroup)findViewById(R.id.Dcgroup);
        timegroup = (RadioGroup)findViewById(R.id.timegroup);
        r_normaldc = (RadioButton)findViewById(R.id.r_Ndc);
        r_cashdc = (RadioButton)findViewById(R.id.r_Cdc);
        r_memberdc = (RadioButton)findViewById(R.id.r_Mdc);
        r_date = (RadioButton)findViewById(R.id.r_date);
        r_time = (RadioButton)findViewById(R.id.r_time);
        b_Pdone = (Button)findViewById(R.id.B_persondone);
        b_gotime =(Button)findViewById(R.id.b_gotime);
        b_back = (Button)findViewById(R.id.b_back);
        b_tdone = (Button)findViewById(R.id.b_timedone);
        tv_total = (TextView)findViewById(R.id.txtV_total);
        tv_cost = (TextView)findViewById(R.id.txtV_cost);
        tv_dc = (TextView)findViewById(R.id.txtV_dc);
        imageView = (ImageView)findViewById(R.id.i_view);
        timePicker = (TimePicker)findViewById(R.id.t_picker);
        calendarView = (CalendarView)findViewById(R.id.c_view);
        mainlayout = (LinearLayout)findViewById(R.id.mainLayout);
        timelayout = (LinearLayout)findViewById(R.id.timeLayout);

        S_start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    chronometer.setTextColor(Color.BLUE);
                    mainlayout.setVisibility(View.VISIBLE);
                }
                if(isChecked == false){
                    mainlayout.setVisibility(View.INVISIBLE);
                    chronometer.stop();
                    et_adult.setText("");
                    et_child.setText("");
                    et_youth.setText("");
                    tv_total.setText("Total : ");
                    tv_cost.setText("Cost : ");
                    tv_dc.setText("DC : ");

                }
            }
        });
        dcgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(r_normaldc.isChecked()){
                    imageView.setImageResource(R.drawable.normal);
                    dcCheck = 0;
                }
                if(r_cashdc.isChecked()){
                    imageView.setImageResource(R.drawable.cash);
                    dcCheck = 1;
                }
                if(r_memberdc.isChecked()){
                    imageView.setImageResource(R.drawable.member);
                    dcCheck = 2;
                }
            }
        });

        b_Pdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a,y,c,cost,dc,result;
                if(et_adult.getText() == null || et_youth.getText() == null || et_child.getText() == null){
                    Toast.makeText(getApplicationContext(),"인원을 입력하세요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    personCheck = true;
                    a = Double.parseDouble(et_adult.getText().toString());
                    y = Double.parseDouble(et_youth.getText().toString());
                    c = Double.parseDouble(et_child.getText().toString());
                    cost = (a * 15000) + (y * 12000) + (c * 8000);
                    dc = cost * (1 / 20);
                    if (dcCheck == 1) {
                        dc = cost * (1 / 10);
                    }
                    if (dcCheck == 2) {
                        dc = cost * (1 / 5);
                    }
                    result = cost - dc;
                    tv_total.setText("Total : " + a + y + c);
                    tv_dc.setText("DC : " + dc);
                    tv_cost.setText("Cost : " + result);
                }
            }
        });
        b_gotime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainlayout.setVisibility(View.INVISIBLE);
                timelayout.setVisibility(View.VISIBLE);
            }
        });
        timegroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(r_date.isChecked()){
                    calendarView.setVisibility(View.VISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                }
                if(r_time.isChecked()){
                    calendarView.setVisibility(View.INVISIBLE);
                    timePicker.setVisibility(View.VISIBLE);
                }
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year1, int month1, int dayOfMonth1) {
                year = year1;
                month = month1;
                day = dayOfMonth1;
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                min = minute;
            }
        });
        b_tdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(personCheck == false){
                    Toast.makeText(getApplicationContext(),"인원예약을 먼저 하세요.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),""+year+"년"+month+"월"+day+"일"+hour+"시"+min+"분 예약이 " +
                            "완료 되었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timelayout.setVisibility(View.INVISIBLE);
                mainlayout.setVisibility(View.VISIBLE);
            }
        });

    }

}
