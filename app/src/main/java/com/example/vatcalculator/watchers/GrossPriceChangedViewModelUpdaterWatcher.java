package com.example.vatcalculator.watchers;

import android.text.Editable;
import android.text.TextWatcher;
import com.example.vatcalculator.ui.viewmodels.GrossPriceViewModel;

import java.util.Objects;

public class GrossPriceChangedViewModelUpdaterWatcher implements TextWatcher {

    private final GrossPriceViewModel grossPriceViewModel;

    public GrossPriceChangedViewModelUpdaterWatcher(GrossPriceViewModel grossPriceViewModel) {
        this.grossPriceViewModel = grossPriceViewModel;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (Objects.isNull(s) || s.toString().isEmpty()) {
            grossPriceViewModel.setGrossPrice(null);
            return;
        }
        grossPriceViewModel.setGrossPrice(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
