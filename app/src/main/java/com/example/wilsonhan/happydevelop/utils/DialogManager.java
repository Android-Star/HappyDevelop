package com.example.wilsonhan.happydevelop.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.common.commonutils.LogUtils;
import com.example.wilsonhan.happydevelop.R;

/**
 * Created by wilsonhan on 2018/1/30.
 */

public class DialogManager {

    private static final int DIALOG_RECORDING = 1;
    private static final int DIALOG_CANCEL = 2;
    private static final int DIALOG_TOO_SHORT = 3;

    private Dialog dialog;
    private Context mContext;

    private ImageView ivIcon;
    private ImageView ivVoice;
    private ImageView ivShort;
    private ImageView ivCancel;
    private TextView tvLabel;

    public DialogManager(Context context) {
        this.mContext = context;
    }

    public void showRecordingDialog() {
        dialog = new Dialog(mContext, R.style.record_dialog);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);

        ivIcon = dialog.findViewById(R.id.iv_icon);
        ivVoice = dialog.findViewById(R.id.iv_voice);
        ivShort = dialog.findViewById(R.id.iv_short);
        ivCancel = dialog.findViewById(R.id.iv_cancel);
        tvLabel = dialog.findViewById(R.id.tv_label);

        dialog.show();

    }

    public void record() {
        if (dialog != null && dialog.isShowing()) {
            ivIcon.setVisibility(View.VISIBLE);
            ivVoice.setVisibility(View.VISIBLE);
            tvLabel.setVisibility(View.VISIBLE);
            ivCancel.setVisibility(View.GONE);
            ivShort.setVisibility(View.GONE);

            ivIcon.setImageResource(R.drawable.recorder);
            ivVoice.setImageResource(R.drawable.record_v1);
            tvLabel.setText("手指上滑，取消发送");
        }
    }

    public void wantCancel() {
        if (dialog != null && dialog.isShowing()) {
            ivIcon.setVisibility(View.GONE);
            ivVoice.setVisibility(View.GONE);
            tvLabel.setVisibility(View.VISIBLE);
            ivCancel.setVisibility(View.VISIBLE);
            ivShort.setVisibility(View.GONE);

            tvLabel.setText("松开手指，取消发送");
        }
    }


    public void showTooShort() {
        if (dialog != null && dialog.isShowing()) {
            ivIcon.setVisibility(View.GONE);
            ivVoice.setVisibility(View.GONE);
            tvLabel.setVisibility(View.VISIBLE);
            ivCancel.setVisibility(View.GONE);
            ivShort.setVisibility(View.VISIBLE);

            tvLabel.setText("录音时间过短");
        }
    }

    public void diMissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void setLevel(int level) {
        if (dialog != null && dialog.isShowing()) {

            int levelId = mContext.getResources()
                    .getIdentifier(
                            "record_v" + level,
                            "drawable",
                            mContext.getPackageName()
                    );
            LogUtils.logi("DialogManager" + levelId);
            ivVoice.setImageResource(levelId);
        }
    }


}
