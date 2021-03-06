package kr.werac.yeah.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    ImageView iv_close;

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
        iv_close = (ImageView)itemView.findViewById(R.id.iv_close);

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
        if(Werac.getStatus() == 3) {
            iv_close.setLayoutParams(new RelativeLayout.LayoutParams((int)(171*scale), (int)myHeight));
            iv_close.setVisibility(View.VISIBLE);
            Glide.with(imageView.getContext()).load(Werac.getImage()).into(imageView);
            titleView.setText("[완료]" + Werac.getTitle());
        }
        else {
            iv_close.setVisibility(View.INVISIBLE);
            Glide.with(imageView.getContext()).load(Werac.getImage()).into(imageView);
            titleView.setText(Werac.getTitle());
        }
        text_main_area.setText(Werac.getLocation_area());
        text_main_like.setText(Werac.getLike()+"");
        image_main_mc.setVisibility(View.INVISIBLE);

        if(Werac.isHas_mc() == true && Werac.getMc() == null){
            image_main_mc.setVisibility(View.VISIBLE);
        }else
            image_main_mc.setVisibility(View.INVISIBLE);
    }
}
