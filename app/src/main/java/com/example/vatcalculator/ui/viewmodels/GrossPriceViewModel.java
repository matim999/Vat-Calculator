package com.example.vatcalculator.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GrossPriceViewModel extends ViewModel {

    private final MutableLiveData<String> grossPrice;

    public GrossPriceViewModel() {
        grossPrice = new MutableLiveData<>();
    }

    public LiveData<String> getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(String grossPrice) {
        this.grossPrice.postValue(grossPrice);
    }
}