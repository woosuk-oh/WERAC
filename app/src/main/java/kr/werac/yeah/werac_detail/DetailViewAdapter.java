package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
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
    public static final int VIEW_TYPE_COMMENT_ENTER = 7;
    public static final int VIEW_TYPE_COMMENT_LIST = 8;
    public static final int VIEW_TYPE_DUMMY = 9;

    WeracItem werac;
    DetailCommentEnterHolder h_cmmt_enter;

    public void setWerac(WeracItem werac) {
        this.werac = werac;
        notifyDataSetChanged();
    }

    public void addCommt(Comment cmmt){
        List<Comment> myCmmt = new ArrayList<>();
        myCmmt = werac.getComments();
        myCmmt.add(cmmt);
        werac.setComments(myCmmt);
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
        if (position < 3) return VIEW_TYPE_DETAIL_VIEW;
        position-=3;
        if (position < 2) return VIEW_TYPE_STAFF;
        position-=2;
        if (position == 0) return VIEW_TYPE_JOINUSERS;
        position--;
        if (position == 0) return VIEW_TYPE_COMMENT_ENTER;
        position--;
       if(werac.getComments().size() > 0) {
            if (position < werac.getComments().size())
                return VIEW_TYPE_COMMENT_LIST;
            position -= werac.getComments().size();
        }
        if (position == 0) return VIEW_TYPE_DUMMY;
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
            case VIEW_TYPE_COMMENT_ENTER : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_comment_enter, null);
                return new DetailCommentEnterHolder(view);
            }
            case VIEW_TYPE_COMMENT_LIST : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_comment_text, null);
                return new DetailCommentListHolder(view);
            }
            case VIEW_TYPE_DUMMY : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dummy, null);
                return new DetailDummyHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    DetailStaffHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(DetailStaffHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    DetailCommentEnterHolder.OnCmmtClickListener mListener_cmmt;
    public void setOnCmmtClickListener(DetailCommentEnterHolder.OnCmmtClickListener listener) {
        mListener_cmmt = listener;
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
            h.setGuests(werac, werac.getGuests());
            return;
        }
        position--;

        if (position == 0) {
            h_cmmt_enter = (DetailCommentEnterHolder)holder;
            h_cmmt_enter.setComments(werac.getComments());
            h_cmmt_enter.setOnCmmtClickListener(mListener_cmmt);
            return;
        }

        position--;

        if(werac.getComments().size() > 0) {
            if (position < werac.getComments().size()) {
                DetailCommentListHolder h_cmmt_list = (DetailCommentListHolder)holder;
                h_cmmt_list.setmCmt_item(werac.getComments().get(position));
                return ;
            }
            position -= werac.getComments().size();
        }

        if (position == 0) {
            DetailDummyHolder h = (DetailDummyHolder)holder;
            return;
        }

        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public int getItemCount() {
        if(werac != null)
            return 11 + werac.getComments().size();
        else
            return 0;
    }
}
