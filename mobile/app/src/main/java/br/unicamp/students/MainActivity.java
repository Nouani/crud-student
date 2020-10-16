package br.unicamp.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ChipNavigationBar nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.nav = (ChipNavigationBar) findViewById(R.id.nav);
        nav.setItemSelected(R.id.search, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new SearchFragment()).commit();

        nav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment selectedFragment = null;

                switch(i) {
                    case R.id.search:
                        selectedFragment = new SearchFragment();
                        break;
                    case R.id.create:
                        selectedFragment = new CreateFragment();
                        break;
                    case R.id.edit:
                        selectedFragment = new EditFragment();
                        break;
                    case R.id.delete:
                        selectedFragment = new DeleteFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
            }
        });
    }
}