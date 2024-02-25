package com.example.travelappinterview.common

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageManager @Inject constructor(){
    var currentLanguage: String = Language.TW
}
