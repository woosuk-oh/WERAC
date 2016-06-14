package kr.werac.yeah.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.werac_detail.DetailViewActivity;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class ImagePagerAdapter extends PagerAdapter {

    public static final int VIEW_COUNT = 2;
    ImageView iv_viewpager;

    public interface OnItemClickListener {
        void onItemClick(View view, String mWhatImage);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getCount() {
        return VIEW_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        iv_viewpager = new ImageView(MyApplication.getContext());

        switch (position){
            case 0:
                Glide.with(iv_viewpager.getContext()).load(R.drawable.vp_other_explain).centerCrop().into(iv_viewpager);
                iv_viewpager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(v, "other");
                        }
                    }
                });
                container.addView(iv_viewpager);
                break;
            case 1:
                Glide.with(iv_viewpager.getContext()).load(R.drawable.vp_mc_explain).centerCrop().into(iv_viewpager);
                iv_viewpager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClick(v, "mc");
                        }
                    }
                });
                container.addView(iv_viewpager);
                break;
        }

        return iv_viewpager;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View)object;
        container.removeView(v);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
