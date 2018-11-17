package com.jinyun.antivirusfour.health.cook.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.jinyun.antivirusfour.R;
import com.jinyun.antivirusfour.databinding.FragmentMenuBinding;
import com.jinyun.antivirusfour.health.cook.activity.CookListActivity;
import com.jinyun.antivirusfour.health.cook.adapter.CookMenuAdapter;
import com.jinyun.antivirusfour.health.cook.entity.CookMenu;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 *
 * 主界面下的菜单分类的Fragment
 */
public class MenuFragment extends Fragment {

    public final static String ITEM_ID = "item_id";
    public static final String TITLE_NAME = "title_name";

    private List<CookMenu> mListMenus;

    private final int[] mArrIds = {1, 10, 15, 52, 62, 68, 75, 82, 98, 112, 147, 161, 218, 166, 182, 188,
            192, 197, 202, 205, 212, 227, 132};
    private final String[] mArrNames = {"美容", "减肥", "保健养生", "人群", "时节", "餐时", "器官", "调养",
            "肠胃消化", "孕产哺乳", "经期", "女性疾病", "男性", "呼吸道", "血管", "心脏", "肝胆脾胰",
            "神经系统", "口腔", "肌肉骨骼", "皮肤", "癌症", "其他"};
    private final int[] mImgRes = {R.drawable.meirong, R.drawable.jianfei, R.drawable.jiankangyangsheng,
            R.drawable.renqun, R.drawable.shijie, R.drawable.canshi, R.drawable.qiguan, R.drawable.tiaoyang,
            R.drawable.chagnweixiaohua, R.drawable.yunchanpuru, R.drawable.jingqi, R.drawable.nvxingjibing,
            R.drawable.nanxing, R.drawable.huxidao, R.drawable.xueguan, R.drawable.xinzang, R.drawable.gandanpiy,
            R.drawable.shenjingxitong, R.drawable.kouqiang, R.drawable.jirouguge, R.drawable.pifu,
            R.drawable.aizheng, R.drawable.qita};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMenuBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, null, false);
        mListMenus = addMenus();
        CookMenuAdapter menuAdapter = new CookMenuAdapter(mListMenus, getActivity());
        binding.gvMenu.setAdapter(menuAdapter);
        //GridView监听
        RxAdapterView.itemClicks(binding.gvMenu)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Intent intent = new Intent(getActivity(), CookListActivity.class);
                        intent.putExtra(ITEM_ID, mListMenus.get(integer).getId());
                        intent.putExtra(TITLE_NAME, mListMenus.get(integer).getName());
                        startActivity(intent);
                    }
                });
        return binding.getRoot();
    }

    /**
     * 给菜单分类List添加数据
     *
     * @return 菜单分类List
     */
    private List<CookMenu> addMenus() {
        List<CookMenu> data = new ArrayList<>();
        for (int i = 0; i < mArrIds.length; i++) {
            CookMenu bean = new CookMenu();
            bean.setId(mArrIds[i]);
            bean.setImgId(mImgRes[i]);
            bean.setName(mArrNames[i]);
            data.add(bean);
        }
        return data;
    }


}
