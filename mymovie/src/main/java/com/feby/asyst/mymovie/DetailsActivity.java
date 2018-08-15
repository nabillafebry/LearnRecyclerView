package com.feby.asyst.mymovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.feby.asyst.mymovie.model.Movie;
import com.feby.asyst.mymovie.utility.Constant;

public class DetailsActivity extends AppCompatActivity {

    ImageView ivImageDetails;
    TextView tvTitleDetails, tvReleaseDetails, tvOverviewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivImageDetails = findViewById(R.id.image_details_imageview);
        tvTitleDetails = findViewById(R.id.title_details_textview);
        tvReleaseDetails = findViewById(R.id.release_date_details_textview);
        tvOverviewDetails = findViewById(R.id.overview_details_textview);

        if (getIntent().getExtras() != null){
            Movie movie = getIntent().getExtras().getParcelable("movie");
            tvTitleDetails.setText(movie.getTitle());
            tvReleaseDetails.setText(movie.getReleaseDate());
            tvOverviewDetails.setText(movie.getOverview());

            RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_broken).error(R.drawable.ic_broken);
            Glide.with(this).load(Constant.KEY_URL_DETAILS+movie.getBackdrop_path()).apply(requestOptions).into(ivImageDetails);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
