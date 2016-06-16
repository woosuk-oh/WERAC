package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.User;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.PropertyManager;

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
    public static final int VIEW_TYPE_RECOMMENT_LIST = 10;

    WeracItem werac;
    DetailCommentEnterHolder h_cmmt_enter;
    int cmmtTotal;
    int cmmtTotalIndex1 = 0;
    int cmmtIndex1 = 0;
    int cmmtCount2 = 0;
    int cmmtTotalIndex2 = 0;

    public void setWerac(WeracItem werac) {
        this.werac = werac;
        notifyDataSetChanged();
    }

    public void addCommt(Comment cmmt){
        List<Comment> myCmmt = new ArrayList<>();
        myCmmt = werac.getComments();
        cmmt.setUser(PropertyManager.getInstance().getUser());
        cmmt.setLike(0);
        myCmmt.add(cmmt);
        werac.setComments(myCmmt);
        notifyDataSetChanged();
    }

    public void equalCommt(List<Comment> myCmmt){
        werac.setComments(myCmmt);
        notifyDataSetChanged();
    }

    public void add_recomment(Comment cmmt){
        List<Comment> myCmmt = new ArrayList<>();
        myCmmt = werac.getComments();
        cmmt.setUser(PropertyManager.getInstance().getUser());
        cmmt.setLike(0);
        myCmmt.add(cmmt);
        werac.setComments(myCmmt);
        notifyDataSetChanged();
    }

    public void modify_comment(Comment cmmt){
        for(int i = 0; i < werac.getComments().size(); i++){
            if(werac.getComments().get(i).getCmmtid() == cmmt.getCmmtid()){
                werac.getComments().set(i, cmmt);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public int get_commentNum(){
        if(werac.getComments() != null)
            return werac.getComments().size();
        else
            return 0;
    }

    public int get_status(){
            return werac.getStatus();
    }

    public void remove_comment(Comment cmmt){
        for(int i = 0; i < werac.getComments().size(); i++){
            if(werac.getComments().get(i).getCmmtid() == cmmt.getCmmtid()){
                werac.getComments().remove(i);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public void participate_user(User user){
        List<User> myG = new ArrayList<>();
        myG = werac.getGuests();
        myG.add(user);
        werac.setGuests(myG);
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
            case VIEW_TYPE_RECOMMENT_LIST : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_recomment_text, null);
                return new DetailReCommentListHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    DetailStaffHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(DetailStaffHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    DetailCommentEnterHolder.OnCmmtEnterClickListener mListener_cmmt;
    public void setOnCmmtEnterListener(DetailCommentEnterHolder.OnCmmtEnterClickListener listener) {
        mListener_cmmt = listener;
    }

    DetailCommentListHolder.OnCommentItemClickListener mListener_cmmt_item;
    public void setOnCmmtItemClickListener(DetailCommentListHolder.OnCommentItemClickListener listener) {
        mListener_cmmt_item = listener;
    }

    DetailCommentListHolder.OnCommentLikeClickListener mListener_cmmt_like;
    public void setOnCmmtLikeClickListener(DetailCommentListHolder.OnCommentLikeClickListener listener) {
        mListener_cmmt_like = listener;
    }

    DetailGuestsHolder.OnGuestListClickListener mListener_guest_list;
    public void setOnGuestListClickListener(DetailGuestsHolder.OnGuestListClickListener listener) {
        mListener_guest_list = listener;
    }

    DetailReCommentListHolder.OnReCommentItemClickListener mListener_recmmt_item;
    public void setOnReCmmtItemClickListener(DetailReCommentListHolder.OnReCommentItemClickListener listener) {
        mListener_recmmt_item = listener;
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
            if(werac.getStatus() != 3)
                h.setOnItemClickListener(mListener);
            return;
        }
        position--;

        if (position == 0) {
            DetailGuestsHolder h = (DetailGuestsHolder)holder;
            h.setGuests(werac);
            if(werac.getStatus() == 2)
                h.setOnGuestListClickListener(mListener_guest_list);
            return;
        }
        position--;

        if (position == 0) {
            h_cmmt_enter = (DetailCommentEnterHolder)holder;
            h_cmmt_enter.setComments(werac);
            if(werac.getStatus() != 3)
                h_cmmt_enter.setOnCmmtClickListener(mListener_cmmt);
            return;
        }

        position--;

        if(werac.getComments().size() > 0) {
            if (position < werac.getComments().size()) {
                DetailCommentListHolder h_cmmt_list = (DetailCommentListHolder)holder;
                h_cmmt_list.setmCmt_item(werac.getComments().get(position));
                if(werac.getStatus() != 3) {
                    h_cmmt_list.setOnCommentItemClickListener(mListener_cmmt_item);
                    h_cmmt_list.setOnCommentLikeClickListener(mListener_cmmt_like);
                }
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
        cmmtTotal = 0;
        if(werac != null){
            for(int i = 0; i < werac.getComments().size(); i++) {
                if(werac.getComments().get(i).getReply() != null)
                cmmtTotal += werac.getComments().get(i).getReply().size();
            }
            cmmtTotal += werac.getComments().size();
            return 11 + cmmtTotal;
        }else
            return 0;
    }
}
