package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class SiswaResponse {
    @SerializedName("id")
    Integer id;

    @SerializedName("nama")
    String nama;

    @SerializedName("nis")
    String nis;

    public SiswaResponse(Integer id, String nama, String nis) {
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
