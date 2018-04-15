package com.easywork.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.easywork.R;
import com.library.utils.ToastUtils;

/**
 * Created by Wisn on 2018/4/15 下午6:25.
 */

public class CustomViewDialog extends DialogFragment implements View.OnClickListener {
    private static final String NO_TITLE = "no_title";
    private boolean mNo_Title;

    public static CustomViewDialog getInstance(boolean mNo_Title){
        CustomViewDialog customViewDialog=new CustomViewDialog();
        Bundle bundle=new Bundle();
        bundle.putBoolean(NO_TITLE,mNo_Title);
        customViewDialog.setArguments(bundle);
        return customViewDialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNo_Title = getArguments().getBoolean(NO_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mNo_Title) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return inflater.inflate(R.layout.dialog_custome, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.bt_ok).setOnClickListener(this);
        view.findViewById(R.id.bt_cancle).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cancle:
                this.dismiss();
                ToastUtils.show(getActivity(),"quxiao");
                break;
            case R.id.bt_ok:
                this.dismiss();
                ToastUtils.show(getActivity(),"queding");

                break;
            default:
                break;
        }
    }
}
