package com.example.ruhulamin.datawork;

import android.app.Activity;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by ruhulamin on 1/5/18.
 */

//Adapter of listView

public class BloodList extends ArrayAdapter<Donor> {
    private Activity context;
    private List<Donor> DonorList;


    public DonorList(Activity context,List<Donor> DonorList){
        super(context, R.layout.list_layout,DonorList);

        this.context=context;
        this.DonorList=DonorList;


    }
    


}
