package com.example.studentapp.view.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameFilter implements InputFilter {

    private static final String TAG = "NameFilter";

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
            String checkMe = String.valueOf(source.charAt(i));
            Pattern pattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]*");
            Matcher matcher = pattern.matcher(checkMe);
            boolean valid = matcher.matches();
            if (!valid) {
                Log.e(TAG, "filter: invalid");
                return "";
            }
        }
        return null;
    }
}
