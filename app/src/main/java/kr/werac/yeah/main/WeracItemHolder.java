package kr.werac.yeah.main;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class WeracItemHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView titleView;
    TextView text_main_area;
    TextView text_main_like;
    WeracItem mWerac;
    ImageView image_main_mc;

    public interface OnItemClickListener {
        void onItemClick(View view, WeracItem mWerac);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public WeracItemHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.image_icon);
        titleView = (TextView)itemView.findViewById(R.id.text_title);
        text_main_area = (TextView)itemView.findViewById(R.id.text_main_area);
        text_main_like = (TextView)itemView.findViewById(R.id.text_main_like);
        image_main_mc = (ImageView)itemView.findViewById(R.id.image_main_mc);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mWerac);
                }
            }
        });
    }

    public void setmWerac(WeracItem Werac) {
        mWerac = Werac;
        int width = Werac.getImage_size().get(0);
        int height = Werac.getImage_size().get(1);
        float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        float myHeight = 171 * height / width * scale;
        imageView.setLayoutParams(new RelativeLayout.LayoutParams((int)(171*scale), (int)myHeight));
        Glide.with(imageView.getContext()).load(Werac.getImage()).into(imageView);
        titleView.setText(Werac.getTitle());
        text_main_area.setText(Werac.getLocation_area());
        text_main_like.setText(Werac.getLike()+"");
        if(Werac.isHas_mc() == true){
            image_main_mc.setVisibility(View.VISIBLE);
        }else
            image_main_mc.setVisibility(View.INVISIBLE);
    }
}
