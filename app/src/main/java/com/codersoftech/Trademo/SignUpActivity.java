package com.codersoftech.Trademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codersoftech.Trademo.ModelResponse.RegisterResponse;
import com.codersoftech.Trademo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String Name,Phone,Email,Pan,Aadhar,Demat,Wallet;

    EditText name,email,phone;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!name.getText().toString().matches("^[A-Za-z]+$")){
                    name.setError("Enter valid name");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!isEmail( email )){
                    email.setError("Enter valid Email Address");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Name=name.getText().toString().trim();
                Phone=phone.getText().toString().trim();
                Email=email.getText().toString().trim();
                Pan="4567";
                Aadhar="98689";
                Demat="Dematno1";
                Wallet="10000";

                if (Name.isEmpty()){
                    name.setError("Enter valid name");
                    name.requestFocus();
                }else if(!name.getText().toString().matches("^[A-Za-z]+$")){
                    name.setError("Enter valid name");
                    name.requestFocus();
                }else if (Phone.isEmpty()) {
                    phone.setError("Please enter 10 digit valid Phone Number");
                    phone.requestFocus();
                } else if (Phone.length() < 10) {
                    phone.setError("Please enter 10 digit valid Phone Number");
                    phone.requestFocus();
                }else if (Email.isEmpty()) {
                    email.setError("Enter valid Email Address");
                    email.requestFocus();
                }else if (!isEmail( email )){
                    email.setError("Enter valid Email Address");
                    email.requestFocus();
                }else {
                    register();
                }

            }
    });
    }

    private void initView() {
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        button=findViewById(R.id.Register);
    }

    public  String genrate(){
        String Demat="TR"+"CS";

        return Demat;
    }

    public void register(){
        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().register(Name,Phone,Email,Pan,Aadhar,Demat,Wallet);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Registration "+registerResponse.getMessage(),Toast.LENGTH_LONG).show();

                    SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("login",true);
                    editor.putString("name",name.getText().toString());
                    editor.putString("email",email.getText().toString());
                    editor.putString("phone",phone.getText().toString());
                    editor.putString("Demat","IT HAS TO CHANGE");
                    editor.putInt("wallet",10000);
                    editor.apply();
                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(SignUpActivity.this,"Error "+registerResponse.getMessage(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this,"Something Went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }

    boolean isEmail(EditText email)
    {
        CharSequence input = email.getText().toString();
        return(!TextUtils.isEmpty(input)&& Patterns.EMAIL_ADDRESS.matcher(input).matches());
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

}