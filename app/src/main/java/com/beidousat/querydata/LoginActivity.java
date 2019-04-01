package com.beidousat.querydata;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beidousat.querydata.activity.RechargeActivity;
import com.beidousat.querydata.base.BaseActivity;
import com.beidousat.querydata.buss.LoginConstract;
import com.beidousat.querydata.buss.LoginPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.model.LoginInfo;
import com.beidousat.querydata.utils.L;
import com.beidousat.querydata.utils.PreferenceUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginConstract.View{
    private EditText userName,passWord;
    private TextView login;
    private LoginPresenter loginPresenter;
    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
           userName=findViewById(R.id.username);
           passWord=findViewById(R.id.passwd);
           login=findViewById(R.id.login);
           userName.setText(PreferenceUtil.getString(this,"userName",""));
           passWord.setText(PreferenceUtil.getString(this,"passWord",""));
           if(!TextUtils.isEmpty(userName.getText().toString().trim())&&!TextUtils.isEmpty(passWord.getText().toString().trim())){
               login();
           }
    }

    @Override
    public void setListener() {
        login.setOnClickListener(this);
    }

    @Override
    public void initData() {
       loginPresenter=new LoginPresenter(this);
    }

    @Override
    public void loadDataWhenOnResume() {

    }

    @Override
    public void cancelLoadingRequest() {

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.login:
           if(TextUtils.isEmpty(userName.getText().toString().trim())){
               Toast.makeText(LoginActivity.this,getText(R.string.userNameNull),Toast.LENGTH_SHORT).show();
           }else if(TextUtils.isEmpty(passWord.getText().toString().trim())){
               Toast.makeText(LoginActivity.this,getText(R.string.pwdNull),Toast.LENGTH_SHORT).show();
           }else{
               login();
           }
           break;
       }
    }

    private void login() {
        Map<String, String> requestParams=new HashMap<>();
        requestParams.put("arg0",userName.getText().toString().trim());
        requestParams.put("arg1",getMD5String(passWord.getText().toString().trim()));
        requestParams.put("arg2", Constant.Key);
        loginPresenter.login(requestParams);
    }

    private String getBase64String(String oldWord){
        try {
            String encodeWord = Base64.encodeToString(oldWord.getBytes("utf-8"), Base64.NO_WRAP);
            L.test(" encode wrods = " + encodeWord);
            return encodeWord;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return oldWord;
    }

    @Override
    public void OnRequestData(LoginInfo loginInfo) {
                 if(loginInfo!=null&&loginInfo.getRoot()!=null&&loginInfo.getRoot().getData()!=null){
                     if("1".equals(loginInfo.getRoot().getData().get(0).getIdresult())){
                         Toast.makeText(LoginActivity.this,getText(R.string.loginSucced),Toast.LENGTH_SHORT).show();
                         PreferenceUtil.setString(this,"userName",userName.getText().toString().trim());
                         PreferenceUtil.setString(this,"passWord",passWord.getText().toString().trim());
                         startActivity(new Intent(this, MainActivity.class));
                     }else{
                         Toast.makeText(LoginActivity.this,getText(R.string.loginFail),Toast.LENGTH_SHORT).show();
                     }
                 }
    }

    @Override
    public void showLoading(String msg) {
        showNormalDialog(msg);
    }

    @Override
    public void hideLoading() {
        closeNormalDialog();
    }

    @Override
    public void onFeedBack(boolean success, String key, Object data) {
        if(!success){
            Toast.makeText(LoginActivity.this,getText(R.string.loginFail),Toast.LENGTH_SHORT).show();
        }

    }
    private String getMD5String(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
