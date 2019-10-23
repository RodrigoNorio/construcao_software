package com.example.trabalhocs;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.trabalhocs.View.Fragments.FragmentHome;
import com.example.trabalhocs.View.Fragments.FragmentVendas;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_principal_container)
    FrameLayout fragmentsPrincipaisContainer;

    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    private Fragment fragmentAtivo;
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    private final FragmentHome fragmentHome = new FragmentHome();
    private final FragmentVendas fragmentVendas = new FragmentVendas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupFragments();

        // Configura a barra de navegação
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    switchFragment(fragmentHome);
                    break;

                case R.id.nav_vendas:
                    switchFragment(fragmentVendas);
                    break;
            }

            return true;
        });

        // O fragment principal é exibido inicialmente
        navigationView.setSelectedItemId(R.id.nav_home);
    }

    /**
     * Carrega os fragments e exibe o fragment principal primeiro
     */
    private void setupFragments() {
        fragmentManager.beginTransaction().add(R.id.fragment_principal_container, fragmentHome).commit(); // fragment inicial

        fragmentManager.beginTransaction().add(R.id.fragment_principal_container, fragmentVendas).hide(fragmentVendas).commit();

        fragmentAtivo = fragmentHome;
    }

    /**
     * Troca de fragment no container principal
     * @param fragment o fragment a ser exibido
     */
    private void switchFragment(Fragment fragment) {
        fragmentManager.beginTransaction().hide(fragmentAtivo).show(fragment).commit();
        fragmentAtivo = fragment;
    }

}
