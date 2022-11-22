package com.example.temphumprojectlucascavataio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback {

    public float maxTemp = 40;
    public float minTemp = 10;

    public float maxHum = 80;
    public float minHum = 10;

    public float temp;
    public float hum;
    public String readTime;

    public Read read;

    public ArrayList<Read> lecturesList = new ArrayList<>();

    TextView tvTemp;
    TextView tvHum;
    TextView tvTime;

    Button readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemp = findViewById(R.id.tvTemp);
        tvHum = findViewById(R.id.tvHum);
        tvTime = findViewById(R.id.tvTime);
    }

    public void readTemperature(View view) {

        SharedPreferences prefs = getSharedPreferences("userPrefs", 0);

        SharedPreferences prefsStr = PreferenceManager.getDefaultSharedPreferences(this);

        readButton = findViewById(R.id.readTempHumButton);

        maxTemp = prefs.getInt("maxTemp", 0);
        minTemp = prefs.getInt("minTemp", 0);

        maxHum = prefs.getInt("maxHum", 0);
        minHum = prefs.getInt("minHum", 0);

        String ip = prefsStr.getString("deviceIp", "DEFAULT");
        Log.d("ip",ip+"");

        System.out.println(maxTemp);
        System.out.println(minTemp);

        readButton.setEnabled(false);

        Call<Read> call = TempHumApiAdapter.getApiService(ip).readTempHum();

        call.enqueue(new Callback<Read>() {
            @Override
            public void onResponse(Call<Read> call, Response<Read> response) {

                if(response.isSuccessful()){

                    Read read = response.body();

                    if(read.GetReadTime() != null){

                        temp = read.GetTemperature();

                        hum = read.GetHumidity();

                        readTime = read.GetReadTime();

                        tvTemp.setText(temp + "ºC");
                        tvHum.setText(hum + "%");
                        tvTime.setText(readTime);

                        lecturesList.add(read);
                    }

                    sendCheck(temp, hum);
                }
            }

            @Override
            public void onFailure(Call<Read> call, Throwable t) {

                String error ="lecture error: " + t.toString() + " try in a few seconds...";

                showErrorMessage(error);

                readButton.setEnabled(true);
            }

        });
    }

    public void showErrorMessage(String _error) {
        Toast.makeText(this, _error, Toast.LENGTH_SHORT).show();
    }

    public void showSuccessMessage(String _event) {
        Toast.makeText(this, _event, Toast.LENGTH_SHORT).show();
    }

    public void sendCheck(float tempRead, float humRead) {

        SharedPreferences prefs = getSharedPreferences("userPrefs", 0);
        String respTempOk;
        String respHumOk;

        boolean tempOk = tempRead > minTemp && tempRead < maxTemp;
        boolean humOk = humRead > minHum && humRead < maxHum;

        if(tempOk){
            respTempOk = "1";
        } else {
            respTempOk = "0";
        }

        if(humOk){
            respHumOk = "1";
        } else {
            respHumOk = "0";
        }

        String event = "Turning leds on...";

        showSuccessMessage(event);

        String ip = prefs.getString("deviceIp", "");

        Call<Read> call = TempHumApiAdapter.getApiService(ip).sendTempHumLightAdvice(respTempOk, respHumOk);
        call.enqueue(new Callback<Read>() {

            @Override
            public void onResponse(Call<Read> call, Response<Read> response) {

                readButton.setEnabled(true);
            }

            @Override
            public void onFailure(Call<Read> call, Throwable t) {
                String error = "error turning leds: " + t.toString();

                showErrorMessage(error);

                readButton.setEnabled(true);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pref_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                Toast.makeText(this, "preferences", Toast.LENGTH_SHORT).show();
                Intent intentPref = new Intent(this, PreferencesActivity.class);
                startActivity(intentPref);
                return true;
            case R.id.lectureHistory:
                Intent intentHistory = new Intent(this, LectureHistoryActivity.class);
                intentHistory.putExtra("lecturesList", (ArrayList<Read>) lecturesList);
                startActivity(intentHistory);
                return true;
            case R.id.testLeds:

                SharedPreferences prefs = getSharedPreferences("userPrefs", 0);

                readButton.setEnabled(false);

                String event = "Executing leds test...";

                showSuccessMessage(event);

                String ip = prefs.getString("deviceIp", "");

                Call<Read> call = TempHumApiAdapter.getApiService(ip).executeTest();

                call.enqueue(new Callback<Read>() {
                    @Override
                    public void onResponse(Call<Read> call, Response<Read> response) {

                        String event = "Leds tested successfuLly";

                        readButton.setEnabled(true);
                    }

                    @Override
                    public void onFailure(Call<Read> call, Throwable t) {

                        String error ="test error: " + t.toString() + "cant execute the leds test, or maybe is already executing. Try in a few seconds...";

                        showErrorMessage(error);

                        readButton.setEnabled(true);
                    }

                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }
}