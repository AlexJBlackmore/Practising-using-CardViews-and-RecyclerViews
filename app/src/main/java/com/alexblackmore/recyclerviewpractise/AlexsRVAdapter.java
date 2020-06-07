package com.alexblackmore.recyclerviewpractise;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AlexsRVAdapter extends RecyclerView.Adapter<AlexsRVAdapter.AlexsViewHolder> {

    class AlexsViewHolder extends RecyclerView.ViewHolder {

        public final TextView alexsTV;
        public final TextView alexsTV2;
        public final TextView alexsTV3;
        public final ImageView alexsIV;

        final AlexsRVAdapter alexsRVAdapterObj;
        View.OnClickListener myListener;
        Sport rugbySport;

        public AlexsViewHolder(View myViewParam, AlexsRVAdapter myAdapterParam){
            //Call constructor of super (the generic ViewHolder class) and pass it the View
            super(myViewParam);
            //Initialise the TV from the XML
            alexsTV = myViewParam.findViewById(R.id.recyclerview_TV_wdg);
            alexsTV2 = myViewParam.findViewById(R.id.recyclerview_TV_wdg2);
            alexsTV3 = myViewParam.findViewById(R.id.recyclerview_TV_wdg3);
            alexsIV = myViewParam.findViewById(R.id.sportsImageIV_wdg);
            //Set the adapter
            this.alexsRVAdapterObj = myAdapterParam;

            myListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Sport currentSport = myListOfThingsInsideAdapterAL.get(getLayoutPosition());
                    Intent launchDetail = new Intent(v.getContext(), DetailActivity.class);
                    launchDetail.putExtra("title", currentSport.getTitle());
                    launchDetail.putExtra("info", currentSport.getInfo());
                    launchDetail.putExtra("desc", currentSport.getDesc());
                    launchDetail.putExtra("image_resource", currentSport.getImageResource());
                    alexsContextObj.startActivity(launchDetail);

                }
            };

            myViewParam.setOnClickListener(myListener);

        }

        void bindSportToTVs(Sport currentSportParam){
            alexsTV.setText(currentSportParam.getTitle());
            alexsTV2.setText(currentSportParam.getInfo());
            alexsTV3.setText(currentSportParam.getDesc());
            Glide.with(alexsContextObj).load(currentSportParam.getImageResource()).into(alexsIV);
        }
    }


    private final ArrayList<Sport> myListOfThingsInsideAdapterAL;
    private Context alexsContextObj;
    private LayoutInflater alexsLayoutInflater;


    public AlexsRVAdapter (Context myContextParam, ArrayList<Sport> myArrayListParam) {
        alexsLayoutInflater = LayoutInflater.from(myContextParam);
        this.myListOfThingsInsideAdapterAL = myArrayListParam;
        this.alexsContextObj = myContextParam;
    }

    @NonNull
    @Override
    public AlexsRVAdapter.AlexsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myViewToBeInflated = alexsLayoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AlexsViewHolder(myViewToBeInflated, this);
    }


    @Override
    public void onBindViewHolder(@NonNull AlexsRVAdapter.AlexsViewHolder holder, int position) {
        Sport currentSport = myListOfThingsInsideAdapterAL.get(position);
        holder.bindSportToTVs(currentSport);
    }

    @Override
    public int getItemCount() {
        return myListOfThingsInsideAdapterAL.size();
    }
}
