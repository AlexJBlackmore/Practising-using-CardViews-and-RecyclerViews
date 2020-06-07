package com.alexblackmore.recyclerviewpractise;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

import static com.alexblackmore.recyclerviewpractise.AddSport.EXTRA_DESC;
import static com.alexblackmore.recyclerviewpractise.AddSport.EXTRA_INFO;
import static com.alexblackmore.recyclerviewpractise.AddSport.EXTRA_TITLE;


public class MainActivity extends AppCompatActivity {

    private RecyclerView alexsRecyclerView;
    private AlexsRVAdapter alexsAdapterObj;

    private ArrayList<Sport> sportArrayList;

    public static final int TEXT_REQUEST = 1;

    private FloatingActionButton myFAB;
    private View.OnClickListener myFABListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFAB = findViewById(R.id.floatingActionButton_wdg);
        myFABListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addSportIntent = new Intent(myFAB.getContext(), AddSport.class);
                startActivityForResult(addSportIntent, TEXT_REQUEST);
            }
        };
        myFAB.setOnClickListener(myFABListener);

        sportArrayList = new ArrayList<>();

        alexsRecyclerView = findViewById(R.id.recyclerView_wdg);
        alexsAdapterObj = new AlexsRVAdapter(this, sportArrayList);
        alexsRecyclerView.setAdapter(alexsAdapterObj);
        alexsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        createArrayListfromResources();

        ItemTouchHelper.SimpleCallback mySC = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getLayoutPosition();
                int to = target.getLayoutPosition();
                Collections.swap(sportArrayList, from, to);
                alexsAdapterObj.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                sportArrayList.remove(viewHolder.getLayoutPosition());
                alexsAdapterObj.notifyItemRemoved(viewHolder.getLayoutPosition());
            }
        };

        ItemTouchHelper myITH = new ItemTouchHelper(mySC);

        myITH.attachToRecyclerView(alexsRecyclerView);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String newSportTitle = data.getStringExtra(EXTRA_TITLE);
                String newSportInfo = data.getStringExtra(EXTRA_INFO);
                String newSportDesc = data.getStringExtra(EXTRA_DESC);

                Sport newSport = new Sport(newSportTitle, newSportInfo, newSportDesc, 2131230836);
                sportArrayList.add(newSport);
                alexsAdapterObj.notifyDataSetChanged();
            }
        }
    }

    public void createArrayListfromResources() {
        String[] sportTitlesArray = getResources().getStringArray(R.array.sport_titles);
        String[] sportInfosArray = getResources().getStringArray(R.array.sport_infos);
        String[] sportDescsArray = getResources().getStringArray(R.array.sport_descs);

        TypedArray sportsImageResourcesTA = getResources().obtainTypedArray(R.array.sport_banner_images);

        sportArrayList.clear();

        for (int i = 0; i < sportTitlesArray.length; i++) {
            sportArrayList.add(new Sport(sportTitlesArray[i], sportInfosArray[i], sportDescsArray[i], sportsImageResourcesTA.getResourceId(i,0)));
        }



        alexsAdapterObj.notifyDataSetChanged();

        System.out.println("Image is called " + sportsImageResourcesTA.getResourceId(0,0));
        System.out.println("Image is called " + R.id.enterDescET_wdg);
        System.out.println("Image is called " + R.drawable.img_basketball);

        sportsImageResourcesTA.recycle();
    }
}
