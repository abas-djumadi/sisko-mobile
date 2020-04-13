package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import info.gorontaloit.absensi.data.model.Jurusan;
import info.gorontaloit.absensi.data.model.Kelas;
import info.gorontaloit.absensi.data.model.Mapel;
import info.gorontaloit.absensi.data.model.TahunAjaran;

public class ScheduleResponse {
    @SerializedName("kode")
    private Integer kode;

    @SerializedName("hari_tanggal")
    private String hariTanggal;

    @SerializedName("mulai")
    private String mulai;

    @SerializedName("selesai")
    private String selesai;

    @SerializedName("mapel")
    private MapelResponse mapelResponse;

    @SerializedName("jurusan")
    private JurusanResponse jurusanResponse;

    @SerializedName("kelas")
    private KelasResponse kelasResponse;

    @SerializedName("tahun_ajaran")
    private TahunAjaranResponse tahunAjaranResponse;

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

    public MapelResponse getMapelResponse() {
        return mapelResponse;
    }

    public void setMapelResponse(MapelResponse mapelResponse) {
        this.mapelResponse = mapelResponse;
    }

    public JurusanResponse getJurusanResponse() {
        return jurusanResponse;
    }

    public void setJurusanResponse(JurusanResponse jurusanResponse) {
        this.jurusanResponse = jurusanResponse;
    }

    public KelasResponse getKelasResponse() {
        return kelasResponse;
    }

    public void setKelasResponse(KelasResponse kelasResponse) {
        this.kelasResponse = kelasResponse;
    }

    public TahunAjaranResponse getTahunAjaranResponse() {
        return tahunAjaranResponse;
    }

    public void setTahunAjaranResponse(TahunAjaranResponse tahunAjaranResponse) {
        this.tahunAjaranResponse = tahunAjaranResponse;
    }
}
