package com.example.temphumprojectlucascavataio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.temphumprojectlucascavataio.adapters.LectureAdapter;

import java.util.ArrayList;

public class LectureHistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LectureAdapter lAdapter;
    private ArrayList<Read> lecturesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_history);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<Read> lectures = (ArrayList<Read>)getIntent().getSerializableExtra("lecturesList");
        lecturesList.addAll(lectures);

        lAdapter = new LectureAdapter(lecturesList);
        mRecyclerView.setAdapter(lAdapter);
    }
}