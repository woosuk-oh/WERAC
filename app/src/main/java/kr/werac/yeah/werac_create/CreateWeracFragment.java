package kr.werac.yeah.werac_create;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import kr.werac.yeah.R;
import kr.werac.yeah.data.WeracItem;

public class CreateWeracFragment extends Fragment {

    RecyclerView listView;
    CreateWeracAdapter mAdapter;
    public static final String EXTRA_WERAC_ID = "WeracId";
    String weracId;
    GridLayoutManager mLayoutManager;


    public static CreateWeracFragment newInstance() {
        CreateWeracFragment fragment = new CreateWeracFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new CreateWeracAdapter();
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

        return view;
    }

    File mUploadFile = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            Uri uri = data.getData();
//            String[] projection = {MediaStore.Images.Media.DATA};
//            Cursor c = getActivity().getContentResolver().query(uri, projection, null, null, null);
//            if (c.moveToNext()) {
//                String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
//                mUploadFile = new File(path);
//                BitmapFactory.Options opts = new BitmapFactory.Options();
//                opts.inSampleSize = 2;
//                Bitmap bm = BitmapFactory.decodeFile(path, opts);
//                CreateImageHolder.setImage(bm);
//            }
//        }
        return;
    }
}
