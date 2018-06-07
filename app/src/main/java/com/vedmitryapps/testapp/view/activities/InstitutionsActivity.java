package com.vedmitryapps.testapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vedmitryapps.testapp.R;
import com.vedmitryapps.testapp.model.api.ApiFactory;
import com.vedmitryapps.testapp.model.Institution;
import com.vedmitryapps.testapp.view.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vedmitryapps.testapp.Constants.KEY_ADDRESS;
import static com.vedmitryapps.testapp.Constants.KEY_DESCRIPTION;
import static com.vedmitryapps.testapp.Constants.KEY_IMAGE_URL;
import static com.vedmitryapps.testapp.Constants.KEY_LATITUDE;
import static com.vedmitryapps.testapp.Constants.KEY_LONGITUDE;
import static com.vedmitryapps.testapp.Constants.KEY_NAME;

public class InstitutionsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerAdapter mAdapter;

    private List<Institution> mInstitutions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution);
        ButterKnife.bind(this);

       // int id = getIntent().getIntExtra("cityId", 0);

        ApiFactory.getApi().getInstitutions().enqueue(new Callback<List<Institution>>() {
            @Override
            public void onResponse(Call<List<Institution>> call, Response<List<Institution>> response) {

                for (Institution i:response.body()
                     ) {
                    mInstitutions.add(i);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Institution>> call, Throwable t) {

            }
        });

        mAdapter = new RecyclerAdapter(mInstitutions, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra(KEY_NAME, mInstitutions.get(itemPosition).getName());
        intent.putExtra(KEY_ADDRESS, mInstitutions.get(itemPosition).getAddress());
        intent.putExtra(KEY_DESCRIPTION, mInstitutions.get(itemPosition).getDescription());
        intent.putExtra(KEY_IMAGE_URL, mInstitutions.get(itemPosition).getImageUrl());
        intent.putExtra(KEY_LATITUDE, mInstitutions.get(itemPosition).getLatitude());
        intent.putExtra(KEY_LONGITUDE, mInstitutions.get(itemPosition).getLongitude());
        startActivity(intent);
    }
}
