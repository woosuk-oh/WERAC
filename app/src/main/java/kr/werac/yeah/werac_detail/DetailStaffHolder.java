package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class DetailStaffHolder extends RecyclerView.ViewHolder {

    TextView text_staff;
    CircleImageView image_staff;
    TextView text_staff_name;
    ImageView image_staff_title;
    TextView imageButton_waiting_mc;
    public int who;

    WeracItem werac;

    public interface OnItemClickListener {
        void onItemClick(View view, WeracItem werac, int who);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DetailStaffHolder(View view) {
        super(view);
        text_staff = (TextView)view.findViewById(R.id.text_staff);
        image_staff = (CircleImageView)view.findViewById(R.id.image_staff);
        text_staff_name = (TextView)view.findViewById(R.id.text_staff_name);
        image_staff_title = (ImageView) view.findViewById(R.id.image_staff_title);
        imageButton_waiting_mc = (TextView)view.findViewById(R.id.imageButton_waiting_mc);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, werac, who);
                }
            }
        });
    }

    public void setStaff(WeracItem werac, int s){
        this.werac = werac;
        who = s+1;
        switch (s) {
            case 0:
                text_staff.setText("진행자");
                image_staff_title.setImageResource(R.drawable.page_mc);
                if(werac.isHas_mc() == true && werac.getMc() != null) {
                    imageButton_waiting_mc.setVisibility(View.INVISIBLE);
                    if(werac.getMc().getProfile_image() == null)
                        image_staff.setImageResource(R.drawable.profile_default);
                    else
                        Glide.with(image_staff.getContext()).load(werac.getMc().getProfile_image()).into(image_staff);
                    text_staff_name.setText("" + werac.getMc().getName());
                }else if(werac.isHas_mc() == true && werac.getMc() == null){
                    imageButton_waiting_mc.setVisibility(View.VISIBLE);
                    image_staff.setVisibility(View.INVISIBLE);
                    text_staff_name.setVisibility(View.INVISIBLE);
                }else if(werac.isHas_mc() == false){
                    imageButton_waiting_mc.setVisibility(View.INVISIBLE);
                    image_staff.setVisibility(View.VISIBLE);
                    text_staff_name.setVisibility(View.VISIBLE);
                    if(werac.getCreator().getProfile_image() == null)
                        image_staff.setImageResource(R.drawable.profile_default);
                    else
                        Glide.with(image_staff.getContext()).load(werac.getCreator().getProfile_image()).into(image_staff);
                    text_staff_name.setText("" + werac.getCreator().getName());
                }
                break;
            case 1:
                imageButton_waiting_mc.setVisibility(View.INVISIBLE);
                image_staff.setVisibility(View.VISIBLE);
                text_staff_name.setVisibility(View.VISIBLE);
                text_staff.setText("개설자");
                if(werac.getCreator().getProfile_image() == null)
                    Glide.with(image_staff.getContext()).load(R.drawable.profile_default).into(image_staff);
                else
                    Glide.with(image_staff.getContext()).load(werac.getCreator().getProfile_image()).into(image_staff);
                text_staff_name.setText("" + werac.getCreator().getName());
                image_staff_title.setImageResource(R.drawable.page_creator);
                break;
        }

    }
}
