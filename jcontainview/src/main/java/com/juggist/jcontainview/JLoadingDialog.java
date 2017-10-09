package com.juggist.jcontainview;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by lufeisong on 2017/4/7.
 */

public class JLoadingDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private JGifMovieView loading;
    private Display display;

    public JLoadingDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public JLoadingDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.j_loading, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        loading = (JGifMovieView) view.findViewById(R.id.loading);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.JLoadingDialogStyle);
        dialog.setContentView(view);
        loading.setMovieResource(R.raw.j_loading);
        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        dialog.setCanceledOnTouchOutside(true);
        lLayout_bg.setVisibility(View.VISIBLE);
        return this;
    }


    public void show() {
        dialog.setCancelable(false);
        try {
            dialog.show();
        } catch (Exception e) {
        }

    }

    public void dismiss() {
        try {
        dialog.dismiss();
        } catch (Exception e) {
        }

    }
}
