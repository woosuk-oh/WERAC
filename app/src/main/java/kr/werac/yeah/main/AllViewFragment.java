package kr.werac.yeah.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.werac_detail.DetailViewActivity;
import kr.werac.yeah.werac_detail.DetailWeracFragment;
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
    ViewPager imagepager;
    ImagePagerAdapter ImageAdapter;
    int p;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new WeracItemAdapter();

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
        imagepager = (ViewPager) view.findViewById(R.id.imagepager);
        ImageAdapter = new ImagePagerAdapter(getActivity());
        imagepager.setAdapter(ImageAdapter);
        p = 0;

        imagepager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                p = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        imagepager.setCurrentItem(2, true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_all);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

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
}
