package kr.werac.yeah.werac_create;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import kr.werac.yeah.MyApplication;
import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;
import kr.werac.yeah.manager.NetworkManager;
import okhttp3.Request;

public class CreateWeracFragment extends Fragment {

    RecyclerView listView;
    CreateWeracAdapter mAdapter;
    GridLayoutManager mLayoutManager;
    RecyclerView.ViewHolder holder;
    File mUploadFile;
    WeracItem werac;
    int hour_x, min_x, Year_x, Month_x, Day_x;

    public static CreateWeracFragment newInstance() {
        CreateWeracFragment fragment = new CreateWeracFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUploadFile = null;
        mAdapter = new CreateWeracAdapter();
        RecyclerView.ViewHolder holder;
        werac = new WeracItem();

//        if (savedInstanceState != null) {
//            String path = savedInstanceState.getString("uploadFile");
//            if (!TextUtils.isEmpty(path)) {
//                mUploadFile = new File(path);
//                BitmapFactory.Options opts = new BitmapFactory.Options();
//                opts.inSampleSize = 2;
//                Bitmap bm = BitmapFactory.decodeFile(path, opts);
//            }
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_werac, container, false);
        listView = (RecyclerView)view.findViewById(R.id.rv_list_create);
        listView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        listView.setLayoutManager(mLayoutManager);
        mAdapter.setOnItemClickListener(new CreateImageHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View view, WeracItem werac) {
//                private void getImageFromGallery() {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/jpeg");
                    startActivityForResult(intent, 1);
//                }
            }
        });
//        mAdapter.OnDateSetListener(new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Year_x = year;
//                Month_x = monthOfYear;
//                Day_x = dayOfMonth;
//            }
//        }, Year_x, Month_x, Day_x));

        return view;
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if (mUploadFile != null) {
//            outState.putString("uploadfile", mUploadFile.getAbsolutePath());
//        }
//    }

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
                opts.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(path, opts);
                mAdapter.addImage(bm);
            }
        }
        return;
    }
    public void sendWerac() {
        werac = mAdapter.getWerac();
        NetworkManager.getInstance().getWeracCreate(getContext(), mUploadFile, werac, new NetworkManager.OnResultListener<WeracItem>() {
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
