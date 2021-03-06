package com.info.hunar.activity.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.info.hunar.R;
import com.info.hunar.activity.Home_Activity;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.model_pojo.ForgotModel;
import com.info.hunar.model_pojo.registration_model.RegistrationModel;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ForgotPassword_Activity extends AppCompatActivity {

    EditText et_email;
    TextView tv_next_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_);

        et_email = findViewById(R.id.et_email);
        tv_next_reset = findViewById(R.id.tv_next_reset);

        tv_next_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!et_email.getText().toString().isEmpty()) {
                    if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
                        Toast.makeText(ForgotPassword_Activity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Conectivity.isConnected(ForgotPassword_Activity.this)) {
                            ForgotPwApi(et_email.getText().toString());
                        } else {
                            Toast.makeText(ForgotPassword_Activity.this, "Please check internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(ForgotPassword_Activity.this, "Please enter email id", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void ForgotPwApi(String email) {
        final ProgressDialog progressDialog = new ProgressDialog(ForgotPassword_Activity.this, R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.ForgotUser(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ForgotModel>() {
                    @Override
                    public void onNext(ForgotModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_test", "" + response.getResponce());

                            if (response.getResponce() == true) {

                                Toast.makeText(ForgotPassword_Activity.this, "" + response.getMsg(), Toast.LENGTH_SHORT).show();

                                onBackPressed();
                            } else {

                                Toast.makeText(ForgotPassword_Activity.this, "" + response.getError_msg(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            progressDialog.dismiss();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                        progressDialog.dismiss();
                        Log.e("mr_product_error", e.toString());
                        // Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
