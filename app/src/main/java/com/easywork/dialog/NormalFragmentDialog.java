package com.easywork.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.library.utils.ToastUtils;

/**
 * Created by Wisn on 2018/4/15 下午8:02.
 */

public class NormalFragmentDialog extends DialogFragment {
    public static NormalFragmentDialog getInstance(){
        NormalFragmentDialog normalFragmentDialog=new NormalFragmentDialog();
        return normalFragmentDialog;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog  alertDialog=new AlertDialog.Builder(getActivity())
                .setTitle("普通的dialog")
                .setMessage("消息message")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.show(getActivity(),"取消");
                    }
                })
                .setPositiveButton("确定",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.show(getActivity(),"确定");

                    }
                }).create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
//        return super.onCreateDialog(savedInstanceState);
        return  alertDialog;
    }
}
