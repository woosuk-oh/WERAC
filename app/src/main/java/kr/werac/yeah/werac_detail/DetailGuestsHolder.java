package kr.werac.yeah.werac_detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.PropertyManager;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class DetailGuestsHolder extends RecyclerView.ViewHolder {

    TextView text_guest_title;
    RecyclerView rv_guests_image;
    GuestItemAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ImageButton ibtn_see_guest_list;
    int weracId;

    public interface OnGuestListClickListener {
        void onItemClick(View view, int weracId);
    }

    OnGuestListClickListener mListener_guest_list;
    public void setOnGuestListClickListener(OnGuestListClickListener listener) {
        mListener_guest_list = listener;
    }

    public DetailGuestsHolder(View itemView) {
        super(itemView);
        text_guest_title = (TextView)itemView.findViewById(R.id.text_guest_title);
        rv_guests_image = (RecyclerView)itemView.findViewById(R.id.rv_guests_image);
        ibtn_see_guest_list = (ImageButton)itemView.findViewById(R.id.ibtn_see_guest_list);
        ibtn_see_guest_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener_guest_list != null) {
                    mListener_guest_list.onItemClick(v, weracId);
                }
            }
        });
    }

    public void setGuests(WeracItem werac){
        weracId = werac.getMid();
        text_guest_title.setText("참여 정원 " + werac.getGuests().size() + "/" + werac.getLimit_num() + "명");

        if(werac.getStatus() == 2 && werac.getCreator().getUid() == PropertyManager.getInstance().getUser().getUid()){
            ibtn_see_guest_list.setVisibility(View.VISIBLE);
            rv_guests_image.setVisibility(View.INVISIBLE);
        }else {
            ibtn_see_guest_list.setVisibility(View.INVISIBLE);
            rv_guests_image.setVisibility(View.VISIBLE);
            mAdapter = new GuestItemAdapter();
            rv_guests_image.setAdapter(mAdapter);
            mLayoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_guests_image.setLayoutManager(mLayoutManager);

            mAdapter.addAll(werac.getGuests());
        }
    }
}
