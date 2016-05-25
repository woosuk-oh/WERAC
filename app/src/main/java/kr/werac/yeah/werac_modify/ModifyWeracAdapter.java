package kr.werac.yeah.werac_modify;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyWeracAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TITLE = 2;
    public static final int VIEW_TYPE_SCHEDULE = 3;
    public static final int VIEW_TYPE_DETAIL_WRITE = 4;


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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_werac_image, null);
                return new ModifyImageHolder(view);
            }
            case VIEW_TYPE_TITLE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_title, null);
                return new ModifyTitleHolder(view);
            }
            case VIEW_TYPE_SCHEDULE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_schedule, null);
                Button btn = (Button)view.findViewById(R.id.btn_add_sch);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return new ModifyScheduleHolder(view);
            }
            case VIEW_TYPE_DETAIL_WRITE : {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_create_detail, null);
                return new ModifyDetailHolder(view);
            }
        }
        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ModifyImageHolder h = (ModifyImageHolder)holder;
            h.setImage(werac);
            return;
        }
        position--;

        if (position == 0) {
            ModifyTitleHolder h = (ModifyTitleHolder)holder;
            h.setTitle(werac);
            return;
        }
        position--;

        if (position == 0) {
            ModifyScheduleHolder h = (ModifyScheduleHolder)holder;
            h.setSchedule(werac.getSchedule());
            return ;
        }
        position--;

        if (position == 0) {
            ModifyDetailHolder h = (ModifyDetailHolder)holder;
            h.setDetailWrite(werac);
            return;
        }
        position--;

        throw new IllegalArgumentException("invalid position");
    }

    @Override
    public int getItemCount() {
        if(werac != null) {
            return 4;
        }else
            return 0;
    }
}