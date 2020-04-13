package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSchedule {
    @SerializedName("result")
    private ScheduleResponsePaged srp;

    public ScheduleResponsePaged getSrp() {
        return srp;
    }
}
