package info.gorontaloit.absensi.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import info.gorontaloit.absensi.data.source.SiskoRepository;
import info.gorontaloit.absensi.ui.absen.AbsenViewModel;
import info.gorontaloit.absensi.ui.jadwal.JadwalViewModel;
import info.gorontaloit.absensi.ui.login.LoginViewModel;
import info.gorontaloit.absensi.utils.Injection;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final SiskoRepository sr;

    private ViewModelFactory(SiskoRepository siskoRepository) {
        sr = siskoRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(sr);
        } else if (modelClass.isAssignableFrom(JadwalViewModel.class)) {
            return (T) new JadwalViewModel(sr);
        } else if (modelClass.isAssignableFrom(AbsenViewModel.class)) {
            return (T) new AbsenViewModel(sr);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
