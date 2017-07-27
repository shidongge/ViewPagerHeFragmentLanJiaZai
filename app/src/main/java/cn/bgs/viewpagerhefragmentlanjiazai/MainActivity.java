package cn.bgs.viewpagerhefragmentlanjiazai;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mVp;
    private AFragment aFragment;
    private BFragment bFragment;
    private CFragment cFragment;
    private DFragment dFragment;
    private List<Fragment> list;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        initList();
        initView();

    }

    private void initList() {
        list = new ArrayList<>();
        aFragment = new AFragment();
        bFragment = new BFragment();
        cFragment = new CFragment();
        dFragment = new DFragment();
        list.add(aFragment);
        list.add(bFragment);
        list.add(cFragment);
        list.add(dFragment);
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.mVp);
        mVp.setAdapter(new MyAdapter(fm));
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                FragmentTransaction ft = fm.beginTransaction();
                switch (position){
                    case 0:
                        ft.show(aFragment);
                        break;
                    case 1:
                        ft.show(bFragment);
                        break;
                    case 2:
                        ft.show(cFragment);
                        break;
                    case 3 :
                        ft.show(dFragment);
                        break;
                }
                ft.commit();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
