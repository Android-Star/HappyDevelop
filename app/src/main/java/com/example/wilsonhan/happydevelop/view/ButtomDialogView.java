package com.example.wilsonhan.happydevelop.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.wilsonhan.happydevelop.R;

public class ButtomDialogView extends Dialog {
    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private View view;
    private Context context;

    public ButtomDialogView(Context context, View view, boolean isCancelable) {
        super(context, R.style.MyDialog);

        this.context = context;
        this.view = view;
        this.iscancelable = isCancelable;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);//这行一定要写在前面
        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }
}
