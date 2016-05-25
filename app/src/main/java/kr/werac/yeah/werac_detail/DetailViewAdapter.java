package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        if (position == 0) return VIEW_TYPE_SCHEDULE_VIEW;
        position--;
//        if (werac.getSchedule().size() > 0) {
//            if (position < werac.getSchedule().size()-1) {
//                return VIEW_TYPE_SCHEDULE_VIEW;
//            }
//            position-=werac.getSchedule().size();
//        }
        if (position < 3) return VIEW_TYPE_DETAIL_VIEW;
        position-=3;
        if (position < 2) return VIEW_TYPE_STAFF;
        position-=2;
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_werac_image, null);
                return new DetailImageHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_title, null);
                return new DetailTitleHolder(view);
            }
            case VIEW_TYPE_SCHEDULE_VIEW : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_schedule, null);
                return new DetailSchedulesHolder(view);
            }
            case VIEW_TYPE_DETAIL_VIEW : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_detail, null);
                return new DetailDetailHolder(view);
            }
            case VIEW_TYPE_STAFF : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_staff, null);
                return new DetailStaffHolder(view);
            }
            case VIEW_TYPE_JOINUSERS : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_guests, null);
                return new DetailGuestsHolder(view);
            }
            case VIEW_TYPE_COMMENTS : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_comments, null);
                return new DetailCommentsHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    DetailStaffHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(DetailStaffHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            DetailImageHolder h = (DetailImageHolder)holder;
            h.setImage(werac);
            return;
        }
        position--;

        if (position == 0) {
            DetailTitleHolder h = (DetailTitleHolder)holder;
            h.setTitle(werac);
            return;
        }
        position--;

        if (position == 0) {
            DetailSchedulesHolder h = (DetailSchedulesHolder)holder;
            h.setSchedule(werac.getSchedule());
            return;
        }
        position--;
//        if (werac.getSchedule().size() > 0) {
//            if (position < werac.getSchedule().size()) {
//                DetailSchedulesHolder h = (DetailSchedulesHolder)holder;
//                h.setSchedule(werac.getSchedule());
//                return ;
//            }
//            position -= werac.getSchedule().size();
//        }

        if (position == 0) {
            DetailDetailHolder h = (DetailDetailHolder) holder;
            h.setDetailDetail(werac, 0);
            return;
        }
        position--;

        if (position == 0) {
            DetailDetailHolder h = (DetailDetailHolder) holder;
            h.setDetailDetail(werac, 1);
            return;
        }
        position--;

        if (position == 0) {
            DetailDetailHolder h = (DetailDetailHolder) holder;
            h.setDetailDetail(werac, 2);
            return;
        }
        position--;

        if (position == 0) {
            DetailStaffHolder h = (DetailStaffHolder) holder;
            h.setStaff(werac, 0);
            h.setOnItemClickListener(mListener);
            return;
        }
        position--;

        if (position == 0) {
            DetailStaffHolder h = (DetailStaffHolder) holder;
            h.setStaff(werac, 1);
            h.setOnItemClickListener(mListener);
            return;
        }
        position--;

        if (position == 0) {
            DetailGuestsHolder h = (DetailGuestsHolder)holder;
            h.setGuests(werac, werac.getGuests_id());
            return;
        }
        position--;
//
//        if (werac.getGuests_id().size() > 0) {
//            if (position < werac.getGuests_id().size()) {
////                DetailGuestsHolder h = (DetailGuestsHolder)holder;
////                h.setGuests(werac, werac.getGuests_id());
//                return ;
//            }
//            position-=werac.getGuests_id().size();
//        }

        if (position == 0) {
            DetailCommentsHolder h = (DetailCommentsHolder)holder;
            h.setComments(werac.getComments());
            return;
        }

        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public int getItemCount() {
        if(werac != null)
            return 10;
        else
            return 0;
    }
}
