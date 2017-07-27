package cn.bgs.viewpagerhefragmentlanjiazai;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shido on 2017/5/15.
 */

public class BFragment extends BaseFragment {
    private TextView tv;
    private String str = "http://japi.juhe.cn/joke/content/list.from?key=b77f82f109f35ed582a6596f93e27df9&page=2&pagesize=10&sort=asc&time=1418745235";
    @Override
    public View initView() {
        View v = View.inflate(getActivity(), R.layout.bfragment, null);
        tv  = (TextView) v.findViewById(R.id.mTv);
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
                    URL url = new URL(str);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb = new StringBuffer();
                    String temp ;
                    while((temp = br.readLine())!=null){
                        sb.append(temp);
                    }
                    String s = sb.toString();
                    Message mess = hand.obtainMessage();
                    mess.obj=s;
                    mess.what=1;
                    hand.sendMessage(mess);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String string = (String) msg.obj;
                tv.setText(string);
            }
        }
    };
}
