package com.doctruyentranh;// Import the necessary libraries
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

    public static void showCustomToast(Context context, String message) {
        // Create a custom view for the Toast
        View customToastView = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);

        // Find views within the custom layout
        ImageView iconImageView = customToastView.findViewById(R.id.iconImageView);
        TextView messageTextView = customToastView.findViewById(R.id.messageTextView);

        // Set the icon and message
        iconImageView.setImageResource(R.drawable.iconload); // Replace with your icon resource
        messageTextView.setText(message);

        // Create the Toast and set the custom view
        Toast customToast = new Toast(context);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.setView(customToastView);
        customToast.show();
    }
    public static void showCustomToasterron(Context context, String message) {
        // Create a custom view for the Toast
        View customToastView = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);

        // Find views within the custom layout
        ImageView iconImageView = customToastView.findViewById(R.id.iconImageView);
        TextView messageTextView = customToastView.findViewById(R.id.messageTextView);

        // Set the icon and message
        iconImageView.setImageResource(R.drawable.iconerror); // Replace with your icon resource
        messageTextView.setText(message);

        // Create the Toast and set the custom view
        Toast customToast = new Toast(context);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.setView(customToastView);
        customToast.show();
    }
    public static void showCustomToastupdate(Context context, String message) {
        // Create a custom view for the Toast
        View customToastView = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null);

        // Find views within the custom layout
        ImageView iconImageView = customToastView.findViewById(R.id.iconImageView);
        TextView messageTextView = customToastView.findViewById(R.id.messageTextView);

        // Set the icon and message
        iconImageView.setImageResource(R.drawable.doiupdate);
        messageTextView.setText(message);

        // Create the Toast and set the custom view
        Toast customToast = new Toast(context);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.setView(customToastView);
        customToast.show();
    }
}
