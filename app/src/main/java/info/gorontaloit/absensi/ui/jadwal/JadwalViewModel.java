package info.gorontaloit.absensi.ui.jadwal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import info.gorontaloit.absensi.data.model.Schedule;
import info.gorontaloit.absensi.data.source.SiskoRepository;

public class JadwalViewModel extends ViewModel {
    private SiskoRepository sr;

    public JadwalViewModel(SiskoRepository sr) {
        this.sr = sr;
    }

    public LiveData<List<Schedule>> getSchedules() {
        return sr.getAllSchedule();
    }
}
