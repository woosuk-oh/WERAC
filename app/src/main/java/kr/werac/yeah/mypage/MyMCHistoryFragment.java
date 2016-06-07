package kr.werac.yeah.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.R;
import kr.werac.yeah.data.User;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.main.WeracItemAdapter;
import kr.werac.yeah.main.WeracItemHolder;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.werac_detail.DetailViewActivity;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-05-12.
 */
public class MyMCHistoryFragment extends Fragment {

    public MyMCHistoryFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    WeracItemAdapter mAdapter;
    int mcId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mcId = getArguments().getInt(MCPageActivity.EXTRA_MC_ID);
        }

        mAdapter = new WeracItemAdapter();
        mAdapter.setOnItemClickListener(new WeracItemHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, WeracItem weracItem) {
                Toast.makeText(getContext(), "눌렀니?", Toast.LENGTH_SHORT).show();
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
        View view = inflater.inflate(R.layout.fragment_view_join, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_join);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        NetworkManager.getInstance().getWeracMy(getContext(), 2, new NetworkManager.OnResultListener<User>() {
            @Override
            public void onSuccess(Request request, User result) {
                if(result.getHistory_mc() != null)
                    mAdapter.equalAll(result.getHistory_mc());
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
