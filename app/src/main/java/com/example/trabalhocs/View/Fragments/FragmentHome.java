package com.example.trabalhocs.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.trabalhocs.R;
import com.example.trabalhocs.View.GerenciarGrupo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHome extends Fragment {

    @BindView(R.id.btn)
    Button btn;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GerenciarGrupo.class);
            startActivity(intent);
        });

        return view;
    }

}
