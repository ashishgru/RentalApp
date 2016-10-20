package ashishrpa.timeplace;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.Address;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Location currentLocation;
    GPSTracker gps;
    String locationString;
    DatePicker datePicker1, datePicker2;
    TimePicker timePicker1, timePicker2;
    EditText editText;
    Button searchButton;
    //MyDate myDate;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gps = new GPSTracker(MainActivity.this);
        editText = (EditText) findViewById(R.id.ed_location);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            currentLocation = gps.getLocation();
            //(new GetAddressTask(this)).execute(currentLocation);

            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            Log.e("Your Lat: ",  latitude + " Long: " + longitude);
            locationString = "Location Lat: "  + latitude + " Long: " + longitude;
            editText.setText(locationString);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        searchButton = (Button) findViewById(R.id.searchButton);
        datePicker1 = (DatePicker) findViewById(R.id.dp_datepicker1);
        timePicker1 =(TimePicker) findViewById(R.id.tp_timePicker1);
        datePicker2 = (DatePicker) findViewById(R.id.dp_datepicker2);
        timePicker2 =(TimePicker) findViewById(R.id.tp_timePicker2);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int mMonth1 = datePicker1.getMonth();
                int mDay1 = datePicker1.getDayOfMonth();
                int hour1 = timePicker1.getCurrentHour();
                int minute1 = timePicker1.getCurrentMinute();

                int mMonth2 = datePicker2.getMonth();
                int mDay2 = datePicker2.getDayOfMonth();
                int hour2 = timePicker2.getCurrentHour();
                int minute2 = timePicker2.getCurrentMinute();

                intent = new Intent(MainActivity.this,Activity_Second.class);
                intent.putExtra("myDay1",mDay1);
                intent.putExtra("mMonth1",mMonth1);
                intent.putExtra("hour1",hour1);
                intent.putExtra("minute1",minute1);

                intent.putExtra("myDay2",mDay2);
                intent.putExtra("mMonth2",mMonth2);
                intent.putExtra("hour2",hour2);
                intent.putExtra("minute2",minute2);

                intent.putExtra("locationString",locationString);

                startActivity(intent);
            }
        });

    }

    /*
   * Subclass of AsyncTask used to get
   * address corresponding to the given latitude & longitude.
   */
    private class GetAddressTask extends AsyncTask<Location, Void, String> {
        Context mContext;

        public GetAddressTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected void onPostExecute(String address) {
            // Display the current address in the UI and save in locationString
            editText.setText(address);
            locationString = address;
        }

        @Override
        protected String doInBackground(Location... params) {
            Geocoder geocoder =new Geocoder(mContext, Locale.getDefault());

            // Get the current location from the input parameter list
            Location loc = params[0];

            // Create a list to contain the result address
            List <Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(loc.getLatitude(),loc.getLongitude(), 1);
            }

            catch (IOException e1) {
                Log.e("LocationSampleActivity","IO_Exception_in_getFromLocation()");
                e1.printStackTrace();
                return ("IO Exception trying to get address");
            }

            catch (IllegalArgumentException e2) {
                String errorString = "Illegal arguments " +
                        Double.toString(loc.getLatitude()) +" , " +Double.toString(loc.getLongitude()) +" passed to address service";
                Log.e("LocationSampleActivity", errorString);
                e2.printStackTrace();
                return errorString;
            }
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String addressText;
                addressText = address.getLocality() + address.getCountryName();
                Log.i("A",addressText);
                return addressText;
            }
            else {
                return "No address found";
            }
        }
    }// AsyncTask class end

}
