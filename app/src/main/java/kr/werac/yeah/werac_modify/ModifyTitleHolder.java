package kr.werac.yeah.werac_modify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyTitleHolder extends RecyclerView.ViewHolder {

    EditText text_title_create;
    EditText text_subtitle_create;
    WeracItem werac;

    public ModifyTitleHolder(View itemView) {
        super(itemView);
        text_title_create = (EditText)itemView.findViewById(R.id.text_title_create);
        text_subtitle_create = (EditText)itemView.findViewById(R.id.text_subtitle_create);
        werac = new WeracItem();
    }

    public void setTitle(WeracItem werac){
        text_title_create.setText(werac.getTitle());
        text_subtitle_create.setText(werac.getTitle_sub());
    }

    public WeracItem getTitle(){
        werac.setTitle(text_title_create.getText().toString());
        werac.setTitle_sub(text_subtitle_create.getText().toString());
        return werac;
    }
}
