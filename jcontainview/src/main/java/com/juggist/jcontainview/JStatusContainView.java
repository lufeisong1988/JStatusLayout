package com.juggist.jcontainview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by lufeisong on 2017/4/6.
 */

public class JStatusContainView extends RelativeLayout {
    Context context;
    private ImageView iv_empty,iv_request;
    private RelativeLayout rl_fail;
    private View loadingView;
    private JLoadingDialog mJLoadingDialog;
    private AlertDialog alertDialog;

    private int empty_src;

    private RelativeLayout.LayoutParams rlp;
    public JStatusContainView(Context context) {
        super(context);
    }

    public JStatusContainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public JStatusContainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    void init(Context context, AttributeSet attrs){
        this.context = context;
        final TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.JEmptyStyle);
        empty_src = array.getResourceId(R.styleable.JEmptyStyle_emptySrc,R.drawable.j_data_empty);
        View view =  LayoutInflater.from(context).inflate(R.layout.j_status_contain,null);
        iv_empty = (ImageView) view.findViewById(R.id.iv_empty);
        iv_request = (ImageView) view.findViewById(R.id.iv_refresh);
        rl_fail = (RelativeLayout) view.findViewById(R.id.rl_fail);
        loadingView = LayoutInflater.from(context).inflate(R.layout.j_loading,null);
        iv_empty.setBackgroundResource(empty_src);
        this.addView(view);

        iv_request.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                requestListener.request();
            }
        });
    }
    RequestListener requestListener;
    public void setRequestListener(RequestListener requestListener){
        this.requestListener = requestListener;

    }
    //展示空数据页面
    public void showEmptyView(){
        iv_empty.setVisibility(VISIBLE);
        dismissFail();
        dismissLoading();
        dismissLoadingDialog();
        dismissFailDialog();
    }
    //展示请求失败页面
    public void showFailView(){
        rl_fail.setVisibility(VISIBLE);
        dismissEmpty();
        dismissLoading();
        dismissLoadingDialog();
        dismissFailDialog();
    }
    //展示页面可滑动页面的loading
    public void showLoadingView(){
        if(loadingView.getParent() == null){
            this.addView(loadingView,getRlp());
        }
        dismissEmpty();
        dismissFail();
        dismissLoadingDialog();
        dismissFailDialog();
    }
    //展示页面不可滑动的loading
    public void showLoadingDialogView(){
        if(mJLoadingDialog == null){
            mJLoadingDialog = new JLoadingDialog(context).builder();
        }
        mJLoadingDialog.show();
        dismissEmpty();
        dismissFail();
        dismissLoading();
        dismissFailDialog();
    }
    //展示网络异常dialog

    public void showFailDialogView(Object msg){
        String msgStr = "";
        if(msg == null){
            msgStr = "获取数据异常";
        }else{
            msgStr = msg.toString();
        }

        if(alertDialog == null){
            alertDialog = new AlertDialog(context).builder()
                    .setMsg(msgStr)
                    .setCancelable(true);
        }
        alertDialog.show();
        dismissEmpty();
        dismissFail();
        dismissLoading();
        dismissLoadingDialog();
    }
    //取消页面显示
    public void dismiss(){
        dismissEmpty();
        dismissFail();
        dismissLoading();
        dismissLoadingDialog();
        dismissFailDialog();
    }
    private void dismissEmpty(){
        iv_empty.setVisibility(INVISIBLE);
    }
    private void dismissFail(){
        rl_fail.setVisibility(INVISIBLE);
    }
    private void dismissLoading(){
        if(loadingView.getParent() != null){
            this.removeView(loadingView);
        }
    }
    private void dismissLoadingDialog(){
        if(mJLoadingDialog != null){
            mJLoadingDialog.dismiss();
            mJLoadingDialog = null;
        }
    }
    private void dismissFailDialog(){
        if(alertDialog != null){
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
    @Override
    public void requestLayout() {
        super.requestLayout();

    }

    public interface RequestListener{
        void request();
    }

    private RelativeLayout.LayoutParams getRlp() {
        rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);//addRule参数对应RelativeLayout XML布局的属性
        return rlp;
    }
}
