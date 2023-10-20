package com.example.exercici1;

import android.app.DatePickerDialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatePicker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public void onDateSet(DatePicker datePicker,
                            int year, int month, int day) {
        Formulari activity = (Formulari) getActivity();
        activity.processDatePickerResult(year, month, day);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(
                getActivity(), this, year, month, day);
    }

}