package kr.werac.yeah.werac_modify;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.werac_create.CreateDetailHolder;
import kr.werac.yeah.werac_create.CreateImageHolder;
import kr.werac.yeah.werac_create.CreateScheduleHolder;
import kr.werac.yeah.werac_create.CreateTitleHolder;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyWeracAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TITLE = 2;
    public static final int VIEW_TYPE_SCHEDULE = 3;
    public static final int VIEW_TYPE_DETAIL_WRITE = 4;


    WeracItem werac;
    ModifyImageHolder h_image;
    ModifyTitleHolder h_title;
    ModifyScheduleHolder h_sch;
    ModifyDetailHolder h_detail;

    public void setWerac(WeracItem werac) {
        this.werac = werac;
        notifyDataSetChanged();
    }

    public void addSch(String et_sch){
        werac.getSchedule().add(et_sch);
        notifyDataSetChanged();
    }

    public WeracItem getWerac(){
        return werac;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_TYPE_IMAGE;
        position--;
        if (position == 0) return VIEW_TYPE_TITLE;
        position--;
        if(werac.getSchedule().size() > 0) {
            if (position < werac.getSchedule().size()) return VIEW_TYPE_SCHEDULE;
            position -= werac.getSchedule().size();
        }
        if (position == 0) return VIEW_TYPE_DETAIL_WRITE;
        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_IMAGE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_werac_image, null);
                return new ModifyImageHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_title, null);
                return new ModifyTitleHolder(view);
            }
            case VIEW_TYPE_SCHEDULE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_schedule_text, null);
                return new ModifyScheduleHolder(view);
            }
            case VIEW_TYPE_DETAIL_WRITE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail, null);
                return new ModifyDetailHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    ModifyDetailHolder.OnDateClickListener mDateListener;
    public void setOnDateClickListener(ModifyDetailHolder.OnDateClickListener listener) {
        mDateListener = listener;
    }

    ModifyDetailHolder.OnTimeClickListener mTimeListener;
    public void setOnTimeClickListener(ModifyDetailHolder.OnTimeClickListener listener) {
        mTimeListener = listener;
    }

    ModifyDetailHolder.OnSchClickListener mListener_sch;
    public void setOnSchClickListener(ModifyDetailHolder.OnSchClickListener listener) {
        mListener_sch = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            h_image = (ModifyImageHolder)holder;
            h_image.setImage(werac);
            return;
        }
        position--;

        if (position == 0) {
            h_title = (ModifyTitleHolder) holder;
            h_title.setTitle(werac);
            return;
        }
        position--;

        if(werac.getSchedule().size()> 0) {
            if (position < werac.getSchedule().size()) {
                h_sch = (ModifyScheduleHolder) holder;
                h_sch.setSchedule(werac.getSchedule().get(position));
                return ;
            }
            position -= werac.getSchedule().size();
        }

        if (position == 0) {
            h_detail = (ModifyDetailHolder) holder;
            h_detail.setDetailWrite(werac);
            h_detail.setOnDateClickListener(mDateListener);
            h_detail.setOnTimeClickListener(mTimeListener);
            h_detail.setOnSchClickListener(mListener_sch);
            return;
        }
        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public int getItemCount() {
        if(werac != null) {
            return 3 + werac.getSchedule().size();
        }else {
            return 0;
        }
    }
}