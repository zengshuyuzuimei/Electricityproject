package com.example.electricityproject.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.Msg;
import com.example.electricityproject.data.bean.RecyclerBean;
import com.example.electricityproject.data.bean.XBannerBean;
import com.example.electricityproject.data.sqlite.DaoSession;
import com.example.electricityproject.data.sqlite.ShopDao;
import com.example.electricityproject.data.table.Shop;
import com.example.electricityproject.di.contract.Home2Contract;
import com.example.electricityproject.di.presenter.Home2Presenter;
import com.example.electricityproject.ui.activity.App;
import com.example.electricityproject.ui.activity.DetailsActivity;
import com.example.electricityproject.ui.activity.SearchActivity;
import com.example.electricityproject.ui.adapter.MlssAdapter;
import com.example.electricityproject.ui.adapter.PzshAdapter;
import com.example.electricityproject.ui.adapter.RxxpAdapter;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 19:56:29
 *@Description:
 * */
public class HomeFragment extends BaseFragment<Home2Contract.Home2View, Home2Presenter<Home2Contract.Home2View>> implements Home2Contract.Home2View {

    private ImageView home_menu;
    private ImageView home_search;
    private EditText home_edit;
    private XBanner xBanner;
    private RecyclerView grid_recycler;
    private RecyclerView line_recycler;
    private RecyclerView stage_recycler;
    private DrawerLayout drawer;
    private ShopDao shopDao;

    @Override
    protected void initView(View view) {
        xBanner = view.findViewById(R.id.x_banner);
        grid_recycler = view.findViewById(R.id.grid_recycler);
        line_recycler = view.findViewById(R.id.line_recycler);
        stage_recycler = view.findViewById(R.id.stag_recycler);
        drawer = view.findViewById(R.id.drawer);
        home_edit = view.findViewById(R.id.home_edit);
        home_menu = view.findViewById(R.id.home_menu);
        home_search = view.findViewById(R.id.home_search);
        home_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });
        home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public void show(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        final XBannerBean xBannerBean = gson.fromJson(string, XBannerBean.class);
        xBanner.setBannerData(xBannerBean.getResult());
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(xBannerBean.getResult().get(position).getXBannerUrl())
                        .into((ImageView) view);
            }
        });
    }

    @Override
    public void showGrid(String string) {
        if (string!=null){
            Shop shop = new Shop();
            shop.setId((long) 1);
            shop.setShopInfo(string);
            shopDao.insert(shop);
            showAdapter(string);
        }else{
            Shop load = shopDao.load((long) 1);
            String shopInfo = load.getShopInfo();
            showAdapter(shopInfo);
        }

    }

    private void showAdapter(String string) {
        Gson gson = new Gson();
        RecyclerBean recyclerBean = gson.fromJson(string, RecyclerBean.class);
        final ArrayList<RecyclerBean.ResultBean.RxxpBean.CommodityListBean> rxxp = (ArrayList<RecyclerBean.ResultBean.RxxpBean.CommodityListBean>) recyclerBean.getResult().getRxxp().getCommodityList();
        //创建适配器
        RxxpAdapter rxxpAdapter = new RxxpAdapter(R.layout.rxxp_view, rxxp);
        grid_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        grid_recycler.setAdapter(rxxpAdapter);
        rxxpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Msg msg = new Msg();
                msg.setId(rxxp.get(position).getCommodityId());
                EventBus.getDefault().postSticky(msg);
                startActivity(new Intent(getContext(), DetailsActivity.class));
            }
        });
    }

    @Override
    public void showLine(String string) {
        Gson gson = new Gson();
        RecyclerBean recyclerBean = gson.fromJson(string, RecyclerBean.class);
        final ArrayList<RecyclerBean.ResultBean.MlssBean.CommodityListBeanXX> mlss = (ArrayList<RecyclerBean.ResultBean.MlssBean.CommodityListBeanXX>) recyclerBean.getResult().getMlss().getCommodityList();
        //创建适配器
        MlssAdapter mlssAdapter = new MlssAdapter(R.layout.mlss_view, mlss);
        line_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        line_recycler.setAdapter(mlssAdapter);
        mlssAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Msg msg = new Msg();
                msg.setId(mlss.get(position).getCommodityId());
                EventBus.getDefault().postSticky(msg);
                startActivity(new Intent(getContext(), DetailsActivity.class));
            }
        });
    }

    @Override
    public void showStag(String string) {
        Gson gson = new Gson();
        RecyclerBean recyclerBean = gson.fromJson(string, RecyclerBean.class);
        final ArrayList<RecyclerBean.ResultBean.PzshBean.CommodityListBeanX> pzsh = (ArrayList<RecyclerBean.ResultBean.PzshBean.CommodityListBeanX>) recyclerBean.getResult().getPzsh().getCommodityList();

        //创建适配器
        PzshAdapter pzshAdapter = new PzshAdapter(R.layout.pzsh_view, pzsh);
        stage_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        stage_recycler.setAdapter(pzshAdapter);
        pzshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Msg msg = new Msg();
                msg.setId(pzsh.get(position).getCommodityId());
                EventBus.getDefault().postSticky(msg);
                startActivity(new Intent(getContext(), DetailsActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        presenter.showBanner();
        presenter.showLine();
        presenter.showStag();
        DaoSession daoSession = App.getDaoSession();
        shopDao = daoSession.getShopDao();
        List<Shop> shops = shopDao.loadAll();
        if (shops!=null&&shops.size()>0){
            Shop load = shopDao.load((long) 1);
            String shopInfo = load.getShopInfo();
            showAdapter(shopInfo);
        }else{
            presenter.showGrid();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected Home2Presenter<Home2Contract.Home2View> createPresenter() {
        return new Home2Presenter<>();
    }


}
