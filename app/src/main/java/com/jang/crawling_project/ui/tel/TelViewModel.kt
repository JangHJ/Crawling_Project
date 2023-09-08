package com.jang.crawling_project.ui.tel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TelViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Tel Fragment22222222"
    }
    val text: LiveData<String> = _text
}