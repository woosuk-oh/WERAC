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
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.Result;
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
            public void onItemClick(View view, WeracItem werac, int who) {
                if(who == 1) {
                    if(werac.isHas_mc() == true && werac.getMc() != null) {
                        Intent intent1 = new Intent(getActivity(), MCPageActivity.class);
                        intent1.putExtra(MCPageActivity.EXTRA_MC_ID, werac.getMc().getUid());
                        intent1.putExtra(CreaterPageActivity.EXTRA_CREATER_ID, werac.getCreator().getUid());
                        startActivity(intent1);
                    }else if(werac.isHas_mc() == true){
//                        dialog
                        applyMC();
                    }
                }else if(who == 2) {
                    Intent intent2 = new Intent(getActivity(), CreaterPageActivity.class);
                    intent2.putExtra(CreaterPageActivity.EXTRA_CREATER_ID, werac.getCreator().getUid());
                    startActivity(intent2);
                }
            }
        });

        mAdapter.setOnCmmtEnterListener(new DetailCommentEnterHolder.OnCmmtEnterClickListener() {
            @Override
            public void onItemClick(View view, EditText edit_comment) {
                Comment newComment = new Comment();
                User user = new User();
                user = PropertyManager.getInstance().getUser();
                newComment.setUser(user);
                newComment.setContent(edit_comment.getText().toString());
                newComment.setLike(0);
                mAdapter.addCommt(newComment);
                addComment(newComment);
                edit_comment.setText("");
            }
        });

        mAdapter.setOnCmmtItemClickListener(new DetailCommentListHolder.OnCommentItemClickListener() {
            @Override
            public void onItemClick(View view, final Comment comment) {
                if(comment.getUser().getUid() == PropertyManager.getInstance().getUser().getUid()){

                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater = getActivity().getLayoutInflater();

                    View AlertView = inflater.inflate(R.layout.dialog_recomment_add, null);
                    alert.setView(AlertView);
                    final EditText et_modi_comment = (EditText) AlertView.findViewById(R.id.et_recomment);
                    et_modi_comment.setText(comment.getContent());

                    alert.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            comment.setContent(et_modi_comment.getText().toString());
                            mAdapter.modify_comment(comment);
                            modifyComment(comment);
                        }
                    });

                    alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });

                    alert.setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            mAdapter.remove_comment(comment);
                            removeComment(comment);
                        }
                    });

                    alert.show();
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
                            user = PropertyManager.getInstance().getUser();
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

        mAdapter.setOnCmmtLikeClickListener(new DetailCommentListHolder.OnCommentLikeClickListener() {
            @Override
            public void onItemClick(View view, Comment comment) {
                cmmtLike(comment.getCmmtid());
                int like = comment.getLike();
                for(int i = 0; i < like; i++) {
                    if(comment.getLikeList().get(i) == PropertyManager.getInstance().getUser().getUid()){
                        comment.getLikeList().remove(i);
                        like--;
                    }
                }
                if(like == comment.getLike()) {
                    List<Integer> myLL = comment.getLikeList();
                    myLL.add(like, PropertyManager.getInstance().getUser().getUid());
                    comment.setLikeList(myLL);
                    like++;
                }
                comment.setLike(like);
                mAdapter.modify_comment(comment);
                Toast.makeText(getContext(), "좋아요 눌림", Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnGuestListClickListener(new DetailGuestsHolder.OnGuestListClickListener(){
            @Override
            public void onItemClick(View view, int weracId) {
                Intent intent = new Intent(getActivity(), GuestListActivity.class);
                intent.putExtra(DetailViewActivity.EXTRA_WERAC_ID, weracId);
                startActivity(intent);
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


    private void addComment(Comment myCmmt) {
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

    private void modifyComment(Comment myCmmt) {
        NetworkManager.getInstance().getWeracModifyComment(getContext(), this_MId, myCmmt, new NetworkManager.OnResultListener<Comment>() {
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

    private void removeComment(Comment myCmmt) {
        NetworkManager.getInstance().getWeracRemoveComment(getContext(), this_MId, myCmmt, new NetworkManager.OnResultListener<Comment>() {
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

    public void cmmtLike(String cmmtId){
        NetworkManager.getInstance().getWeracLikeComment(getContext(), this_MId, cmmtId, new NetworkManager.OnResultListener<Comment>() {
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

    public void applyMC(){
        NetworkManager.getInstance().applyMC(getContext(), this_MId, new NetworkManager.OnResultListener<Result>() {
            @Override
            public void onSuccess(Request request, Result result) {
                if(result.getSuccess() == 1)
                    Toast.makeText(getContext(), "진행자로 지원되었습니다", Toast.LENGTH_SHORT).show();
                else if(result.getSuccess() == 0)
                    Toast.makeText(getContext(), "이미 진행 지원하셨습니다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void join(){
        mAdapter.participate_user(PropertyManager.getInstance().getUser());
        Toast.makeText(getActivity(), "참여되었습니다", Toast.LENGTH_SHORT).show();
    }
}
