package com.kotlin.in_app_notification;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.ColorUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.uikit.R;
import de.hdodenhof.circleimageview.CircleImageView;

public final class InAppNotification {
    private Snackbar mSnackbar;
    private View custom;
    private Resources resources;

    public static InAppNotification make(
            View parent,
            String title,
            String text,
            @ColorRes int backgroundColorInt,
            @DrawableRes int imageResId
    ){
        InAppNotification notification = new InAppNotification(parent, title, text, backgroundColorInt);
        notification.setImage(notification.custom, imageResId);
        return notification;
    }

    public static InAppNotification make(
            View parent,
            String title,
            String text,
            @ColorRes int backgroundColorInt,
            String imageURL
    ){
        InAppNotification notification = new InAppNotification(parent, title, text, backgroundColorInt);
        notification.setImage(notification.custom, imageURL);
        return notification;
    }

    private InAppNotification(
            View parent,
            String title,
            String text,
            @ColorRes int backgroundColorInt
    ){
        this.mSnackbar = Snackbar.make(parent, "", Snackbar.LENGTH_SHORT);
        this.resources = parent.getResources();

        Context context = parent.getContext();

        this.custom = LayoutInflater.from(context).inflate(R.layout.in_app_notification, null);
        ((ViewGroup) mSnackbar.getView()).removeAllViews();
        ((ViewGroup) mSnackbar.getView()).addView(custom);


        TextView titleTV = custom.findViewById(R.id.notificationTitle);
        titleTV.setText(title);
        TextView textTV = custom.findViewById(R.id.notificationText);
        textTV.setText(text);

        CircleImageView close = custom.findViewById(R.id.notificationClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackbar.dismiss();
            }
        });

        ViewGroup notification = custom.findViewById(R.id.in_app_notification);
        StateListDrawable shapeDrawable = (StateListDrawable) notification.getBackground();
        int colorBackground = resources.getColor(backgroundColorInt, null);
        shapeDrawable.setColorFilter(colorBackground, PorterDuff.Mode.MULTIPLY);

        if (isColorDark(colorBackground)) {
            titleTV.setTextColor(resources.getColor(R.color.absolute_white, null));
            textTV.setTextColor(resources.getColor(R.color.absolute_white, null));
            close.setColorFilter(resources.getColor(R.color.absolute_white, null));
            close.setBorderColor(resources.getColor(R.color.absolute_white, null));
        }
        configSnackDesign();
    }

    private boolean isColorDark(int color) {
        return ColorUtils.calculateLuminance(color) < 0.7;
    }

    private void configSnackDesign() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mSnackbar.getView().getLayoutParams();
        params.setMargins(dpToPx(16), dpToPx(0), dpToPx(16), dpToPx(90));

        mSnackbar.getView().setLayoutParams(params);
        mSnackbar.getView().setBackgroundResource(R.drawable.foreground_notification);
        mSnackbar.getView().setPadding(dpToPx(0), dpToPx(0), dpToPx(0), dpToPx(0));
        mSnackbar.getView().setElevation(10);
    }

    private int dpToPx(@Dimension(unit = Dimension.DP) int dip) {
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    private void setImage(@NonNull View custom, @DrawableRes int drawableImageID){
        CircleImageView image = custom.findViewById(R.id.notificationImage);
        image.setImageResource(drawableImageID);
    }

    private void setImage(@NonNull View custom, String imageURL) {
        CircleImageView image = custom.findViewById(R.id.notificationImage);
        Glide.with(custom)
                .load(imageURL)
                .into(image);
    }

    public void show(){
        mSnackbar.show();
    }

    public void setIndefinite(boolean isIndefinite) {
        if (isIndefinite) {
            mSnackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        } else {
            mSnackbar.setDuration(Snackbar.LENGTH_SHORT);
        }
    }
}
