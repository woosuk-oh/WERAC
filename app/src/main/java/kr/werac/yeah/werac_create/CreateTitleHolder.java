package kr.werac.yeah.werac_create;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class CreateTitleHolder extends RecyclerView.ViewHolder {

    EditText text_title_create;
    EditText text_subtitle_create;
    WeracItem werac;

    public CreateTitleHolder(View itemView) {
        super(itemView);
        werac = new WeracItem();
        text_title_create = (EditText)itemView.findViewById(R.id.text_title_create);
        text_subtitle_create = (EditText)itemView.findViewById(R.id.text_subtitle_create);
    }

    public WeracItem getTitle(){
        werac.setTitle(text_title_create.getText().toString());
        werac.setTitle_sub(text_subtitle_create.getText().toString());
        return werac;
    }
}
