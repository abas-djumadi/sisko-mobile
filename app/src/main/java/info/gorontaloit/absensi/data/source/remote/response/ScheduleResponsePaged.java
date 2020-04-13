package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import info.gorontaloit.absensi.data.model.Schedule;

public class ScheduleResponsePaged {
    @SerializedName("current_page")
    String currentPage;

    @SerializedName("data")
    List<ScheduleResponse> scheduleResponseList = new ArrayList<>();

    public List<ScheduleResponse> getScheduleResponseList() {
        return scheduleResponseList;
    }
}
