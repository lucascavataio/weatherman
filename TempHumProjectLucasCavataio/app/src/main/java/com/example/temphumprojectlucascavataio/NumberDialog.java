package com.example.temphumprojectlucascavataio;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.DialogPreference;

public class NumberDialog extends DialogPreference {
    public NumberDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public CharSequence getSummary() {
        int saved = getPersistedInt(-1);
        if (saved == -1) {
            return "no defined";
        }
        return "#" + saved;
    }

    @Override
    protected int getPersistedInt(int defaultReturnValue) {
        return super.getPersistedInt(defaultReturnValue);
    }

    public void saveInt(int valor) {
        super.persistInt(valor);
        notifyChanged();
    }
}
