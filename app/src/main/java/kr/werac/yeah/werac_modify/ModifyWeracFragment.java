package kr.werac.yeah.werac_modify;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import kr.werac.yeah.R;
import kr.werac.yeah.data.Comment;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.werac_create.CreateDetailHolder;
import kr.werac.yeah.werac_create.CreateWeracAdapter;
import kr.werac.yeah.werac_detail.DetailViewActivity;
import okhttp3.Request;

public class ModifyWeracFragment extends Fragment {

    RecyclerView listView;
    ModifyWeracAdapter mAdapter;
    static int this_MId;
    GridLayoutManager mLayoutManager;
    int hour_x, min_x, Year_x, Month_x, Day_x;
    WeracItem werac;

    public static ModifyWeracFragment newInstance(int thisMId) {
        ModifyWeracFragment fragment = new ModifyWeracFragment();
        this_MId = thisMId;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ModifyWeracAdapter();
        final Calendar myCal = Calendar.getInstance();
        werac = new WeracItem();
        Year_x = myCal.get(Calendar.YEAR);
        Month_x = myCal.get(Calendar.MONTH);
        Day_x = myCal.get(Calendar.DAY_OF_MONTH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_werac, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list_create);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        listView.setLayoutManager(mLayoutManager);

        mAdapter.setOnDateClickListener(new ModifyDetailHolder.OnDateClickListener() {
            @Override
            public void onItemClick(View view) {
                DatePickerDialog myDPD = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mAdapter.h_detail.setDate(year, monthOfYear, dayOfMonth);
                    }
                },  Year_x, Month_x, Day_x);
                myDPD.show();
            }
        });

        mAdapter.setOnTimeClickListener(new ModifyDetailHolder.OnTimeClickListener() {
            @Override
            public void onItemClick(View view, final int SorE) {
                TimePickerDialog myTPD = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mAdapter.h_detail.setTime(SorE, hourOfDay, minute);
                    }
                }, hour_x, min_x, false);
                myTPD.show();
            }
        });

        mAdapter.setOnSchClickListener(new ModifyDetailHolder.OnSchClickListener() {
            @Override
            public void onItemClick(final View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View AlertView = inflater.inflate(R.layout.fragment_schedule_add_dialog, null);
                alert.setView(AlertView);
                final EditText et_sch = (EditText) AlertView.findViewById(R.id.et_sch);
//              new AlertDialog.Builder(mContext, R.style.MyCustomDialogTheme);

//                alert.setMessage("Enter Your Message");
//                alert.setTitle("Enter Your Title");

                alert.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
//                        Editable YouEditTextValue = edittext.getText();
                        //OR
                        mAdapter.addSch(et_sch.getText().toString());
                    }
                });

                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                        dialog.cancel();
                    }
                });

                alert.show();
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

//        for (int i = 0 ; i < 16 ; i++) {
//            WeracItem werac = new WeracItem();
//            werac.setMid(0);
//            werac.setPicturePath(R.drawable.p3);
//            werac.setTitle("[위락 모임]꽃놀이 후 피크닉!");
//            werac.setTitle_sub("한강 둔치에서 배달음식 먹고 이야기 나누고 싶은 분들 함께해요~");
//            ArrayList<String> sch = new ArrayList<>();
//            sch.add(0, "자전거 대여해서 타기");
//            sch.add(1, "잔디밭에 앉아서 배달음식 시켜먹기");
//            werac.setSchedule(sch);
//            werac.setLocation_detail("관악구 봉천동 1632-3");
//            werac.setLocation_area("서울시");
//            werac.setDate("5월 23일");
//            werac.setStart_time("12:30");
//            werac.setEnd_time("17:30");
//            werac.setFee(5000);
//            werac.setHas_mc(false);
//            werac.setMc_id(123);
//            werac.setUid(456);
//            werac.setLimit_num(20);
//            werac.setJoin_num(16);
//            ArrayList<Integer> gi = new ArrayList<>();
//            gi.add(0, 7);
//            gi.add(1, 8);
//            gi.add(2, 9);
//            gi.add(3, 10);
//            gi.add(4, 17);
//            gi.add(5, 81);
//            gi.add(6, 91);
//            gi.add(7, 101);
//            gi.add(8, 72);
//            gi.add(9, 18);
//            gi.add(10, 239);
//            gi.add(11, 101);
//            gi.add(12, 72);
//            gi.add(13, 28);
//            gi.add(14, 92);
//            gi.add(15, 110);
//            werac.setGuests_id(gi);
//            ArrayList<Comment> cmts = new ArrayList<>();
//            Comment cmt1 = new Comment();
//            cmt1.setUid(7);
//            cmt1.setContent("헐진짜재미겠다 ㅠㅠ");
//            Comment cmt2 = new Comment();
//            cmt2.setUid(8);
//            cmt2.setContent("꿀잼예약요 기대함");
//            cmts.add(0, cmt1);
//            cmts.add(1, cmt2);
//            werac.setComments(cmts);
//            mAdapter.setWerac(werac);
//        }


       }

    private void modifyWerac() {
        werac = mAdapter.getWerac();
        NetworkManager.getInstance().getWeracModify(getContext(), werac, new NetworkManager.OnResultListener<WeracItem>() {
            @Override
            public void onSuccess(Request request, WeracItem result) {
                Toast.makeText(getContext(), "Mid (" + result.getMid() + ")가 생성되었움", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
