package kr.werac.yeah.werac_detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.User;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.manager.PropertyManager;
import kr.werac.yeah.mypage.CreaterPageActivity;
import kr.werac.yeah.mypage.MCPageActivity;
import okhttp3.Request;

public class DetailWeracFragment extends Fragment {

    RecyclerView listView;
    DetailViewAdapter mAdapter;
    GridLayoutManager mLayoutManager;
    static int this_MId;

    public static DetailWeracFragment newInstance(int thisMId) {
        DetailWeracFragment fragment = new DetailWeracFragment();
        this_MId = thisMId;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DetailViewAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_werac, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list_detail);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 6);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mAdapter.getItemViewType(position);
                if (type == DetailViewAdapter.VIEW_TYPE_STAFF) {
                    return 3;
                } else if (type == DetailViewAdapter.VIEW_TYPE_DETAIL_VIEW) {
                    return 2;
                } else {
                    return 6;
                }
            }
        });
        listView.setLayoutManager(mLayoutManager);
        mAdapter.setOnItemClickListener(new DetailStaffHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, WeracItem werac, int who, User mcId) {
                if(who == 1) {
                    if(mcId.getUid() != 0) {
                        Intent intent1 = new Intent(getActivity(), MCPageActivity.class);
                        intent1.putExtra(MCPageActivity.EXTRA_MC_ID, werac.getMc_id().getUid());
                        startActivity(intent1);
                    }else if(mcId.getUid() == 0){
//                        dialog
                        Toast.makeText(getContext(), "진행자로 지원되었습니다", Toast.LENGTH_SHORT).show();
                    }
                }else if(who == 2) {
                    Intent intent2 = new Intent(getActivity(), CreaterPageActivity.class);
                    intent2.putExtra(CreaterPageActivity.EXTRA_CREATER_ID, werac.getCreator().getUid());
                    startActivity(intent2);
                }
            }
        });

        mAdapter.setOnCmmtClickListener(new DetailCommentEnterHolder.OnCmmtEnterClickListener() {
            @Override
            public void onItemClick(View view, EditText edit_comment) {
                Comment newComment = new Comment();
                User user = new User();
                user = PropertyManager.getInstance().getUser();
//                user.setProfile_image();
                newComment.setUser(user);
                newComment.setContent(edit_comment.getText().toString());
                newComment.setLike(0);
                mAdapter.addCommt(newComment);
                addComment(edit_comment.getText().toString());
                edit_comment.setText("");
            }
        });

        mAdapter.setOnCmmtItemClickListener(new DetailCommentListHolder.OnCommentItemClickListener() {
            @Override
            public void onItemClick(View view, TextView writer) {
                if(writer.getText().toString() == "2번이"){

                }else {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater = getActivity().getLayoutInflater();

                    View AlertView = inflater.inflate(R.layout.dialog_recomment_add, null);
                    alert.setView(AlertView);
                    final EditText et_recomment = (EditText) AlertView.findViewById(R.id.et_recomment);

                    alert.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Comment newComment = new Comment();
                            User user = new User();
                            user.setUid(2);
                            newComment.setUser(user);
                            newComment.setContent(et_recomment.getText().toString());
                            newComment.setLike(0);
                            mAdapter.add_recomment(newComment);
                        }
                    });

                    alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });

                    alert.show();
                }
            }
        });

        setData();
        return view;
}

    private void setData() {
        NetworkManager.getInstance().getWeracDetail(getContext(), this_MId, new NetworkManager.OnResultListener<WeracItem>() {
            @Override
            public void onSuccess(Request request, WeracItem result) {
                mAdapter.setWerac(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addComment(String myCmmt) {
        NetworkManager.getInstance().getWeracAddComment(getContext(), this_MId, myCmmt, new NetworkManager.OnResultListener<Comment>() {
            @Override
            public void onSuccess(Request request, Comment result) {
//                mAdapter.addCommt(result);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
