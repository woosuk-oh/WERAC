package kr.werac.yeah.werac;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DetailWriteViewHolder extends RecyclerView.ViewHolder {

    EditText edit_detail;
    Spinner spinner_area;
    EditText edit_date;
    EditText edit_time;
    EditText edit_fee;
    RadioGroup radio_hasmc;
    EditText edit_lm;

    public DetailWriteViewHolder(View itemView) {
        super(itemView);
        edit_detail = (EditText)itemView.findViewById(R.id.edit_detail);
        spinner_area = (Spinner)itemView.findViewById(R.id.spinner_area);
        edit_date = (EditText)itemView.findViewById(R.id.edit_date);
        edit_time = (EditText)itemView.findViewById(R.id.edit_time);
        edit_fee = (EditText)itemView.findViewById(R.id.edit_detail);
        radio_hasmc = (RadioGroup)itemView.findViewById(R.id.radio_hasmc);
        edit_lm = (EditText)itemView.findViewById(R.id.edit_lm);
    }

//    public void setProduct(WeracItem werac) {
//        this.werac = werac;
//
//        //Glide.with(thumbView.getContext()).load(werac.getThumbnailUrl()).into(thumbView);
//
//        nameView.setText(werac.getName());
//        descriptionView.setText(werac.getDescription());
//        scoreView.setText(""+werac.getScore());
//        downloadView.setText(""+werac.getDownloadCount());
//        detailView.setText(werac.getDetailDescription());
//    }
}
