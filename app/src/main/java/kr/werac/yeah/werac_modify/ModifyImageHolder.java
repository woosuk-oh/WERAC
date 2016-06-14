package kr.werac.yeah.werac_modify;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyImageHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    Bitmap myBm;
    WeracItem werac;

    public interface OnImageClickListener {
        void onItemClick(View view, WeracItem werac);
    }

    OnImageClickListener mListener_image;
    public void setOnImageClickListener(OnImageClickListener listener) {
        mListener_image = listener;
    }


    public ModifyImageHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.image_detail_werac);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener_image != null) {
                    mListener_image.onItemClick(v, werac);
                }
            }
        });
    }

    public void setImage(String path, int myW, int myH){
        if(path != null) {
            float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
            Glide.with(imageView.getContext()).load(path).override((int)(360*scale), (int)(360 * myH / myW * scale)).into(imageView);
            //Glide.with(imageView.getContext()).load(path).into(imageView);
        }else{
            imageView.setImageResource(R.drawable.make_image);
        }
    }

    public void setImageURL(String path){
        if(path != null) {
            Glide.with(imageView.getContext()).load(path).into(imageView);
        }else{
            imageView.setImageResource(R.drawable.make_image);
        }
    }
}
