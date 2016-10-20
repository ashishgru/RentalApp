package ashishrpa.timeplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.text.DateFormatSymbols;

public class Activity_Second extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    EditText editText;
    Intent intent, returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__second);

        getSupportActionBar().hide();

        intent = getIntent();
        editText =(EditText) findViewById(R.id.et_datedetails);
        imageView =(ImageView) findViewById(R.id.imageView1);
        imageView.setOnClickListener(this);

        int mMonth1 = intent.getIntExtra("mMonth1",1);
        int mDay1 = intent.getIntExtra("mDay1",1);
        int hour1 = intent.getIntExtra("hour1",1);
        int minute1 = intent.getIntExtra("minute1",1);

        int mMonth2 = intent.getIntExtra("mMonth2",1);
        int mDay2 = intent.getIntExtra("mDay2",1);
        int hour2 = intent.getIntExtra("hour2",1);
        int minute2 = intent.getIntExtra("minute2",1);

        String locationString = intent.getStringExtra("locationString");

        String FORMAT1, FORMAT2;
        if(hour1 > 12){
            hour1-=12;
            FORMAT1 ="PM";
        }
        else{
            FORMAT1 ="AM";
        }

        if(hour1 > 12){
            hour1-=12;
            FORMAT2 ="PM";
        }
        else{
            FORMAT2 ="AM";
        }

        String monthName1 = new DateFormatSymbols().getMonths()[mMonth1];
        String monthName2 = new DateFormatSymbols().getMonths()[mMonth2];

        editText.setText(new StringBuilder().append(locationString).append("\n").append(mDay1).append(" ").append(monthName1).append(",")
                .append(hour1).append(":").append(minute1).append(" ").append(FORMAT1)
                .append(" - ").append(mDay2).append(" ").append(monthName2).append(",")
                .append(hour2).append(":").append(minute2).append(" ").append(FORMAT2));

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cars_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        returnIntent = new Intent(Activity_Second.this,MainActivity.class);
        startActivity(returnIntent);
    }
}
