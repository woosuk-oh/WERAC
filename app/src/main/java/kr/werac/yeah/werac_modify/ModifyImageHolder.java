package kr.werac.yeah.werac_modify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyImageHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public ModifyImageHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.image_detail_werac);
    }

    public void setImage(WeracItem werac){
        imageView.setImageResource(R.drawable.p3);
    }
}
