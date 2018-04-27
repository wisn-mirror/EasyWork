package base;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.laiyifen.library.base.ABaseActivity;
import com.laiyifen.library.mvp.BaseModel;
import com.laiyifen.library.mvp.BasePresenter;

import java.util.List;

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

    public  boolean isForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(20);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
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
