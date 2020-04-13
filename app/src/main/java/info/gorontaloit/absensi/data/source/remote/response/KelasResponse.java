package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class KelasResponse {
    @SerializedName("id")
    Integer id;

    @SerializedName("nama")
    String nama;

    public KelasResponse(Integer id, String nama) {
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
