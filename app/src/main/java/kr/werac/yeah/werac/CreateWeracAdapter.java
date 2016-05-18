package kr.werac.yeah.werac;

import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.jar.Attributes;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

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
                return new ImageViewHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_title, null);
                return new TitleViewHolder(view);
            }
            case VIEW_TYPE_SCHEDULE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_schedule, null);
                Button btn = (Button)view.findViewById(R.id.btn_add_sch);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return new ScheduleViewHolder(view);
            }
            case VIEW_TYPE_DETAIL_WRITE : {
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
        position--;

        if (position == 0) {
            ScheduleViewHolder h = (ScheduleViewHolder)holder;
//                h.setSchedule(werac.getSchedule().get(position));
            return ;
        }
        position--;

        if (position == 0) {
            DetailWriteViewHolder h = (DetailWriteViewHolder)holder;
//            h.setDetailWrite(werac);
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