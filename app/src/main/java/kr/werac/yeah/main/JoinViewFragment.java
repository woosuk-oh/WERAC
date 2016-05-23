package kr.werac.yeah.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.werac_detail.DetailViewActivity;

/**
 * Created by Tacademy on 2016-05-12.
 */
public class JoinViewFragment extends Fragment {

    public JoinViewFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    WeracItemAdapter mAdapter;

    int[] IDS = {R.drawable.p10,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p1,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
            R.drawable.p5,
            R.drawable.p1,
            R.drawable.p3,
            R.drawable.p5,
            R.drawable.p10,
            R.drawable.p6,
            R.drawable.p8,
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new WeracItemAdapter();
        mAdapter.setOnItemClickListener(new WeracItemHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, WeracItem weracItem) {
                Toast.makeText(getContext(), "눌렀니?", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailViewActivity.class);
//                intent.putExtra(TStoreAppListActivity.EXTRA_CATEGORY_CODE, weracItem.getCategoryCode());
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_join, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_join);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        for (int i = 0; i < IDS.length; i++) {
            WeracItem data = new WeracItem();
            data.setPicturePath(IDS[i]);
            data.setTitle("title " + i);
            data.setTitle_sub("subtitle " + i);
            mAdapter.add(data);
        }
    }
}
