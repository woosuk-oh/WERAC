package kr.werac.yeah.werac_modify;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.werac_create.CreateScheduleHolder;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyWeracAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TITLE = 2;
    public static final int VIEW_TYPE_SCHEDULE = 3;
    public static final int VIEW_TYPE_DETAIL_WRITE = 4;

    WeracItem werac;
    Bitmap bm;
    ModifyImageHolder h_image;
    ModifyTitleHolder h_title;
    ModifyScheduleHolder h_sch;
    ModifyDetailHolder h_detail;
    List<String> MySch = new ArrayList<String>();

    public void setWerac(WeracItem werac) {
        this.werac = werac;
        notifyDataSetChanged();
    }

    public void addImage(Bitmap bm) {
        this.bm = bm;
        notifyDataSetChanged();
    }

    public void addSch(String et_sch){
        werac.getSchedule().add(et_sch);
        MySch.add(et_sch);
        notifyDataSetChanged();
    }

    public void removeSch(String et_sch){
        for(int i = 0; i < MySch.size(); i++){
            if(MySch.get(i).equals(et_sch)) {
                MySch.remove(i);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public WeracItem getWeracWhole(){
        return werac;
    }

    public WeracItem getWeracBy(){
        werac.setTitle(h_title.getTitle().getTitle());
        werac.setTitle_sub(h_title.getTitle().getTitle_sub());
        werac.setSchedule(MySch);
        werac.setLocation_detail(h_detail.getDetail().getLocation_detail());
        werac.setLocation_area(h_detail.getDetail().getLocation_area());
        werac.setDate(h_detail.getDetail().getDate());
        werac.setStart_time(h_detail.getDetail().getStart_time());
        werac.setEnd_time(h_detail.getDetail().getEnd_time());
        werac.setFee(h_detail.getDetail().getFee());
        werac.setHas_mc(h_detail.getDetail().isHas_mc());
        werac.setLimit_num(h_detail.getDetail().getLimit_num());
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

    ModifyImageHolder.OnItemClickListener mListener_image;
    public void setOnItemClickListener(ModifyImageHolder.OnItemClickListener listener) {
        mListener_image = listener;
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

    ModifyScheduleHolder.OnSchDelClickListener mListener_sch_del;
    public void setOnSchDelClickListener(ModifyScheduleHolder.OnSchDelClickListener listener) {
        mListener_sch_del = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            h_image = (ModifyImageHolder)holder;
            h_image.setImageURL(werac);
            h_image.setOnItemClickListener(mListener_image);
            if(bm != null)
                h_image.setImage(bm);
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
                MySch = werac.getSchedule();
                h_sch.setSchedule(werac.getSchedule().get(position), position);
                h_sch.setOnSchDelClickListener(mListener_sch_del);
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