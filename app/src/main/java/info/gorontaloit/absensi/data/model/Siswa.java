package info.gorontaloit.absensi.data.model;

public class Siswa {
    Integer id;
    String nama;
    String nis;

    public Siswa(Integer id, String nama, String nis) {
        this.id = id;
        this.nama = nama;
        this.nis = nis;
    }

    public Integer getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNis() {
        return nis;
    }
}
