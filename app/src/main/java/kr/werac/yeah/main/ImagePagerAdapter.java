package kr.werac.yeah.main;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class ImagePagerAdapter extends PagerAdapter {
    Context mContext;
    public static final int VIEW_COUNT = 3;
    WeracItem weracItem;

    public ImagePagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return VIEW_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView v = new ImageView(mContext);

        switch (position){
            case 0:
                v.setImageResource(R.drawable.p3);
                v.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(v);
                break;
            case 1:
                v.setImageResource(R.drawable.p6);
                v.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(v);
                break;
            case 2:
                v.setImageResource(R.drawable.p8);
                v.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(v);
                break;
        }
        return v;
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
