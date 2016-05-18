package kr.werac.yeah.werac;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import kr.werac.yeah.R;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView;
        imageView.setImageResource(R.drawable.image_add);
    }
}
