package base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.library.base.ABaseActivity;
import com.library.mvp.BaseModel;
import com.library.mvp.BasePresenter;

/**
 * Created by Wisn on 2018/4/6 下午7:41.
 */

public abstract class BaseActivity<T extends BaseModel, E extends BasePresenter> extends ABaseActivity {
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 显示一个Toast类型的消息
     *
     * @param msg 显示的消息
     */
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(msg);
                    toast.setDuration(Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });
    }

    /**
     * 显示{@link Toast}通知
     *
     * @param strResId 字符串资源id
     */
    public void showToast(final int strResId) {
        String text = getString(strResId);
        showToast(text);
    }
}
