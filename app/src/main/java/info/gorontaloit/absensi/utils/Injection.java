package info.gorontaloit.absensi.utils;

import android.app.Application;

import info.gorontaloit.absensi.data.source.SiskoRepository;
import info.gorontaloit.absensi.data.source.remote.Api;
import info.gorontaloit.absensi.data.source.remote.RemoteRepository;

public class Injection {
    public static SiskoRepository provideRepository(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(application);

        return SiskoRepository.getInstance(remoteRepository,application);
    }
}
