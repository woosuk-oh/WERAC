package kr.werac.yeah.werac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.WeracItem;

public class DetailViewActivity extends AppCompatActivity {

    RecyclerView listView;
    CreateWeracAdapter mAdapter;
    public static final String EXTRA_WERAC_ID = "weracId";
    String weracId;
    GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_werac);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (RecyclerView)findViewById(R.id.rv_list_create);
        mAdapter = new CreateWeracAdapter();
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(this, 6);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mAdapter.getItemViewType(position);
                if (type == DetailViewAdapter.VIEW_TYPE_STAFF) {
                    return 3;
                } else if (type == DetailViewAdapter.VIEW_TYPE_DETAIL_VIEW) {
                    return 2;
                } else {
                    return 6;
                }
            }
        });
        listView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        weracId = intent.getStringExtra(EXTRA_WERAC_ID);

        setData();
    }

    private void setData() {
        for (int i = 0 ; i < 16 ; i++) {
            WeracItem werac = new WeracItem();
            werac.setMid(0);
            werac.setPicturePath(R.drawable.gallery_photo_5);
            werac.setTitle("[위락 모임]꽃놀이 후 피크닉!");
            werac.setTitle_sub("한강 둔치에서 배달음식 먹고 이야기 나누고 싶은 분들 함께해요~");
            ArrayList<String> sch = new ArrayList<>();
            sch.set(0, "자전거 대여해서 타기");
            sch.set(1, "잔디밭에 앉아서 배달음식 시켜먹기");
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
            ArrayList<Integer> gi = new ArrayList<>();
            gi.set(0, 7);
            gi.set(1, 8);
            gi.set(2, 9);
            gi.set(3, 10);
            werac.setGuests_id(gi);
            ArrayList<Comment> cmts = new ArrayList<>();
            Comment cmt1 = new Comment();
            cmt1.setUid(7);
            cmt1.setContent("헐진짜재미겠다 ㅠㅠ");
            Comment cmt2 = new Comment();
            cmt2.setUid(8);
            cmt2.setContent("꿀잼예약요 기대함");
            cmts.set(0, cmt1);
            cmts.set(1, cmt2);
            werac.setComments(cmts);
            mAdapter.setWerac(werac);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
