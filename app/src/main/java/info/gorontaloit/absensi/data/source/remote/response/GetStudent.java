package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetStudent {
    @SerializedName("result")
    List<SiswaResponse> siswaResponses = new ArrayList<>();

    public List<SiswaResponse> getSiswaResponses() {
        return siswaResponses;
    }
}
