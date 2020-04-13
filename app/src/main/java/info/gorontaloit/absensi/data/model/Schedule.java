package info.gorontaloit.absensi.data.model;

public class Schedule {
    private Integer kode;
    private String hariTanggal;
    private String mulai;
    private String selesai;
    private Mapel mapel;
    private Jurusan jurusan;
    private Kelas kelas;
    private TahunAjaran tahunAjaran;

    public Integer getKode() {
        return kode;
    }

    public void setKode(Integer kode) {
        this.kode = kode;
    }

    public String getHariTanggal() {
        return hariTanggal;
    }

    public void setHariTanggal(String hariTanggal) {
        this.hariTanggal = hariTanggal;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getSelesai() {
        return selesai;
    }

    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    public Mapel getMapel() {
        return mapel;
    }

    public void setMapel(Mapel mapel) {
        this.mapel = mapel;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }

    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }

    public TahunAjaran getTahunAjaran() {
        return tahunAjaran;
    }

    public void setTahunAjaran(TahunAjaran tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }
}
