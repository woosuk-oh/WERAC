package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class DetailImageHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public DetailImageHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.image_detail_werac);
    }

    public void setImage(WeracItem werac) {
        Glide.with(imageView.getContext()).load(werac.getImage()).into(imageView);
//        imageView.setImageResource(R.drawable.p3);
//        imageView.setImageResource(werac.getPicturePath());
    }
}
