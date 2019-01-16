package com.example.molder.footprint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMainFragment extends Fragment {
    FragmentActivity activity ;

    private RecyclerView recyclerView ;
    private View schedule_fragment ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        // Inflate the layout for this fragment
        schedule_fragment = inflater.inflate(R.layout.schedule_main, container, false);
        handleViews();
        return schedule_fragment;

//        recyclerView.setAdapter(new TripAdapter(this,getTrip()));

    }
    private class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder>{
        Context context ;
        List<Trip> trips ;
        public TripAdapter(Context context,List<Trip>trips){
            this.context = context ;
            this.trips = trips ;
        }

        @Override
        public int getItemCount() {
            return trips.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView ;
            TextView shTvTitle ;
            TextView shTvDate ;
            public MyViewHolder(View itemView){
                super(itemView);
                imageView = itemView.findViewById(R.id.shImgView);
                shTvTitle = itemView.findViewById(R.id.shTvTitle);
                shTvDate =itemView.findViewById(R.id.shTvDate);
            }
        }

        @NonNull
        @Override //載入item_view的layout檔
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.schedule_main_item,viewGroup,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            final Trip trip = trips.get(i);
            myViewHolder.imageView.setImageResource(trip.getImageid());
            myViewHolder.shTvTitle.setText(trip.getTitle());
            myViewHolder.shTvDate.setText(trip.getDate());
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });



        }

    }



    private void handleViews(){
        recyclerView= schedule_fragment.findViewById(R.id.shRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new TripAdapter(activity,getTrip()));
        FloatingActionButton shBtAdd = schedule_fragment.findViewById(R.id.shBtAdd);
        shBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ScheduleCreateActivity.class);
                startActivity(intent);
//                Fragment fragment = new ScheduleCreateFragment();
//                changeFragment(fragment);
            }
        });


    }

    private List<Trip>getTrip(){
        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip(R.drawable.sh_mountaine,"TripName","2019/05/24"));
        return trips ;
    }


}