package code.challange.a20190417_prakratisharma_nycschools.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import code.challange.a20190417_prakratisharma_nycschools.MainApplication;
import code.challange.a20190417_prakratisharma_nycschools.R;
import code.challange.a20190417_prakratisharma_nycschools.adapter.SchoolAdapter;
import code.challange.a20190417_prakratisharma_nycschools.model.School;
import code.challange.a20190417_prakratisharma_nycschools.model.SchoolDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SchoolActivity extends AppCompatActivity {

    private SchoolAdapter adapter;
    private RecyclerView regionsRecyclerView;
    ProgressDialog progressDoalog;

    List<School> schoolList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        showProgressBar();

        makeSchoolsListServiceCall();

    }
// initializing the views
    private void initViews(){
        progressDoalog = new ProgressDialog(SchoolActivity.this);
        progressDoalog.setMessage("Loading....");

        regionsRecyclerView = findViewById(R.id.regions_recycler_view);
        adapter = new SchoolAdapter(this,schoolList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SchoolActivity.this);
        regionsRecyclerView.setLayoutManager(layoutManager);
        regionsRecyclerView.setAdapter(adapter);

        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissProgressBar();
                final TextView regionNameView = v.findViewById(R.id.school_name);
                final String regionName = regionNameView.getText().toString();
                makeSchoolsDetailServiceCall(regionName);
            }
        });
    }
// calling the School API
    private void makeSchoolsDetailServiceCall(String regionName) {
        MainApplication.apiManager.getSchoolDetail(regionName, new Callback<List<SchoolDetail>>() {
            @Override
            public void onResponse(Call<List<SchoolDetail>> call, Response<List<SchoolDetail>> response) {
                dismissProgressBar();
                if (response.isSuccessful()){
                    // list of school details
                    List<SchoolDetail> countries = response.body();
                    if(countries != null && !countries.isEmpty()){
                        SchoolDetail schoolDetail = countries.get(0);
                        // As we are getting only one school record as result, reading the first item fro the array.
                        Intent intent = new Intent(SchoolActivity.this, SchoolDetailActivity.class);
                        intent.putExtra("school", (Serializable) schoolDetail);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(SchoolActivity.this, "Error", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            @Override
            public void onFailure(Call<List<SchoolDetail>> call, Throwable t) {
                dismissProgressBar();
                Toast.makeText(SchoolActivity.this, t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
// show progress bar
    private void showProgressBar(){
        if(progressDoalog != null && !progressDoalog.isShowing()){
            progressDoalog.show();
        }
    }
// dismiss progress bar
    private void dismissProgressBar(){
        if(progressDoalog != null && progressDoalog.isShowing()){
            progressDoalog.dismiss();
        }
    }
// school list service call
    private void makeSchoolsListServiceCall(){

        MainApplication.apiManager.getSchool(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, final Response<List<School>> response) {

                if (response.isSuccessful()) {
                    dismissProgressBar();

                    updateListWithData(response.body());

                } else {
                    try {
                        Toast.makeText(SchoolActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT)
                                .show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {

                Toast.makeText(SchoolActivity.this, t.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void updateListWithData(List<School> schoolList) {
       adapter.setSchoolList(schoolList);
    }
}
