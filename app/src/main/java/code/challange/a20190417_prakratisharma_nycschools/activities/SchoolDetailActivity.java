package code.challange.a20190417_prakratisharma_nycschools.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import code.challange.a20190417_prakratisharma_nycschools.R;
import code.challange.a20190417_prakratisharma_nycschools.model.SchoolDetail;


public class SchoolDetailActivity extends AppCompatActivity {

    private SchoolDetail schoolDetail;
    private String region;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_detail_activity);
        parseIntent();

        TextView tvTestTakers = findViewById(R.id.tvTestTakers);
        TextView schoolName = findViewById(R.id.tvSchoolName);
        TextView tvReadingScore = findViewById(R.id.tvReadingScore);
        TextView tvMathScore = findViewById(R.id.tvMathScore);

        TextView tvWritingAvgScore = findViewById(R.id.tvWritingAvgScore);

        tvTestTakers.setText("No of Sat Test Taker: "+schoolDetail.getTestTakers());
        schoolName.setText("School Name :"+schoolDetail.getName());
        tvReadingScore.setText("Sat Reading Avg Score: "+schoolDetail.getReadingScore());
        tvMathScore.setText("Sat Math Avg Score :"+schoolDetail.getMathScore());

        tvWritingAvgScore.setText("Sat Writting Avg Score: "+schoolDetail.getWritingScore());




    }

    private void parseIntent() {
        Intent intent = getIntent();
       if (!intent.hasExtra("school")) {
            throw new RuntimeException("Countries Intent was not passed!");
        }
        schoolDetail = (SchoolDetail) intent.getSerializableExtra("school");
       // region = intent.getStringExtra("school_name");

    }
}
