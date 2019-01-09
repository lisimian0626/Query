package com.beidousat.querydata.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.beidousat.querydata.fragment.PaperFragment;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends FragmentPagerAdapter{
    List<Fragment> list;
    public MyAdapter(FragmentManager fm) {
        super(fm);
        //创建集合用来保存fragment对象
        list=new ArrayList<>();
    }
    public void add(Fragment framgent){
        list.add(framgent);

    }
    //当前页数据
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    //获取pager数量
    @Override
    public int getCount() {
        return list.size();
    }
    //重写父类的destroyItem方法，什么都不要写，避免viewpager销毁fragment
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
    //添加标题
    @Override
    public CharSequence getPageTitle(int position) {
        //返回的标题是之前传的值
        return list.get(position).getArguments().getString("title");
    }
}

