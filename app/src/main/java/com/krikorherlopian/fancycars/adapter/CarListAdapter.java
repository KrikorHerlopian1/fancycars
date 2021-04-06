package com.krikorherlopian.fancycars.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.krikorherlopian.fancycars.R;
import com.krikorherlopian.fancycars.model.Car;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Car> cars;
    Context context;
    int lastPosition = -1;
    CarItemClickListener listener;
    public CarListAdapter(List<Car> cars, Context context,CarItemClickListener listener){
        this.cars = cars;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_car, parent, false);
        return new CarHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CarHolder holderItem = (CarHolder) holder;
        configureViewHolder(holderItem, position);
    }

    private void configureViewHolder(final CarHolder holder, final int position) {

        Animation anim;
        if(position > lastPosition){
            anim = AnimationUtils.loadAnimation(context,R.anim.up_from_bottom);
        }
        else{
            anim = AnimationUtils.loadAnimation(context,R.anim.down_from_top);
        }
        lastPosition = position;
        holder.itemView.startAnimation(anim);
        holder.setInformation(cars.get(position),context);
        holder.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCarClicked(cars.get(position));
            }
        });
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
    public interface CarItemClickListener {
        void onCarClicked(Car car);
    }
}


class CarHolder extends RecyclerView.ViewHolder{
    TextView carNameView;
    TextView carMakeModelView;
    TextView carYearView;
    ImageView carImageView;
    Button buttonView;
    public CarHolder(View itemView) {
        super(itemView);
        carNameView = itemView.findViewById(R.id.car_name);
        carMakeModelView = itemView.findViewById(R.id.car_make_model);
        carYearView = itemView.findViewById(R.id.car_year);
        carImageView = itemView.findViewById(R.id.car_image);
        buttonView = itemView.findViewById(R.id.button);
    }
    public void setInformation(Car car, Context context){
        carNameView.setText(car.getName());
        carMakeModelView.setText(car.getMake()+" , "+car.getModel());
        carYearView.setText(car.getYear());
        Glide.with(context).load(car.getImg()).into(carImageView);
        if(car.getVisualizeAvailability()){
            if(car.getAvailabilityInt() == 0){
                buttonView.setVisibility(View.VISIBLE);
                buttonView.setText(R.string.buy);
            }
            else if(car.getAvailabilityInt() == 2){
                buttonView.setText(R.string.out_of_stock);
                buttonView.setVisibility(View.VISIBLE);
            }
            else{
                //unavailable
                buttonView.setVisibility(View.GONE);
            }
        }
        else{
            buttonView.setVisibility(View.VISIBLE);
            buttonView.setText(R.string.check_availability);
        }
    }
}
