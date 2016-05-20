package kr.werac.yeah.werac_modify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class MScheduleItemHolder extends RecyclerView.ViewHolder {

    TextView et_sch;

    public interface OnItemClickListener {
        void onItemClick(View view, WeracItem mWerac);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MScheduleItemHolder(View itemView) {
        super(itemView);
        et_sch = (TextView)itemView.findViewById(R.id.et_sch);;
    }

    public void setmSch_item(String sch_item){
        et_sch.setText(sch_item);
    }
}
