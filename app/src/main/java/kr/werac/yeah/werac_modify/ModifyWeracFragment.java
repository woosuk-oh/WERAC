package kr.werac.yeah.werac_modify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.werac_create.CreateWeracAdapter;

public class ModifyWeracFragment extends Fragment {

    RecyclerView listView;
    ModifyWeracAdapter mAdapter;
    public static final String EXTRA_WERAC_ID = "WeracId";
    String weracId;
    GridLayoutManager mLayoutManager;


    public static ModifyWeracFragment newInstance() {
        ModifyWeracFragment fragment = new ModifyWeracFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ModifyWeracAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_werac, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list_create);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        listView.setLayoutManager(mLayoutManager);

        setData();
        return view;
    }

    private void setData() {
        for (int i = 0 ; i < 16 ; i++) {
            WeracItem werac = new WeracItem();
            werac.setMid(0);
            werac.setPicturePath(R.drawable.gallery_photo_5);
            werac.setTitle("[위락 모임]꽃놀이 후 피크닉!");
            werac.setTitle_sub("한강 둔치에서 배달음식 먹고 이야기 나누고 싶은 분들 함께해요~");
            ArrayList<String> sch = new ArrayList<>();
            sch.add(0, "자전거 대여해서 타기");
            sch.add(1, "잔디밭에 앉아서 배달음식 시켜먹기");
            werac.setSchedule(sch);
            werac.setLocation_detail("관악구 봉천동 1632-3");
            werac.setLocation_area("서울시");
            werac.setDate("5월 23일");
            werac.setStart_time("12:30");
            werac.setEnd_time("17:30");
            werac.setFee(5000);
            werac.setHas_mc(false);
            werac.setMc_id(123);
            werac.setUid(456);
            werac.setLimit_num(20);
            werac.setJoin_num(16);
            ArrayList<Integer> gi = new ArrayList<>();
            gi.add(0, 7);
            gi.add(1, 8);
            gi.add(2, 9);
            gi.add(3, 10);
            gi.add(4, 17);
            gi.add(5, 81);
            gi.add(6, 91);
            gi.add(7, 101);
            gi.add(8, 72);
            gi.add(9, 18);
            gi.add(10, 239);
            gi.add(11, 101);
            gi.add(12, 72);
            gi.add(13, 28);
            gi.add(14, 92);
            gi.add(15, 110);
            werac.setGuests_id(gi);
            ArrayList<Comment> cmts = new ArrayList<>();
            Comment cmt1 = new Comment();
            cmt1.setUid(7);
            cmt1.setContent("헐진짜재미겠다 ㅠㅠ");
            Comment cmt2 = new Comment();
            cmt2.setUid(8);
            cmt2.setContent("꿀잼예약요 기대함");
            cmts.add(0, cmt1);
            cmts.add(1, cmt2);
            werac.setComments(cmts);
            mAdapter.setWerac(werac);
        }
    }
}
