package com.example.vatcalculator;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.vatcalculator.databinding.ActivityMainBinding;
import com.example.vatcalculator.ui.viewmodels.GrossPriceViewModel;
import com.example.vatcalculator.utils.textfilters.DecimalDigitsInputFilter;
import com.example.vatcalculator.watchers.GrossPriceChangedViewModelUpdaterWatcher;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        GrossPriceViewModel grossPriceViewModel = new ViewModelProvider(this).get(GrossPriceViewModel.class);

        EditText editText = binding.editTextNumber;
        editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4, 2)});
        editText.addTextChangedListener(new GrossPriceChangedViewModelUpdaterWatcher(grossPriceViewModel));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bottomNavigationView, Objects.requireNonNull(navHostFragment).getNavController());
    }

}