package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DetailTitleHolder extends RecyclerView.ViewHolder {

    TextView tv_title;
    TextView tv_subtitle;
    public DetailTitleHolder(View itemView) {
        super(itemView);
        tv_title = (TextView)itemView.findViewById(R.id.tv_title);
        tv_subtitle = (TextView)itemView.findViewById(R.id.tv_subtitle);
    }

    public void setTitle(WeracItem werac){
        tv_title.setText(werac.getTitle());
        tv_subtitle.setText(werac.getTitle_sub());
    }
}
