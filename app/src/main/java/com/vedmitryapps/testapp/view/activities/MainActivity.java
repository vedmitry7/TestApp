package com.vedmitryapps.testapp.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.vedmitryapps.testapp.R;
import com.vedmitryapps.testapp.model.City;
import com.vedmitryapps.testapp.model.api.ApiFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spinner)
    Spinner spinner;

    private List<String> list;
    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = new ArrayList<>();

        cities = new ArrayList<>();

        final CustomAdapter adapter = new CustomAdapter(this);

        ApiFactory.getApi().getCities().enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                list.add(getString(R.string.choose_your_city));

                cities = response.body();
                for (City city: cities
                     ) {
                    list.add(city.getName());
                }
                adapter.notifyDataSetChanged();
                spinner.performClick();
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    return;
                }

                Intent intent = new Intent(MainActivity.this, InstitutionsActivity.class);
                //intent.putExtra("cityId", cities.get(position-1).getId());
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setAdapter(adapter);
        spinner.setSelection(-1);
    }

    public class CustomAdapter extends ArrayAdapter {

        public CustomAdapter(Context context) {
            super(context, R.layout.spinner_item, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = View.inflate(MainActivity.this, R.layout.spinner_item, null);
                TextView tv = (TextView) convertView;
                tv.setText(list.get(position));
            return convertView;
        }
    }
}
