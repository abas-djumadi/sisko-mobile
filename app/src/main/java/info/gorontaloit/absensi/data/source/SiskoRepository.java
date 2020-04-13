package info.gorontaloit.absensi.data.source;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import info.gorontaloit.absensi.data.model.Jurusan;
import info.gorontaloit.absensi.data.model.Kelas;
import info.gorontaloit.absensi.data.model.Mapel;
import info.gorontaloit.absensi.data.model.Schedule;
import info.gorontaloit.absensi.data.model.Siswa;
import info.gorontaloit.absensi.data.model.TahunAjaran;
import info.gorontaloit.absensi.data.model.User;
import info.gorontaloit.absensi.data.source.remote.RemoteRepository;
import info.gorontaloit.absensi.data.source.remote.response.GetSchedule;
import info.gorontaloit.absensi.data.source.remote.response.GetStudent;
import info.gorontaloit.absensi.data.source.remote.response.JurusanResponse;
import info.gorontaloit.absensi.data.source.remote.response.KelasResponse;
import info.gorontaloit.absensi.data.source.remote.response.MapelResponse;
import info.gorontaloit.absensi.data.source.remote.response.PostLogin;
import info.gorontaloit.absensi.data.source.remote.response.ScheduleResponse;
import info.gorontaloit.absensi.data.source.remote.response.SiswaResponse;
import info.gorontaloit.absensi.data.source.remote.response.TahunAjaranResponse;
import info.gorontaloit.absensi.data.source.remote.response.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiskoRepository implements SiskoDataSource {

    private static SiskoRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;
    private  final Application application;
    private MutableLiveData<List<Schedule>> scheduleResults = new MutableLiveData<>();
    private MutableLiveData<User> userResult = new MutableLiveData<>();
    private MutableLiveData<List<Siswa>> siswaResults = new MutableLiveData<>();

    public SiskoRepository(RemoteRepository remoteRepository, Application application) {
        this.remoteRepository = remoteRepository;
        this.application = application;
    }

    public static SiskoRepository getInstance(RemoteRepository rr, Application app) {
        if (INSTANCE == null) {
            synchronized (SiskoRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SiskoRepository(rr, app);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<User> login(@NotNull String email, @NotNull String password) {
        postLogin(email,password);
        return userResult;
    }

    @Override
    public LiveData<List<Schedule>> getAllSchedule() {
        getSchedules();
        return scheduleResults;
    }

    @Override
    public LiveData<List<Siswa>> getStudentsByJurusanAndKelas(Integer jurusanId, Integer kelasId) {
        callStudentsByJurusanAndKelas(jurusanId,kelasId);
        return siswaResults;
    }

    private void callStudentsByJurusanAndKelas(Integer jurusanId, Integer kelasId) {
        remoteRepository.getStudentsBySchedule(new Callback<GetStudent>() {
            @Override
            public void onResponse(Call<GetStudent> call, Response<GetStudent> response) {
                if (response.isSuccessful()) {
                    List<SiswaResponse> siswaResponses = null;
                    List<Siswa> siswaList = new ArrayList<>();
                    if (response.body() != null) {
                        siswaResponses = response.body().getSiswaResponses();
                    }

                    if (siswaResponses != null) {
                        for (SiswaResponse siswaResponse : siswaResponses) {
                            Siswa siswa = new Siswa(siswaResponse.getId(), siswaResponse.getNama(), siswaResponse.getNis());
                            Log.d("siswa nama", siswa.getNama());
                            siswaList.add(siswa);
                        }
                        siswaResults.postValue(siswaList);
                    }
                    siswaResults.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetStudent> call, Throwable t) {
                Log.d("Gagal ambil data", String.valueOf(t.getMessage()));
            }
        },jurusanId,kelasId);
    }

    private void getSchedules() {
        remoteRepository.getAllSchedule(new Callback<GetSchedule>() {
            @Override
            public void onResponse(Call<GetSchedule> call, Response<GetSchedule> response) {
                if (response.isSuccessful()) {

                    List<ScheduleResponse> scheduleResponses = null;
                    List<Schedule> schedules = new ArrayList<>();
                    if (response.body() != null) {
                        scheduleResponses = response.body().getSrp().getScheduleResponseList();
                    }

                    if (scheduleResponses != null) {
                        for (ScheduleResponse scheduleResponse : scheduleResponses) {
                            Schedule schedule = new Schedule();
                            schedule.setKode(scheduleResponse.getKode());
                            schedule.setHariTanggal(scheduleResponse.getHariTanggal());
                            schedule.setMulai(scheduleResponse.getMulai());
                            schedule.setSelesai(scheduleResponse.getSelesai());

                            MapelResponse mr = scheduleResponse.getMapelResponse();
                            Mapel mapel = new Mapel(mr.getId(), mr.getNama());
                            schedule.setMapel(mapel);

                            JurusanResponse jr = scheduleResponse.getJurusanResponse();
                            Jurusan jurusan = new Jurusan(jr.getId(), jr.getNama());
                            schedule.setJurusan(jurusan);

                            KelasResponse kr = scheduleResponse.getKelasResponse();
                            Kelas kelas = new Kelas(kr.getId(), kr.getNama());
                            schedule.setKelas(kelas);

                            TahunAjaranResponse tar = scheduleResponse.getTahunAjaranResponse();
                            TahunAjaran ta = new TahunAjaran(tar.getId(), tar.getTahun(), tar.getNama(), tar.getStatus());
                            schedule.setTahunAjaran(ta);

                            schedules.add(schedule);
                        }
                        scheduleResults.postValue(schedules);
                    } else {
                        scheduleResults.postValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetSchedule> call, Throwable t) {
                Log.d("Gagal ambil data", String.valueOf(t.getMessage()));
            }
        });
    }

    private void postLogin(String email,String password){
        remoteRepository.login(new Callback<PostLogin>() {
            @Override
            public void onResponse(Call<PostLogin> call, Response<PostLogin> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body().getUser();
                    User user = new User(
                            userResponse.getUsername(),
                            userResponse.getNip(),
                            userResponse.getToken()
                    );
                    userResult.postValue(user);
                } else {
                    userResult.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PostLogin> call, Throwable t) {
                Log.d("Gagal login karena ", t.getMessage().toString());
            }
        },email,password);
    }
}
