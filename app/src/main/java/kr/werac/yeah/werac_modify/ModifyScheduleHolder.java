package kr.werac.yeah.werac_modify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyScheduleHolder extends RecyclerView.ViewHolder {

    ImageView create_sch_circle;
    TextView et_sch;
    ImageButton btn_sch_delete;

    public interface OnSchDelClickListener {
        void onItemClick(View view, TextView et_sch);
    }

    OnSchDelClickListener mListener_sch_del;
    public void setOnSchDelClickListener(OnSchDelClickListener listener) {
        mListener_sch_del = listener;
    }

    public ModifyScheduleHolder(View itemView) {
        super(itemView);
        create_sch_circle= (ImageView)itemView.findViewById(R.id.create_sch_circle);
        et_sch = (TextView)itemView.findViewById(R.id.tv_sch);
        btn_sch_delete = (ImageButton)itemView.findViewById(R.id.btn_sch_delete);
        btn_sch_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener_sch_del != null)
                    mListener_sch_del.onItemClick(v, et_sch);
            }
        });
    }

    public void setSchedule(String sch, int position){
        if(position == 0)
            create_sch_circle.setImageResource(R.drawable.schedule_1);
        else
            create_sch_circle.setImageResource(R.drawable.schedule_2);
        et_sch.setText(sch);
    }
}
