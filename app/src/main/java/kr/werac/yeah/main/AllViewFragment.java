package kr.werac.yeah.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.werac_detail.DetailViewActivity;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-05-12.
 */
public class AllViewFragment extends Fragment {

    public AllViewFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    WeracItemAdapter mAdapter;
    StaggeredGridLayoutManager mLayoutManager;
    ViewPager imagepager;
    ImagePagerAdapter ImageAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    boolean isLast = false;
    boolean isMoreData = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new WeracItemAdapter();
        ImageAdapter = new ImagePagerAdapter();
        ImageAdapter.setOnItemClickListener(new ImagePagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String whatImage) {
                Intent intent = new Intent(getContext(), ImageViewActivity.class);
                intent.putExtra("whatImage", whatImage);
                startActivity(intent);
            }
        });

        mAdapter.setOnItemClickListener(new WeracItemHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, WeracItem weracItem) {
//                Toast.makeText(getContext(), "눌렀니?", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailViewActivity.class);
                intent.putExtra(DetailViewActivity.EXTRA_WERAC_ID, weracItem.getMid());
                startActivity(intent);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_all);
        recyclerView.setAdapter(mAdapter);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        imagepager = (ViewPager) view.findViewById(R.id.imagepager);
        imagepager.setAdapter(ImageAdapter);
        imagepager.setCurrentItem(0, true);


        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.fva_srl);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                initData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (isLast && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    getMoreData();
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                int totalCount = mAdapter.getItemCount();
//                int[] index = new int[2];
//                index[0] = 0;
//                index[1] = 1;
//                int[] lastVisibleItem = mLayoutManager.findLastVisibleItemPositions(index);
//                if (totalCount > 0 && (lastVisibleItem[0] >= totalCount/2 -1 || lastVisibleItem[1] >= totalCount/2 - 1)) {
//                    isLast = true;
//                } else {
//                    isLast = false;
//                }
//            }
//        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        NetworkManager.getInstance().getWeracList(getContext(), 0, new NetworkManager.OnResultListener<List<WeracItem>>() {
            @Override
            public void onSuccess(Request request, List<WeracItem> result) {
                mAdapter.clear();
                mAdapter.addAll(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        for (int i = 0; i < IDS.length; i++) {
//            WeracItem data = new WeracItem();
//            data.setPicturePath(IDS[i]);
//            data.setTitle("title " + i);
//            data.setTitle_sub("subtitle " + i);
//            mAdapter.add(data);
//        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

//    public void getMoreData(){
//        if (!isMoreData && mAdapter.isMore()) {
//            isMoreData = true;
//            final int page = mAdapter.getLastPage() + 1;
//            try {
//                NetworkManager.getInstance().getTStoreSearchProductList(getContext(), mAdapter.getKeyword(), page, 10, NetworkManager.SEARCH_PRODUCT_ORDER_R, new NetworkManager.OnResultListener<TStoreCategoryProduct>() {
//                    @Override
//                    public void onSuccess(Request request, TStoreCategoryProduct result) {
//                        mAdapter.addAll(result.products.productList);
//                        mAdapter.setLastPage(page);
//                        isMoreData = false;
//                    }
//
//                    @Override
//                    public void onFail(Request request, IOException exception) {
//                        isMoreData = false;
//                    }
//                });
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                isMoreData = false;
//            }
//        }
//    }
}
