package kr.werac.yeah.werac_detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.PropertyManager;

/**
 * Created by Tacademy on 2016-05-19.
 */
public class DetailCommentEnterHolder extends RecyclerView.ViewHolder {

    TextView comment_title;
    Button btn_comment_enter;
    EditText edit_comment;

    public interface OnCmmtEnterClickListener {
        void onItemClick(View view, EditText edit_comment);
    }

    OnCmmtEnterClickListener mListener_cmmt;
    public void setOnCmmtClickListener(OnCmmtEnterClickListener listener) {
        mListener_cmmt = listener;
    }

    public DetailCommentEnterHolder(View view) {
        super(view);
        comment_title = (TextView)view.findViewById(R.id.comment_title);
        edit_comment = (EditText)view.findViewById(R.id.edit_comment);
        btn_comment_enter = (Button)view.findViewById(R.id.btn_comment_enter);
        btn_comment_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener_cmmt != null){
                    mListener_cmmt.onItemClick(v, edit_comment);
                }
            }
        });
    }

    public void setComments(WeracItem werac){
        comment_title.setText("댓글 " + werac.getComments().size());
        if(werac.getStatus() == 3)
            edit_comment.setVisibility(View.INVISIBLE);
    }

    public EditText getComments(){
        return edit_comment;
    }
}
