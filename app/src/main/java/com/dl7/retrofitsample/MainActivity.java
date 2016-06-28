package com.dl7.retrofitsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dl7.library.OnRequestListener;
import com.dl7.library.RequestCallBack;
import com.dl7.library.user.UserInfo;
import com.dl7.library.user.UserService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContent = (TextView) findViewById(R.id.tv_content);
        Button btnUserInfo = (Button) findViewById(R.id.btn_user_info);
        Button btnUserList = (Button) findViewById(R.id.btn_user_list);

        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestCallBack.mIsList = false;
                UserService.userInfo(10086, new OnRequestListener<UserInfo>() {
                    @Override
                    public void onResponse(UserInfo entity) {
                        Log.i("userInfo", entity.toString());
                        mContent.setText(entity.toString());
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Log.d("userInfo", errorMsg);
                    }
                });
            }
        });

        btnUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestCallBack.mIsList = true;
                UserService.userList("info", new OnRequestListener<List<UserInfo>>() {
                    @Override
                    public void onResponse(List<UserInfo> entity) {
                        mContent.setText(entity.toString());
                        Log.w("userList", entity.toString());
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        Log.e("userList", errorMsg);
                    }
                });
            }
        });

    }
}
