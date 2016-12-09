package sampleandroid.a_201244089_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Switch S_start;
    Chronometer chronometer;
    EditText et_adult,et_youth,et_child;
    RadioGroup dcgroup;
    RadioButton r_normaldc, r_cashdc, r_memberdc,r_date,r_time;
    Button b_Pdone, b_gotime, b_tdone, b_back;
    TextView tv_total,tv_dc,tv_cost;
    ImageView imageView;
    TimePicker timePicker;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
