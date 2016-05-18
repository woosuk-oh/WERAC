package kr.werac.yeah.werac;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import kr.werac.yeah.R;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class TitleViewHolder extends RecyclerView.ViewHolder {

    EditText text_title_create;
    EditText text_subtitle_create;
    public TitleViewHolder(View itemView) {
        super(itemView);
        text_title_create = (EditText)itemView.findViewById(R.id.text_title_create);
        text_subtitle_create = (EditText)itemView.findViewById(R.id.text_subtitle_create);
    }
}
