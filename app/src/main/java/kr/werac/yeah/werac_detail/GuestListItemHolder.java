package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.werac.yeah.R;
import kr.werac.yeah.data.User;

/**
 * Created by Tacademy on 2016-06-08.
 */
public class GuestListItemHolder extends RecyclerView.ViewHolder {

    CircleImageView image_guest_profile;
    TextView tv_guest_name;
    TextView tv_guest_phone;
    User guest;

    public GuestListItemHolder(View itemView) {
        super(itemView);
        image_guest_profile = (CircleImageView) itemView.findViewById(R.id.image_guest_profile);
        tv_guest_name = (TextView) itemView.findViewById(R.id.tv_guest_name);
        tv_guest_phone = (TextView) itemView.findViewById(R.id.tv_guest_phone);
    }

    public void setGuest(User mGuest){
        guest = mGuest;
        if(guest.getProfile_image() != null)
            Glide.with(image_guest_profile.getContext()).load(guest.getProfile_image()).into(image_guest_profile);
        tv_guest_name.setText(guest.getName());
        tv_guest_phone.setText(guest.getPhone());
    }
}
