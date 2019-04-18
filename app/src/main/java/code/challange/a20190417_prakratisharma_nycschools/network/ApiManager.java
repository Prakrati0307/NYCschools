package code.challange.a20190417_prakratisharma_nycschools.network;

import java.util.List;

import code.challange.a20190417_prakratisharma_nycschools.models.School;
import code.challange.a20190417_prakratisharma_nycschools.models.SchoolDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static SchoolAPI service;
    private static ApiManager apiManager;

    private static String GETURL = "https://data.cityofnewyork.us/resource/";

    //private static String GETURL="https://data.cityofnewyork.us/resource/f9bf-2cp4.json";
    private ApiManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GETURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SchoolAPI.class);
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public void getSchool(Callback<List<School>> callback) {
        Call<List<School>> regionsCall = service.getSchool();
        regionsCall.enqueue(callback);
    }

    public void getSchoolDetail(String schoolName, Callback<List<SchoolDetail>> callback) {
        Call<List<SchoolDetail>> regionsCall = service.getSchoolDetail(schoolName);
        regionsCall.enqueue(callback);
    }


}