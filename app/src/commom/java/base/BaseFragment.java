package base;

import android.widget.Toast;

import com.laiyifen.library.base.ABaseFragment;
import com.laiyifen.library.mvp.BaseModel;
import com.laiyifen.library.mvp.BasePresenter;

/**
 * Created by Wisn on 2018/4/6 下午9:09.
 */

public abstract class BaseFragment<T extends BaseModel,E extends BasePresenter> extends ABaseFragment {
    private  Toast toast;
    /**
     * 显示一个Toast类型的消息
     *
     * @param msg 显示的消息
     */
    public void showToast(final String msg) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
                    } else {
                        toast.setText(msg);
                        toast.setDuration(Toast.LENGTH_SHORT);
                    }
                    toast.show();
                }
            });
        }
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
