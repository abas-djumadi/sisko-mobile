package info.gorontaloit.absensi.data.source.remote;

import static info.gorontaloit.absensi.data.source.remote.StatusResponse.EMPTY;
import static info.gorontaloit.absensi.data.source.remote.StatusResponse.ERROR;
import static info.gorontaloit.absensi.data.source.remote.StatusResponse.SUCCESS;

public class ApiResponse<T> {
    public final StatusResponse statusResponse;
    public final String message;
    public final T body;

    public ApiResponse(StatusResponse statusResponse, T body,String message) {
        this.statusResponse = statusResponse;
        this.message = message;
        this.body = body;
    }
    public static <T> ApiResponse<T> success(T body) {
        return new ApiResponse<>(SUCCESS, body, null);
    }

    public static <T> ApiResponse<T> empty(String msg, T body) {
        return new ApiResponse<>(EMPTY, body, msg);
    }

    public static <T> ApiResponse<T> error(String msg, T body) {
        return new ApiResponse<>(ERROR, body, msg);
    }
}
