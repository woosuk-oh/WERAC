package kr.werac.yeah.werac_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kr.werac.yeah.R;
import kr.werac.yeah.main.MainActivity;

/**
 * Created by Tacademy on 2016-05-20.
 */
public class DetailCloseWeracDialog extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(STYLE_NO_TITLE, R.style.MyDIalogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_status_change, container, false);

        TextView tv = (TextView)view.findViewById(R.id.tv_dialog_title);
        tv.setText("모임이 마감되었습니다");
        tv = (TextView)view.findViewById(R.id.tv_dialog_subtitle);
        tv.setText("    참여자들과 함께\n모임을 즐겨보세요");

        Button btn = (Button)view.findViewById(R.id.btn_dialog_status_change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(myIntent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }
}
