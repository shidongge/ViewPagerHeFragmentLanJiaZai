package cn.bgs.viewpagerhefragmentlanjiazai;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by shido on 2017/5/15.
 */

public class CFragment extends BaseFragment {
    private ImageView mImg;
    private String path = "http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg";
    @Override
    public View initView() {
        View inflate = View.inflate(getActivity(), R.layout.afragment, null);
        mImg = (ImageView) inflate.findViewById(R.id.mImg);
        return inflate;
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
