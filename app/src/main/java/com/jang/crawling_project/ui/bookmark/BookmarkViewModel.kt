package com.jang.crawling_project.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookmarkViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bookmark Fragment33333333"
    }
    val text: LiveData<String> = _text
}