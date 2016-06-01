package kr.werac.yeah.werac_create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.werac_detail.DetailStaffHolder;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class CreateWeracAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TITLE = 2;
    public static final int VIEW_TYPE_SCHEDULE = 3;
    public static final int VIEW_TYPE_DETAIL_WRITE = 4;
    public static final int VIEW_TYPE_DETAIL_VIEW = 5;
    public static final int VIEW_TYPE_STAFF = 6;

    WeracItem werac = new WeracItem();
    Bitmap bm;
    List<String> MySch = new ArrayList<String>();
    int hasSch = 0;

    CreateImageHolder h_image;
    CreateTitleHolder h_title;
    CreateScheduleHolder h_sch;
    CreateDetailHolder h_detail;

    public void setWerac(WeracItem werac) {
        this.werac = werac;
        notifyDataSetChanged();
    }

    public void addImage(Bitmap bm) {
        this.bm = bm;
        notifyDataSetChanged();
    }

    public void addSch(String et_sch){
        if(hasSch == 1) {
            MySch.set(0, et_sch);
        }else
            MySch.add(et_sch);
        hasSch++;
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

    public WeracItem getWerac(){
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
        if(MySch.size() > 0) {
            if (position < MySch.size()) return VIEW_TYPE_SCHEDULE;
            position -= MySch.size();
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
                return new CreateImageHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_title, null);
                return new CreateTitleHolder(view);
            }
            case VIEW_TYPE_SCHEDULE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_schedule_text, null);
                return new CreateScheduleHolder(view);
            }
            case VIEW_TYPE_DETAIL_WRITE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail, null);
                return new CreateDetailHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }
    CreateImageHolder.OnImageClickListener mListener_image;
    public void setOnImageClickListener(CreateImageHolder.OnImageClickListener listener) {
        mListener_image = listener;
    }

    CreateDetailHolder.OnDateClickListener mDateListener;
    public void setOnDateClickListener(CreateDetailHolder.OnDateClickListener listener) {
        mDateListener = listener;
    }

    CreateDetailHolder.OnTimeClickListener mTimeListener;
    public void setOnTimeClickListener(CreateDetailHolder.OnTimeClickListener listener) {
        mTimeListener = listener;
    }

    CreateDetailHolder.OnSchClickListener mListener_sch;
    public void setOnSchClickListener(CreateDetailHolder.OnSchClickListener listener) {
        mListener_sch = listener;
    }

    CreateScheduleHolder.OnSchDelClickListener mListener_sch_del;
    public void setOnSchDelClickListener(CreateScheduleHolder.OnSchDelClickListener listener) {
        mListener_sch_del = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {
            h_image = (CreateImageHolder)holder;
            h_image.setOnImageClickListener(mListener_image);
            h_image.setImage(bm);
            return;
        }
        position--;

        if (position == 0) {
            h_title = (CreateTitleHolder)holder;
            return;
        }
        position--;

        if(MySch.size() > 0) {
            if (position < MySch.size()) {
                h_sch = (CreateScheduleHolder)holder;
                h_sch.setSchedule(MySch.get(position), position);
                h_sch.setOnSchDelClickListener(mListener_sch_del);
                return ;
            }
            position-=MySch.size();
        }

        if (position == 0) {
            h_detail = (CreateDetailHolder)holder;
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
        return 3 + MySch.size();
    }
}