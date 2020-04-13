package info.gorontaloit.absensi.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import info.gorontaloit.absensi.data.model.User;
import info.gorontaloit.absensi.data.source.SiskoRepository;

public class LoginViewModel extends ViewModel {
    private SiskoRepository sr;

    public LoginViewModel(SiskoRepository sr) {
        this.sr = sr;
    }

    public LiveData<User> login(@NotNull String email, @NotNull String password) {
        return sr.login(email, password);
    }
}
