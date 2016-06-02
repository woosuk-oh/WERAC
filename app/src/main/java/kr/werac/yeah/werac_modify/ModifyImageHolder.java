package kr.werac.yeah.werac_modify;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class ModifyImageHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    Bitmap myBm;
    WeracItem werac;

    public interface OnItemClickListener {
        void onItemClick(View view, WeracItem werac);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public ModifyImageHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.image_detail_werac);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, werac);
                }
            }
        });
    }

    public void setImageURL(WeracItem werac){
        Glide.with(imageView.getContext()).load(werac.getImage()).into(imageView);
    }

    public void setImage(Bitmap bm){
        if(bm != null) {
            myBm = bm;
            imageView.setImageBitmap(bm);
        }
        imageView.setImageResource(R.drawable.make_image);
    }

    public Bitmap getImage(){
        if(myBm != null)
            return myBm;
        return null;
    }
}
