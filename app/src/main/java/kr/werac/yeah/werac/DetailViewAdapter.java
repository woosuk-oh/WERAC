package kr.werac.yeah.werac;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DetailViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TITLE = 2;
    public static final int VIEW_TYPE_SCHEDULE_VIEW = 3;
    public static final int VIEW_TYPE_DETAIL_VIEW = 4;
    public static final int VIEW_TYPE_STAFF = 5;
    public static final int VIEW_TYPE_JOINUSERS = 6;
    public static final int VIEW_TYPE_COMMENTS = 7;


    WeracItem werac;

    public void setWerac(WeracItem werac) {
        this.werac = werac;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_TYPE_IMAGE;
        position--;
        if (position == 0) return VIEW_TYPE_TITLE;
        position--;
        if (werac.getSchedule().size() > 0) {
            if (position == 0) return VIEW_TYPE_SCHEDULE_VIEW;
            position--;
            if (position < werac.getSchedule().size()) {
                return VIEW_TYPE_SCHEDULE_VIEW;
            }
            position-=werac.getSchedule().size();
        }
        if (position == 0) return VIEW_TYPE_DETAIL_VIEW;
        position--;
        if (position == 0) return VIEW_TYPE_STAFF;
        position--;
        if (position == 0) return VIEW_TYPE_JOINUSERS;
        position--;
        if (position == 0) return VIEW_TYPE_COMMENTS;
        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_IMAGE : {
                View view = new ImageView(parent.getContext());
                return new ImageViewHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_title, null);
                return new TitleViewHolder(view);
            }
            case VIEW_TYPE_SCHEDULE_VIEW : {
                View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
                return new ScheduleViewHolder(view);
            }
            case VIEW_TYPE_DETAIL_VIEW : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail_write, null);
                return new DetailWriteViewHolder(view);
            }
            case VIEW_TYPE_STAFF : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail_write, null);
                return new DetailWriteViewHolder(view);
            }
            case VIEW_TYPE_JOINUSERS : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail_write, null);
                return new DetailWriteViewHolder(view);
            }
            case VIEW_TYPE_COMMENTS : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail_write, null);
                return new DetailWriteViewHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ImageViewHolder h = (ImageViewHolder)holder;
//            h.setImage(werac);
            return;
        }
        position--;

        if (position == 0) {
            TitleViewHolder h = (TitleViewHolder)holder;
//            h.setTitle(werac);
            return;
        }

        if (werac.getSchedule().size() > 0) {
            if (position == 0) {
                ScheduleViewHolder h = (ScheduleViewHolder)holder;
//                h.setSchedule(werac.getSchedule().get(position));
                return ;
            }
            position--;
            if (position < werac.getSchedule().size()) {
                ScheduleViewHolder h = (ScheduleViewHolder)holder;
//                h.setSchedule(werac.getSchedule().get(position));
                return ;
            }
            position-=werac.getSchedule().size();
        }

        if (position == 0) {
            DetailWriteViewHolder h = (DetailWriteViewHolder)holder;
//            h.setDetailWrite(werac);
            return;
        }

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public int getItemCount() {
        if (werac == null) return 0;
        int size = 2;
        if (werac.getSchedule().size() > 0) {
            size += 1;
            size += werac.getSchedule().size();
        }
        size += 1;
        return size;
    }
}
