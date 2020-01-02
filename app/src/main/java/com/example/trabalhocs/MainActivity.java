package com.example.trabalhocs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.Tutorial.Tutorial_Main_Menu;
import com.example.trabalhocs.View.Fragments.FragmentHome;
import com.example.trabalhocs.View.Fragments.FragmentVendas;
import com.example.trabalhocs.View.GerenciarDestinos;
import com.example.trabalhocs.View.GerenciarGrupo;
import com.example.trabalhocs.View.ListarFontesAdd;
import com.example.trabalhocs.View.GerenciarReceita;
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

    public void gerenciarreceitas (View view){
        Intent it = new Intent (MainActivity.this, GerenciarReceita.class);
        startActivity(it);
    }

    public void gerenciargrupos (View view){
        Intent it = new Intent (MainActivity.this, GerenciarGrupo.class);
        startActivity(it);
    }
    public void gerenciardestino (View view){
        Intent it = new Intent (MainActivity.this, GerenciarDestinos.class);
        startActivity(it);
    }
    public void tutorialmainmenu (View view){
        Intent it = new Intent (MainActivity.this, Tutorial_Main_Menu.class);
        startActivity(it);
    }
}
