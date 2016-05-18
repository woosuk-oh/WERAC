package kr.werac.yeah.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class WeracItemHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView titleView;
    TextView subtitleView;
    WeracItem mWerac;

    public interface OnItemClickListener {
        public void onItemClick(View view, WeracItem mWerac);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public WeracItemHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.image_icon);
        titleView = (TextView)itemView.findViewById(R.id.text_title);
        subtitleView = (TextView)itemView.findViewById(R.id.text_subtitle);

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
        titleView.setText(Werac.getTitle());
        subtitleView.setText(Werac.getSubtitle());
        imageView.setImageResource(Werac.getPicturePath());
    }
}
