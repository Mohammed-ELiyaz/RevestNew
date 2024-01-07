package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BreedDetailsViewModel extends ViewModel {
    private MutableLiveData<String> imageUrl = new MutableLiveData<>();

    public MutableLiveData<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String url) {
        imageUrl.setValue(url);
    }
}
