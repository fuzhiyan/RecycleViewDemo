package fuzhiyan.bwei.com.recycleviewdemo;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by Administrator on 2017/5/23.
 * time:
 * author:付智焱
 */

public class IApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration info=new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(info);
        x.Ext.init(this);
        x.Ext.setDebug(false);

    }
}
