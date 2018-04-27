package com.easywork.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easywork.R;
import com.laiyifen.library.utils.ToastUtils;

import java.util.List;

import top250Movies.bean.Movies;

/**
 * Created by Wisn on 2018/4/15 下午6:25.
 */

public class CustomViewAnyPositionDialog extends DialogFragment implements View.OnClickListener {
    private static final String NO_TITLE = "no_title";
    private boolean mNo_Title;
    private RecyclerView recyclerView;
    DialogAdapter moviesAdapter;
    private List<Movies.SubjectsBean> data;
    int select=0;
    public static CustomViewAnyPositionDialog getInstance(){
        CustomViewAnyPositionDialog customViewDialog=new CustomViewAnyPositionDialog();
        return customViewDialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1 通过样式定义,DialogFragment.STYLE_NORMAL这个很关键的
        setStyle(DialogFragment.STYLE_NORMAL,R.style.MyDialog1);
        //2代码设置 无标题 无边框  这个就很坑爹，这么设置很多系统效果就都没有了
        //setStyle(DialogFragment.STYLE_NO_TITLE|DialogFragment.STYLE_NO_FRAME,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //3 在此处设置 无标题 对话框背景色
        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // dialog的背景色
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
        getDialog().getWindow().setDimAmount(0.4f);//背景黑暗度
        return inflater.inflate(R.layout.dialog_custome, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.bt_ok).setOnClickListener(this);
        view.findViewById(R.id.bt_cancle).setOnClickListener(this);
        data = Movies.getData();
//        moviesAdapter = new DialogAdapter();
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    ShangjiaHodle
                            myviewHodle =
                            new ShangjiaHodle(LayoutInflater.from(getContext()).inflate(R.layout.topmovies_badmovies,parent,false));

                    return myviewHodle;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                if (holder instanceof ShangjiaHodle) {
                    ShangjiaHodle holder1 = (ShangjiaHodle) holder;
                    holder1.ll_root_shangjia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            notifyItemChanged(select);
                            Toast.makeText(getActivity(), "textView", Toast.LENGTH_SHORT).show();
                            select=position;
                            notifyItemChanged(select);
                        }
                    });
                    if(position==select){
                        holder1.img_seleect_all.setImageResource(R.drawable.selected_true);

                    }else{
                        holder1.img_seleect_all.setImageResource(R.drawable.selected_false);

                    }
//                    holder1.textview.setText(data.get(position));
                }
            }


            @Override
            public int getItemCount() {
                return data.size();
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

        };
        recyclerView.setAdapter(mAdapter);
       /* moviesAdapter.setOnItemClickListener( new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        } );*/
    }

    class ShangjiaHodle extends RecyclerView.ViewHolder {
        public TextView tv_shangjia_name;
        public TextView shangpin_count;
        public LinearLayout ll_root_shangjia;
        public ImageView img_seleect_all;

        public ShangjiaHodle(View itemView) {
            super(itemView);
            tv_shangjia_name = itemView.findViewById(R.id.tv_shangjia_name);
            shangpin_count = itemView.findViewById(R.id.shangpin_count);
            ll_root_shangjia = itemView.findViewById(R.id.ll_root_shangjia);
            img_seleect_all = itemView.findViewById(R.id.img_seleect_all);
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity= Gravity.CENTER|Gravity.CENTER;
        window.setAttributes(attributes);
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                dialog.dismiss();
                return false;
            }
        });

    }
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
             getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            ToastUtils.show(getActivity(),dm.widthPixels+" "+dm.heightPixels);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.7), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
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
