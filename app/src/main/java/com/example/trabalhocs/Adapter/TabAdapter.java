package com.example.trabalhocs.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.trabalhocs.Controller.HistoricoController;
import com.example.trabalhocs.View.Fragments.FragmentConfiguravel;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {

    private final List<FragmentConfiguravel> fragmentList = new ArrayList<>();
    private final List<String> titulosList = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    public void config(Context context, HistoricoController controller){
        for (FragmentConfiguravel f : fragmentList) {
            f.config(context, controller);
        }
    }

    public void addFragment(FragmentConfiguravel fragment, String title) {
        fragmentList.add(fragment);
        titulosList.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulosList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
