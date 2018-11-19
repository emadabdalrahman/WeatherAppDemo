package com.example.emad.weatherappdemo.MainActivity.Fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.emad.weatherappdemo.MainActivity.CityAdapter;
import com.example.emad.weatherappdemo.MainActivity.VModel.CityWeatherVModel;
import com.example.emad.weatherappdemo.POJOs.CWeather.CityWeather;
import com.example.emad.weatherappdemo.R;
import com.example.emad.weatherappdemo.ToolbarShadowView;

import java.util.ArrayList;

import androidx.navigation.fragment.NavHostFragment;

public class MainFragment extends Fragment implements CityAdapter.ItemListener {

    LiveData<ArrayList<CityWeather>> mCitesWeather;
    CityAdapter mCityAdapter;
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final LottieAnimationView lottieAnimationView = rootView.findViewById(R.id.loading_animation_view);
        lottieAnimationView.playAnimation();

        initRecyclerView(rootView);
        initToolbar(rootView);
        CityWeatherVModel cityWeatherVModel = ViewModelProviders.of(this).get(CityWeatherVModel.class);
        mCitesWeather = cityWeatherVModel.getCitesWeather();
        mCitesWeather.observe(this, new Observer<ArrayList<CityWeather>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CityWeather> cityWeather) {
                updateRecycler(cityWeather);
                lottieAnimationView.pauseAnimation();
                lottieAnimationView.setVisibility(View.GONE);
            }
        });

        return rootView;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.cites_recyclerView);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setRecycleChildrenOnDetach(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void updateRecycler(ArrayList<CityWeather> citesWeather) {
        if (mCityAdapter == null) {
            mCityAdapter = new CityAdapter(citesWeather, this);
            mRecyclerView.setAdapter(mCityAdapter);
        } else {
            mCityAdapter.updateDataSet(citesWeather);
        }
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.main_fg_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ToolbarShadowView toolbarShadowView = view.findViewById(R.id.elevationView);
        toolbarShadowView.setRecyclerView(mRecyclerView);
        toolbarShadowView.setLineDivider(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        composeEmail();
        return true;
    }

    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:emadabdalrahman11@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "mail from weather app");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCitesWeather.hasActiveObservers())
            mCitesWeather.removeObservers(this);

        mRecyclerView.setAdapter(null);
        if (mCityAdapter != null)
            mCityAdapter.setItemListener(null);
        mCityAdapter = null;
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("CityID", mCitesWeather.getValue().get(position).getId());
        bundle.putInt("Visibility", mCitesWeather.getValue().get(position).getVisibility());
        NavHostFragment.findNavController(this).navigate(R.id.forecastFragment, bundle);
    }
}
