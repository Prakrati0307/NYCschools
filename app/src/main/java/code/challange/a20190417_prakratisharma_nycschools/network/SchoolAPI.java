package code.challange.a20190417_prakratisharma_nycschools.network;


import java.util.List;

import code.challange.a20190417_prakratisharma_nycschools.model.School;
import code.challange.a20190417_prakratisharma_nycschools.model.SchoolDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolAPI {

    @GET("f9bf-2cp4.json")
    Call<List<School>> getSchool();

    @GET("f9bf-2cp4.json")
    Call<List<SchoolDetail>> getSchoolDetail(@Query("school_name") String schoolName);



}
