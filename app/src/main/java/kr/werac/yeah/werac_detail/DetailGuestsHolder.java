package kr.werac.yeah.werac_detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class DetailGuestsHolder extends RecyclerView.ViewHolder {

    TextView text_guest_title;
    RecyclerView rv_guests_image;
    GuestItemAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    public DetailGuestsHolder(View itemView) {
        super(itemView);
        text_guest_title = (TextView)itemView.findViewById(R.id.text_guest_title);
        rv_guests_image = (RecyclerView)itemView.findViewById(R.id.rv_guests_image);
    }

    public void setGuests(WeracItem werac, List<Integer> Guests_id){

        text_guest_title.setText("참여 정원 " + Guests_id.size() + "/" + werac.getLimit_num() + "명");

        mAdapter = new GuestItemAdapter();
        rv_guests_image.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_guests_image.setLayoutManager(mLayoutManager);

        mAdapter.addAll(Guests_id);
    }
}
