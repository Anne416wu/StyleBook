package com.example.stylebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.stylebook.Closet.ClosetAddActivity;

public class HomepageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.homepage_fragment,container,false);
        ImageButton addButton=(ImageButton)view.findViewById(R.id.Add_Button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClosetAddActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
}
