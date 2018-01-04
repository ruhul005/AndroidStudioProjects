package com.example.ruhulamin.memecreator;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ruhulamin on 5/6/17.
 */

public class TopSectionFragment extends Fragment {


    private static EditText topTextInput;

    private static EditText bottomTextInput;

    TopSectionListener ActivityCommander;

    public interface TopSectionListener{

        public void createMeme(String top,String bottom);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            ActivityCommander =(TopSectionListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.top_section_fragment,container,false);

        topTextInput= (EditText) view.findViewById(R.id.topTextInput);
        bottomTextInput= (EditText) view.findViewById(R.id.bottomTextInput);
        final Button button =(Button) view.findViewById(R.id.button);

        button.setOnClickListener(
                new View.OnClickListener(){

                    public void onClick(View v)
                    {
                        buttonClicked(v);
                    }
                }

        );





        return view;
    }

    public void buttonClicked(View view){

        //ActivityCommander.createMeme(topTextInput.getText().toString();
        ActivityCommander.createMeme(topTextInput.getText().toString(),bottomTextInput.getText().toString());

        //ActivityCommander.createMeme(bottomTextInput.getText().toString());



    }
}
