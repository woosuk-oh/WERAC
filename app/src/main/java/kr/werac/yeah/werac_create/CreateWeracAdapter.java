package kr.werac.yeah.werac_create;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

    public WeracItem getWerac(){
        werac.setTitle(h_title.getTitle().getTitle());
        werac.setTitle_sub(h_title.getTitle().getTitle_sub());
        werac.setSchedule(h_sch.getSchedule());
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
        if (position == 0) return VIEW_TYPE_SCHEDULE;
        position--;
        if (position == 0) return VIEW_TYPE_DETAIL_WRITE;
        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_IMAGE : {
                View view = new ImageView(parent.getContext());
                return new CreateImageHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_title, null);
                return new CreateTitleHolder(view);
            }
            case VIEW_TYPE_SCHEDULE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_schedule, null);
                return new CreateScheduleHolder(view);
            }
            case VIEW_TYPE_DETAIL_WRITE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail, null);
                return new CreateDetailHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }
    CreateImageHolder.OnItemClickListener mListener_image;
    public void setOnItemClickListener(CreateImageHolder.OnItemClickListener listener) {
        mListener_image = listener;
    }

    CreateDetailHolder.OnItemClickListenerDate mListener_detail_date;
    public void setOnItemClickListenerDate(CreateDetailHolder.OnItemClickListenerDate listener) {
        mListener_detail_date = listener;
    }

    CreateDetailHolder.OnItemClickListenerTime mListener_detail_time;
    public void setOnItemClickListenerTime(CreateDetailHolder.OnItemClickListenerTime listener) {
        mListener_detail_time = listener;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            h_image = (CreateImageHolder)holder;
            h_image.setOnItemClickListener(mListener_image);
            h_image.setImage(bm);
            return;
        }
        position--;

        if (position == 0) {
                h_title = (CreateTitleHolder)holder;
                return;
            }
            position--;

            if (position == 0) {
                h_sch = (CreateScheduleHolder)holder;
                h_sch.setSchedule();
            return ;
        }
        position--;

        if (position == 0) {
            h_detail = (CreateDetailHolder)holder;
            h_detail.setOnItemClickListenerDate(mListener_detail_date);
            h_detail.setOnItemClickListenerTime(mListener_detail_time);
            return;
        }
        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}