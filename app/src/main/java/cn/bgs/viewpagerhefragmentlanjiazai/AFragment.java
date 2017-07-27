package cn.bgs.viewpagerhefragmentlanjiazai;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by shido on 2017/5/15.
 */

public class AFragment extends BaseFragment {
    private ImageView mImg;
    private String path = "http://pic.58pic.com/58pic/15/24/70/58PIC58PIC958PICdqW_1024.jpg";

    @Override
    public View initView() {
        View v = View.inflate(getActivity(), R.layout.afragment, null);
        mImg = (ImageView) v.findViewById(R.id.mImg);
        return v;
    }

    @Override
    public void initList() {

    }

    @Override
    public void setOnclick() {

    }

    @Override
    protected void Start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Message mess = hand.obtainMessage();
                    mess.obj = bitmap;
                    mess.what = 1;
                    hand.sendMessage(mess);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Bitmap bitmap1 = (Bitmap) msg.obj;
                mImg.setImageBitmap(bitmap1);
            }
        }
    };
}
