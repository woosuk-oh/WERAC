package kr.werac.yeah.werac_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class DetailReCommentListHolder extends RecyclerView.ViewHolder {

    TextView tv_cmt_writer;
    CircleImageView image_cmt_writer;
    TextView tv_cmt_content;
    Comment comment;

    public interface OnReCommentItemClickListener {
        void onItemClick(View view, Comment comment);
    }

    OnReCommentItemClickListener mListener;
    public void setOnReCommentItemClickListener(OnReCommentItemClickListener listener) {
        mListener = listener;
    }

    public DetailReCommentListHolder(View itemView) {
        super(itemView);
        tv_cmt_writer = (TextView)itemView.findViewById(R.id.tv_rcmt_writer);
        image_cmt_writer = (CircleImageView)itemView.findViewById(R.id.image_rcmt_writer);
        tv_cmt_content = (TextView)itemView.findViewById(R.id.tv_rcmt_content);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, comment);
                }
            }
        });
    }

    public void setmCmt_item(Comment cmt_item){
        comment = cmt_item;
        tv_cmt_writer.setText(""+cmt_item.getUser().getName());
        if(cmt_item.getUser().getProfile_image() == null)
            image_cmt_writer.setImageResource(R.drawable.profile_default);
        else
            Glide.with(image_cmt_writer.getContext()).load(cmt_item.getUser().getProfile_image()).into(image_cmt_writer);
        tv_cmt_content.setText(cmt_item.getContent());
    }
}
