package com.example.alexander.testapplication.common.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastMessageUtils {
    public static void showMessage(String message, Context context) {
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG).show();
    }
}
