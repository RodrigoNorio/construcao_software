package com.example.trabalhocs.View.Fragments;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.trabalhocs.Controller.HistoricoController;

/**
 * Classe abstrata para implementar fragments configuraveis usados na funcionalidade de histórico
 */
public abstract class FragmentConfiguravel extends Fragment {

    public abstract void config(Context context, HistoricoController controller);

}
