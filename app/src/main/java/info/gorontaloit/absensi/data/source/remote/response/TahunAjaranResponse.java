package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class TahunAjaranResponse {
    @SerializedName("id")
    private Integer id;

    @SerializedName("tahun")
    private Integer tahun;

    @SerializedName("nama")
    private String nama;

    @SerializedName("status")
    private Integer status;

    public TahunAjaranResponse(Integer id, Integer tahun, String nama, Integer status) {
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
