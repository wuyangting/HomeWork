package com.example.talk.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talk.R;
import com.example.talk.adapter.AddFriendsAdapter;

import java.util.ArrayList;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.tasks.GetEventNotificationTaskMng;
import cn.jpush.im.api.BasicCallback;

public class AddFriendsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNameSearch;
    private Button mAdd;
    private RecyclerView mRec;
    private AddFriendsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        initView();
        //注册
        JMessageClient.registerEventReceiver(this);
    }

    /**
     * 注销
     */
    @Override
    protected void onDestroy() {
        JMessageClient.unRegisterEventReceiver(this);
        super.onDestroy();
    }

    /**
     * 接收事件
     * @param event
     *
     */
    public void onEvent(ContactNotifyEvent event) {
        Toast.makeText(this, "1231331233", Toast.LENGTH_SHORT).show();
        String reason = event.getReason();
        String fromUsername = event.getFromUsername();
        String appkey = event.getfromUserAppKey();

        switch (event.getType()) {
            case invite_received://收到好友邀请
                ArrayList<String> strings = new ArrayList<>();
                strings.add(reason);
                adapter.addList(strings);
                //...
                break;
            case invite_accepted://对方接收了你的好友邀请
                //...
                break;
            case invite_declined://对方拒绝了你的好友邀请
                //...
                break;
            case contact_deleted://对方将你从好友中删除
                //...
                break;
            default:
                break;
        }
    }
    private void initView() {
        mNameSearch = (EditText) findViewById(R.id.search_name);
        mAdd = (Button) findViewById(R.id.add);
        mAdd.setOnClickListener(this);
        mRec = (RecyclerView) findViewById(R.id.rec);
        mRec.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddFriendsAdapter(this);
        adapter.setOnClick(new AddFriendsAdapter.onClick() {
            @Override
            public void isAdd(int posi, String name, boolean isAddOrNo) {
                agreeAdd(name,isAddOrNo);
                adapter.delete(posi);
            }


        });
    }

    private void agreeAdd(String userName, boolean isAddOrNo) {
        if(isAddOrNo){
            ContactManager.acceptInvitation(userName, null, new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    if (0 == responseCode) {
                        //接收好友请求成功
                        Toast.makeText(AddFriendsActivity.this, "接收好友请求成功", Toast.LENGTH_SHORT).show();
                    } else {
                        //接收好友请求失败
                        Toast.makeText(AddFriendsActivity.this, "接收好友请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            ContactManager.declineInvitation(userName, null, "sorry~", new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    if (0 == responseCode) {
                        //拒绝好友请求成功
                        Toast.makeText(AddFriendsActivity.this, "拒绝好友请求成功", Toast.LENGTH_SHORT).show();
                    } else {
                        //拒绝好友请求失败
                        Toast.makeText(AddFriendsActivity.this, "拒绝好友请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                // TODO 20/06/27
                add();
                break;
            default:
                break;
        }
    }

    private void add() {
        String s = mNameSearch.getText().toString();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
        } else {
            ContactManager.sendInvitationRequest(s, null, "hello", new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {
                    if (0 == responseCode) {
                        //好友请求请求发送成功
                        Toast.makeText(AddFriendsActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                    } else {
                        //好友请求发送失败
                        Toast.makeText(AddFriendsActivity.this, "申请失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}