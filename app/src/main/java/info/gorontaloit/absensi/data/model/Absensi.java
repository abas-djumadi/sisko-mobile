package info.gorontaloit.absensi.data.model;

public class Absensi {
    Schedule schedule;
    String status;
    String ket;
    Siswa siswa;

    public Absensi(Schedule schedule, String status, String ket, Siswa siswa) {
        this.schedule = schedule;
        this.status = status;
        this.ket = ket;
        this.siswa = siswa;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getStatus() {
        return status;
    }

    public String getKet() {
        return ket;
    }

    public Siswa getSiswa() {
        return siswa;
    }
}
