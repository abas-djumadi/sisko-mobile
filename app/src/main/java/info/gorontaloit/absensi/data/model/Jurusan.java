package info.gorontaloit.absensi.data.model;

public class Jurusan {
    private Integer id;
    private String nama;

    public Jurusan(Integer id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public Integer getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }
}
