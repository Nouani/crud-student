package br.unicamp.students.Retrofit;

import java.util.List;

import br.unicamp.students.Retrofit.Objects.Message;
import br.unicamp.students.Retrofit.Objects.Student;
import br.unicamp.students.Retrofit.Objects.StudentEdit;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface Service {
    @GET("/student")
    Call<List<Student>> index();

    @GET("/student/{RA}")
    Call<Student> show(@Path("RA") String RA);

    @POST("/student")
    Call<Student> store(@Body Student student);

    @PUT("/student/{RA}")
    Call<Student> update(@Path("RA") String RA, @Body StudentEdit student);

    @DELETE("/student/{RA}")
    Call<Message> destroy(@Path("RA") String RA);
}
