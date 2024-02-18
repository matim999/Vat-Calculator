package com.example.vatcalculator.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.vatcalculator.common.enums.VatValue;
import com.example.vatcalculator.databinding.FragmentPrice8And23PercentBinding;
import com.example.vatcalculator.ui.viewmodels.GrossPriceViewModel;
import com.example.vatcalculator.utils.calculators.CalculatedPrice;
import com.example.vatcalculator.utils.calculators.VatAndNetValueCalculator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class Price8And23PercentFragment extends Fragment {

    private FragmentPrice8And23PercentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GrossPriceViewModel grossPriceViewModel =
                new ViewModelProvider(requireActivity()).get(GrossPriceViewModel.class);

        binding = FragmentPrice8And23PercentBinding.inflate(inflater, container, false);
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
                Optional<CalculatedPrice> calculatedPrice8Percent = VatAndNetValueCalculator.calculate(grossPrice, VatValue.VAT_8_Percent);
                Optional<CalculatedPrice> calculatedPrice23Percent = VatAndNetValueCalculator.calculate(grossPrice, VatValue.VAT_23_Percent);
                if (!calculatedPrice8Percent.isPresent() || !calculatedPrice23Percent.isPresent()) {
                    clearFieldValues();
                } else {
                    setFieldValues(calculatedPrice8Percent.get(), calculatedPrice23Percent.get());
                }
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setFieldValues(CalculatedPrice calculatedPrice8Percent, CalculatedPrice calculatedPrice23Percent) {
        binding.netValue8Percent.setText(String.valueOf(calculatedPrice8Percent.getNetString()));
        binding.vatValue8Percent.setText(String.valueOf(calculatedPrice8Percent.getVatString()));
        binding.netValue23Percent.setText(String.valueOf(calculatedPrice23Percent.getNetString()));
        binding.vatValue23Percent.setText(String.valueOf(calculatedPrice23Percent.getVatString()));
    }

    private void clearFieldValues() {
        binding.netValue8Percent.setText("");
        binding.vatValue8Percent.setText("");
        binding.netValue23Percent.setText("");
        binding.vatValue23Percent.setText("");
    }
}