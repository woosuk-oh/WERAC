package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class DetailDetailHolder extends RecyclerView.ViewHolder {

    TextView text_detail_title;
    TextView text_werac_detail;
    ImageView image_detail_title;

    public DetailDetailHolder(View view) {
        super(view);
        text_detail_title = (TextView)view.findViewById(R.id.text_detail_title);
        text_werac_detail = (TextView)view.findViewById(R.id.text_werac_detail);
        image_detail_title = (ImageView)view.findViewById(R.id.image_detail_title);
    }

    public void setDetailDetail(WeracItem werac, int d){
        switch (d) {
            case 0 :
                text_detail_title.setText("모임 장소");
                text_werac_detail.setText(werac.getLocation_detail() + "\n(" + werac.getLocation_area() + ")");
                image_detail_title.setImageResource(R.drawable.page_location);
                break;
            case 1:
                text_detail_title.setText("모임 시간");
                text_werac_detail.setText(werac.getDate() + "\n" + werac.getStart_time() + "~" + werac.getEnd_time());
                image_detail_title.setImageResource(R.drawable.page_time);
                break;
            case 2:
                text_detail_title.setText("참가비");
                text_werac_detail.setText(werac.getFee()+"원");
                image_detail_title.setImageResource(R.drawable.page_ticket);
                break;
        }
    }
}
