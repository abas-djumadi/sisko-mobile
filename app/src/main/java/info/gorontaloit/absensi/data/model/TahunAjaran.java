package info.gorontaloit.absensi.data.model;

public class TahunAjaran {
    private Integer id;
    private Integer tahun;
    private String nama;
    private Integer status;

    public TahunAjaran(Integer id, Integer tahun, String nama, Integer status) {
        this.id = id;
        this.tahun = tahun;
        this.nama = nama;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTahun() {
        return tahun;
    }

    public String getNama() {
        return nama;
    }

    public Integer getStatus() {
        return status;
    }
}
