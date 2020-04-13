package info.gorontaloit.absensi.ui.absen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import info.gorontaloit.absensi.data.model.Siswa;
import info.gorontaloit.absensi.data.source.SiskoRepository;

public class AbsenViewModel extends ViewModel {
    private SiskoRepository sr;

    public AbsenViewModel(SiskoRepository sr) {
        this.sr = sr;
    }

    public LiveData<List<Siswa>> getSiswaByJurusanAndKelas(Integer jurusanId, Integer kelasId) {
        return sr.getStudentsByJurusanAndKelas(jurusanId, kelasId);
    }
}
