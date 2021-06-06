package com.example.capstonedesign;

import static com.example.capstonedesign.user.UserInfo.userName;
import static com.example.capstonedesign.user.UserInfo.userId;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    RetrofitClient rc = new RetrofitClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText un = (EditText)findViewById(R.id.edit_UserName);
        EditText userPw = (EditText)findViewById(R.id.edit_UserPwd);

        String uName = String.valueOf(un.getText());

        Button btn = (Button)findViewById(R.id.btn_SignIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = uName;
                Call<String> call = rc.mainFlowService.userLogin(userName);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            System.out.println("=====================================");
                            System.out.println(response.body());
                            userId = Integer.parseInt(response.body());
                            System.out.println("=====================================");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });
    }
}