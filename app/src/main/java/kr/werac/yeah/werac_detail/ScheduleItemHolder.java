package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class ScheduleItemHolder extends RecyclerView.ViewHolder {

    ImageView detail_sch_circle;
    TextView tv_sch_item;


    public ScheduleItemHolder(View itemView) {
        super(itemView);
        detail_sch_circle= (ImageView)itemView.findViewById(R.id.create_sch_circle);
        tv_sch_item = (TextView)itemView.findViewById(R.id.textview_sch);
    }

    public void setmSch_item(String sch_item, int position, int num){

        if(position == 0 && num == 1) {
            detail_sch_circle.setImageResource(R.drawable.schedule_0);
        }else if(position == 0)
            detail_sch_circle.setImageResource(R.drawable.schedule_1);
        else if(position == (num-1))
            detail_sch_circle.setImageResource(R.drawable.schedule_3);
        else
            detail_sch_circle.setImageResource(R.drawable.schedule_2);

        if(sch_item.equals("스케쥴을 추가해주세요"))
            tv_sch_item.setText("스케쥴이 없습니다.");
        else
            tv_sch_item.setText(sch_item);
    }
}
