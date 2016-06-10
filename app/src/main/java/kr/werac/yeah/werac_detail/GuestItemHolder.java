package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class GuestItemHolder extends RecyclerView.ViewHolder {

    CircleImageView iv_guests;

    public interface OnItemClickListener {
        void onItemClick(View view, WeracItem mWerac);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public GuestItemHolder(View itemView) {
        super(itemView);
        iv_guests = (CircleImageView)itemView.findViewById(R.id.image_guests);
    }

    public void setGuest_item(String Guest_image){
        Glide.with(iv_guests.getContext()).load(Guest_image).into(iv_guests);
    }
}
