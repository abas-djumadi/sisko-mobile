package info.gorontaloit.absensi.data.source.remote;

import android.app.Application;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import info.gorontaloit.absensi.data.model.Jurusan;
import info.gorontaloit.absensi.data.model.Kelas;
import info.gorontaloit.absensi.data.source.remote.response.GetSchedule;
import info.gorontaloit.absensi.data.source.remote.response.GetStudent;
import info.gorontaloit.absensi.data.source.remote.response.PostLogin;
import info.gorontaloit.absensi.data.source.remote.response.UserResponse;
import info.gorontaloit.absensi.utils.RetrofitClient;
import info.gorontaloit.absensi.utils.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RemoteRepository {

    private static RemoteRepository INSATANCE;
    private Api mApi;
    private Application application;

    public RemoteRepository(Application application) {
        mApi = RetrofitClient.getClient().create(Api.class);
        this.application = application;
    }

    public static RemoteRepository getInstance(Application application) {
        if (INSATANCE == null) {
            synchronized (RemoteRepository.class) {
                if (INSATANCE == null) {
                    INSATANCE = new RemoteRepository(application);
                }
            }
        }
        return INSATANCE;
    }

    public void login(Callback<PostLogin> callback,@NotNull String email,@NotNull String password) {
        Call<PostLogin> loginCall = mApi.login(email, password);
        loginCall.enqueue(callback);
    }

    public void getAllSchedule(Callback<GetSchedule> callback) {
        mApi = RetrofitClient.getRetrofitInstance(application,null).create(Api.class);
        Call<GetSchedule> scheduleCall = mApi.getSchedule();
        scheduleCall.enqueue(callback);
    }

    public void getStudentsBySchedule(Callback<GetStudent> callback, Integer jurusanId, Integer kelasId) {
        mApi = RetrofitClient.getRetrofitInstance(application, "siswa/"+jurusanId + "/" + kelasId+"/").create(Api.class);
        Call<GetStudent> studentCall = mApi.getStudentBySchedule();
        studentCall.enqueue(callback);
    }
}
