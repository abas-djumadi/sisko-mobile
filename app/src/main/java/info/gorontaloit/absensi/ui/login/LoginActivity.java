package info.gorontaloit.absensi.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import info.gorontaloit.absensi.ui.home.HomeActivity;
import info.gorontaloit.absensi.R;
import info.gorontaloit.absensi.utils.SharedPreference;
import info.gorontaloit.absensi.viewmodel.ViewModelFactory;

import static info.gorontaloit.absensi.utils.SharedPreference.NIP;
import static info.gorontaloit.absensi.utils.SharedPreference.TOKEN;
import static info.gorontaloit.absensi.utils.SharedPreference.USERNAME;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel lvm;
    TextInputLayout inputLayoutEmail,inputLayoutPassword;
    TextInputEditText emailET,passwordET;
    SharedPreference sp;
    String email;
    String password;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = new SharedPreference(this);
        initializeWidgets();
        loading.setVisibility(View.INVISIBLE);

        Button button = findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });
    }

    @NonNull
    private static LoginViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(LoginViewModel.class);
    }

    private void requestLogin() {
        showLoading(true);
        boolean isValid = true;
        if (emailET.getText().toString().isEmpty()) {
            inputLayoutEmail.setError("Email is required");
            isValid = false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        if (passwordET.getText().toString().isEmpty()) {
            inputLayoutPassword.setError("Password is required");
            isValid = false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        lvm = obtainViewModel(this);
        lvm.login(emailET.getText().toString(), passwordET.getText().toString()).observe(this, user -> {
            if (user != null) {
                sp.saveString(USERNAME, user.getUsername());
                sp.saveString(NIP, user.getNip());
                sp.saveString(TOKEN, user.getToken());
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(this,"Login Berhasil",Toast.LENGTH_SHORT).show();
                showLoading(false);
            }else{
                Toast.makeText(this,"Login Gagal",Toast.LENGTH_SHORT).show();
                showLoading(false);
            }
        });
    }

    private void initializeWidgets() {
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.inputLayoutEmail);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.inputLayoutPassword);

        emailET = (TextInputEditText) findViewById(R.id.etEmail);
        passwordET = (TextInputEditText) findViewById(R.id.etPassword);

        loading = (ProgressBar) findViewById(R.id.loading);

    }

    private void showLoading(boolean status) {
        if (status) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }

    private void disableWidgets() {

    }

}
