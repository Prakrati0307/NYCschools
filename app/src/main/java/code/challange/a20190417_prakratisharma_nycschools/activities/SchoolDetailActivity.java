package code.challange.a20190417_prakratisharma_nycschools.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import code.challange.a20190417_prakratisharma_nycschools.R;
import code.challange.a20190417_prakratisharma_nycschools.constants.Constants;
import code.challange.a20190417_prakratisharma_nycschools.models.SchoolDetail;

/**
 *
 * SchoolDetailActivity - Activity to display the selected school details.
 *
 */
public class SchoolDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_detail_activity);
        SchoolDetail schoolDetail = getSchoolDetailsFromIntent();

        TextView tvTestTakers = findViewById(R.id.tvTestTakers);
        TextView schoolName = findViewById(R.id.tvSchoolName);
        TextView tvReadingScore = findViewById(R.id.tvReadingScore);
        TextView tvMathScore = findViewById(R.id.tvMathScore);

        TextView tvWritingAvgScore = findViewById(R.id.tvWritingAvgScore);

        if(schoolDetail != null) {
            tvTestTakers.setText(schoolDetail.getTestTakers());
            schoolName.setText(schoolDetail.getName());
            tvReadingScore.setText(schoolDetail.getReadingScore());
            tvMathScore.setText(schoolDetail.getMathScore());

            tvWritingAvgScore.setText(schoolDetail.getWritingScore());
        }
    }

    /**
     *  Method to parse the school details form the bundle.
     * @return SchoolDetail
     */
    private SchoolDetail getSchoolDetailsFromIntent() {
        Intent intent = getIntent();
        if (!intent.hasExtra(Constants.SCHOOL_DETAILS_INTENT_KEY)) {
            throw new RuntimeException("Countries Intent was not passed!");
        }
        return  (SchoolDetail) intent.getSerializableExtra(Constants.SCHOOL_DETAILS_INTENT_KEY);
    }
}
