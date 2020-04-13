package info.gorontaloit.absensi.data.source;

import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import info.gorontaloit.absensi.data.model.Jurusan;
import info.gorontaloit.absensi.data.model.Kelas;
import info.gorontaloit.absensi.data.model.Schedule;
import info.gorontaloit.absensi.data.model.Siswa;
import info.gorontaloit.absensi.data.model.User;

public interface SiskoDataSource {

    LiveData<User> login(@NotNull String email, @NotNull String password);
    LiveData<List<Schedule>> getAllSchedule();

    LiveData<List<Siswa>> getStudentsByJurusanAndKelas(Integer jurusanId, Integer kelasId);

}
