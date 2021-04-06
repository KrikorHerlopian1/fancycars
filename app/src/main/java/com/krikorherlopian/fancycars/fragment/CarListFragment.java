package com.krikorherlopian.fancycars.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.krikorherlopian.fancycars.R;
import com.krikorherlopian.fancycars.adapter.CarListAdapter;
import com.krikorherlopian.fancycars.databinding.FragmentCarListBinding;
import com.krikorherlopian.fancycars.model.Car;
import com.krikorherlopian.fancycars.model.CarRepoModel;
import com.krikorherlopian.fancycars.viewmodel.CarListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CarListFragment extends Fragment {
    private FragmentCarListBinding binding;
    CarListAdapter adapter;
    List<Car> carList = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_car_list,menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCarListBinding.inflate(inflater, container, false);;
        final View rootView = binding.getRoot();

        CarListAdapter.CarItemClickListener listener = new CarListAdapter.CarItemClickListener() {
            @Override
            public void onCarClicked(Car car) {
                if(car.getVisualizeAvailability()){
                    if(car.getAvailabilityInt() == 0)
                        Toast.makeText(getContext(),R.string.congratulations_buy, Toast.LENGTH_SHORT).show();
                }
                else{
                    //assume we also make api call to availability?id= and update the object.
                    CarListViewModel.Companion.getAvailability(car);
                    adapter.notifyDataSetChanged();
                }
            }
        };

        CarListViewModel.Companion.getCar().observe(this, new Observer<CarRepoModel>() {
            @Override
            public void onChanged(CarRepoModel carRepoModel) {

                if(carRepoModel.getThrowable() != null){
                    Toast.makeText(getContext(), carRepoModel.getThrowable().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    CarListViewModel.Companion.createDb(rootView.getContext());
                    carList = CarListViewModel.Companion.readCars();
                    binding.progress.setVisibility(View.GONE);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter = new CarListAdapter(carList, rootView.getContext(), listener);
                    binding.recyclerView.setAdapter(adapter);
                }
                else{
                    carList = carRepoModel.getCarList();
                    CarListViewModel.Companion.createDb(rootView.getContext());
                    CarListViewModel.Companion.deleteCars();
                    CarListViewModel.Companion.writeCar(carList);
                    binding.progress.setVisibility(View.GONE);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter = new CarListAdapter(carList, rootView.getContext(), listener);
                    binding.recyclerView.setAdapter(adapter);
                }
            }
        });


        if(savedInstanceState == null)
            CarListViewModel.Companion.getCarCall();
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_by_name:
                CarListViewModel.Companion.sortByCarName(carList);
                for(Car car :carList)
                    System.out.println("eeee" +car.getName());
                adapter.notifyDataSetChanged();;
                return true;
            case R.id.sort_by_availability:
                CarListViewModel.Companion.sortByCarAvailability(carList);
                adapter.notifyDataSetChanged();;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
