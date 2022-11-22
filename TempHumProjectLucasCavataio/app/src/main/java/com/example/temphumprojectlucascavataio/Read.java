package com.example.temphumprojectlucascavataio;

import java.io.Serializable;

public class Read implements Serializable {

    private float temp;
    private float hum;
    private String readTime;

    public Read(float temp, float hum, String readTime){

        this.temp = temp;
        this.hum = hum;
        this.readTime = readTime;
    }

    public float GetTemperature() {


        return temp;
    }

    public float GetHumidity() {


        return hum;
    }

    public String GetReadTime() {


        return readTime;
    }
}
