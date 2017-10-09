package com.juggist.statuslayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.juggist.jcontainview.JStatusContainView;

public class MainActivity extends Activity {
    JStatusContainView containView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containView = (JStatusContainView) findViewById(R.id.containView);

    }

    public void showEmpty(View view){
        containView.showEmptyView();

    }
    public void showFail(View view){
        containView.showFailDialogView(null);
    }
    public void showLoading(View view){
        containView.showFailDialogView("失败");
    }
    public void showLoadingDialog(View view){

        containView.showLoadingDialogView();
    }
    public void dismiss(View view){
        containView.dismiss();
    }
}
