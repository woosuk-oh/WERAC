package kr.werac.yeah.werac_modify;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import kr.werac.yeah.werac_create.CreateImageHolder;
import kr.werac.yeah.werac_create.CreateScheduleHolder;
import okhttp3.Request;

public class ModifyWeracFragment extends Fragment {

    RecyclerView listView;
    ModifyWeracAdapter mAdapter;
    static int this_MId;
    GridLayoutManager mLayoutManager;
    File mUploadFile;
    int hour_x, min_x, Year_x, Month_x, Day_x;
    WeracItem werac;
    int ChangImageOrNot;

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
        ChangImageOrNot = 1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_werac, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list_create);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        listView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(new ModifyImageHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, WeracItem werac) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/jpeg");
                startActivityForResult(intent, 1);
            }
        });

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

        mAdapter.setOnSchDelClickListener(new ModifyScheduleHolder.OnSchDelClickListener() {
            @Override
            public void onItemClick(View view, TextView et_sch) {
                mAdapter.removeSch(et_sch.getText().toString());
            }
        });


        setData();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(uri, projection, null, null, null);
            if (c.moveToNext()) {
                String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                mUploadFile = new File(path);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 10;
                Bitmap bm = BitmapFactory.decodeFile(path, opts);
                mAdapter.addImage(bm);
                ChangImageOrNot = 2;
            }
        }
        return;
    }

    private void setData() {
        werac = mAdapter.getWeracWhole();
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

    public void modifyWerac() {
        werac = mAdapter.getWeracBy();
        NetworkManager.getInstance().getWeracModify(getContext(), this_MId, ChangImageOrNot, mUploadFile, werac, new NetworkManager.OnResultListener<WeracItem>() {
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
