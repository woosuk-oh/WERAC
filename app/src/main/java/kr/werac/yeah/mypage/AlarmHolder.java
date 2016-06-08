package kr.werac.yeah.mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Alarm;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-06-08.
 */
public class AlarmHolder extends RecyclerView.ViewHolder {

    TextView tv_alarm_date;
    TextView tv_alarm_message;
    Alarm myAlarm;

    public interface OnItemClickListener {
        void onItemClick(View view, Alarm alarm);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AlarmHolder(View itemView) {
        super(itemView);
        tv_alarm_date = (TextView)itemView.findViewById(R.id.tv_alarm_date);
        tv_alarm_message = (TextView)itemView.findViewById(R.id.tv_alarm_message);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, myAlarm);
                }
            }
        });
    }

    public void setAlram(Alarm alarm){
        myAlarm = alarm;
        tv_alarm_date.setText(alarm.getDate());
        tv_alarm_message.setText(alarm.getComment());
    }
}
