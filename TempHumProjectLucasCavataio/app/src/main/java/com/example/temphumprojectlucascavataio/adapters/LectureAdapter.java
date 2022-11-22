package com.example.temphumprojectlucascavataio.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temphumprojectlucascavataio.R;
import com.example.temphumprojectlucascavataio.Read;

import java.util.ArrayList;
import java.util.List;

public class LectureAdapter extends RecyclerView.Adapter {
    private List<Read> mDataSet;

    // Obtener referencias de los componentes visuales para cada elemento
    // Es decir, referencias de los EditText, TextViews, Buttons
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTemp;
        public TextView tvHum;
        public TextView tvTime;
        public MyViewHolder(CardView cv) {
            super(cv);
            tvTemp = cv.findViewById(R.id.tvTemp);
            tvHum = cv.findViewById(R.id.tvHum);
            tvTime = cv.findViewById(R.id.tvTime);
            cv.setOnClickListener(view -> {
                // TODO abrir nueva actividad pasándole el id del equipo
            });
        }
    }

    // Este es nuestro constructor (puede variar según lo que queremos mostrar)
    public LectureAdapter(List<Read> myDataSet) {
        mDataSet = myDataSet;
    }

    // El layout manager invoca este método
    // para renderizar cada elemento del RecyclerView
    @Override
    public LectureAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // Creamos una nueva vista
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_view, parent, false);

        // Aquí podemos definir tamaños, márgenes, paddings, etc
        MyViewHolder vh = new MyViewHolder(cv);
        return vh;
    }

    // Este método asigna valores para cada elemento de la lista
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - obtenemos un elemento del dataset según su posición
        // - reemplazamos el contenido usando tales datos
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        Read eq = mDataSet.get(position);
        myViewHolder.tvTemp.setText(eq.GetTemperature() + "ºC");
        myViewHolder.tvHum.setText(eq.GetHumidity() + "%");
        myViewHolder.tvTime.setText(eq.GetReadTime());
    }

    // Método que define la cantidad de elementos del RecyclerView
    // Puede ser más complejo (por ejem, si implementamos filtros o búsquedas)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}