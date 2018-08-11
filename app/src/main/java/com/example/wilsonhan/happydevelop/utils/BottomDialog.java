package com.example.wilsonhan.happydevelop.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilsonhan.happydevelop.R;

public class BottomDialog extends Dialog{
    public BottomDialog(@NonNull Context context) {
        super(context);
    }

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder{
        private Activity mActivity;
        private BottomDialog mDialog;
        private ClickButtonListener mClickListener;

        public Builder(Activity activity, ClickButtonListener listener){
            mActivity = activity;
            mClickListener = listener;
        }

        public Builder create(){
            mDialog = new BottomDialog(mActivity, R.style.BottomEnterDialog);
            View contentView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_bottom, null);
            TextView imgText = contentView.findViewById(R.id.open_img_text);
            TextView cameraText = contentView.findViewById(R.id.open_camera_text);
            if (mClickListener != null){
                imgText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.clickImg();
                    }
                });
                cameraText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.clickCamera();
                    }
                });
            }
            mDialog.setContentView(contentView);
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = mActivity.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height = mActivity.getResources().getDisplayMetrics().heightPixels/3;
            contentView.setLayoutParams(layoutParams);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.getWindow().setGravity(Gravity.BOTTOM);
            return this;
        }

        public void showDialog(){
            if (mDialog != null){
                mDialog.show();
            }
        }

        public void dismissDialog(){
            if (mDialog != null){
                mDialog.dismiss();
            }
        }

        public boolean isShowing(){
            return mDialog != null? mDialog.isShowing() : false;
        }

        public interface ClickButtonListener{
            void clickImg();

            void clickCamera();
        }
    }
}
