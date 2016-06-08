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

    public static final int VIEW_COUNT = 3;
    WeracItem werac1, werac2, werac3;
    ImageView iv_viewpager;
    WeracItem werac;

    public interface OnItemClickListener {
        void onItemClick(View view, WeracItem mWerac);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getCount() {
        return VIEW_COUNT;
    }

    public void InitialData(WeracItem Mwerac1, WeracItem Mwerac2, WeracItem Mwerac3){
        werac1 = new WeracItem();
        werac1 = Mwerac1;

        werac2 = new WeracItem();
        werac2 = Mwerac2;

        werac3 = new WeracItem();
        werac3 = Mwerac3;
    }

    public WeracItem setData(int position){
        if(position == 0)
            werac = werac1;
        else if(position == 1)
            werac = werac2;
        else if(position == 2)
            werac = werac3;
        return werac;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        werac = new WeracItem();
        iv_viewpager = new ImageView(MyApplication.getContext());
        iv_viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, werac);
                }
            }
        });

        switch (position){
            case 0:
                werac = setData(position);
                if(werac != null) {
                    Glide.with(iv_viewpager.getContext()).load(werac.getImage()).centerCrop().into(iv_viewpager);

                }else {
                    iv_viewpager.setImageResource(R.drawable.p3);
                    iv_viewpager.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                container.addView(iv_viewpager);
                break;
            case 1:
                werac = setData(position);
                if(werac != null)
                    Glide.with(iv_viewpager.getContext()).load(werac.getImage()).centerCrop().into(iv_viewpager);
                else {
                    iv_viewpager.setImageResource(R.drawable.p3);
                    iv_viewpager.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                container.addView(iv_viewpager);
                break;
            case 2:
                werac = setData(position);
                if(werac != null)
                    Glide.with(iv_viewpager.getContext()).load(werac.getImage()).centerCrop().into(iv_viewpager);
                else {
                    iv_viewpager.setImageResource(R.drawable.p3);
                    iv_viewpager.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
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
