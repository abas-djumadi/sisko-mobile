package info.gorontaloit.absensi.data.source.remote;

import info.gorontaloit.absensi.data.source.remote.response.GetSchedule;
import info.gorontaloit.absensi.data.source.remote.response.GetStudent;
import info.gorontaloit.absensi.data.source.remote.response.PostLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<PostLogin> login(@Field("email") String email, @Field("password") String password);

    @GET("jadwal")
    Call<GetSchedule> getSchedule();

    @GET("siswa")
    Call<GetStudent> getStudentBySchedule();
}
