package com.example.temphumprojectlucascavataio;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import androidx.preference.PreferenceDialogFragmentCompat;

public class NumberPreferenceDialog extends PreferenceDialogFragmentCompat {

    private NumberPicker picker;

    public MainActivity mainActivity;

    public static NumberPreferenceDialog newInstance(String key, int min, int max, int ini) {
        final NumberPreferenceDialog fragment = new NumberPreferenceDialog();
        final Bundle bundle = new Bundle(4);
        bundle.clear();
        bundle.putString(ARG_KEY, key);
        bundle.putInt("maxKey", max);
        bundle.putInt("minKey", min);
        bundle.putInt("iniKey", ini);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View onCreateDialogView(Context context) {
        picker = new NumberPicker(context);
        picker.setMaxValue(getArguments().getInt("maxKey"));
        picker.setMinValue(getArguments().getInt("minKey"));
        return picker;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        NumberDialog nd = (NumberDialog) getPreference();
        picker.setValue(nd.getPersistedInt(getArguments().getInt("iniKey")));
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            picker.clearFocus();
            int pickerValue = picker.getValue();

            if (getPreference().callChangeListener(pickerValue)) {
                NumberDialog nd = (NumberDialog) getPreference();

                nd.saveInt(pickerValue);

                SharedPreferences sharedPref = getContext().getSharedPreferences("userPrefs", 0);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(nd.getKey(), nd.getPersistedInt(getArguments().getInt("iniKey")));
                editor.apply();
            }

        }
    }
}
