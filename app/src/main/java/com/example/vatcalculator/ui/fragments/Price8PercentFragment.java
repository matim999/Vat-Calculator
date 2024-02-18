package com.example.vatcalculator.ui.fragments;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.vatcalculator.common.enums.VatValue;
import com.example.vatcalculator.databinding.FragmentPrice8PercentBinding;
import com.example.vatcalculator.ui.viewmodels.GrossPriceViewModel;
import com.example.vatcalculator.utils.calculators.CalculatedPrice;
import com.example.vatcalculator.utils.calculators.VatAndNetValueCalculator;
import com.example.vatcalculator.utils.textfilters.DecimalDigitsInputFilter;
import com.example.vatcalculator.watchers.GrossPriceChangedViewModelUpdaterWatcher;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class Price8PercentFragment extends Fragment {

    private FragmentPrice8PercentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GrossPriceViewModel grossPriceViewModel =
                new ViewModelProvider(requireActivity()).get(GrossPriceViewModel.class);

        binding = FragmentPrice8PercentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        grossPriceViewModel.getGrossPrice().observe(getViewLifecycleOwner(), fieldValuesGrossPriceChangeObserver());
        return root;
    }

    @NotNull
    private Observer<String> fieldValuesGrossPriceChangeObserver() {
        return grossPrice -> {
            if (Objects.isNull(grossPrice)) {
                clearFieldValues();
            } else {
                Optional<CalculatedPrice> calculatedPrice = VatAndNetValueCalculator.calculate(grossPrice, VatValue.VAT_8_Percent);
                if (!calculatedPrice.isPresent()) {
                    clearFieldValues();
                } else {
                    setFieldValues(calculatedPrice.get());
                }
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setFieldValues(CalculatedPrice calculatedPrice) {
        binding.netValue.setText(String.valueOf(calculatedPrice.getNetString()));
        binding.vatValue.setText(String.valueOf(calculatedPrice.getVatString()));
    }

    private void clearFieldValues() {
        binding.netValue.setText("");
        binding.vatValue.setText("");
    }
}