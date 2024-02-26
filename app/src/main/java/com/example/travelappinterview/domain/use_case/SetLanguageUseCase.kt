package com.example.travelappinterview.domain.use_case

import com.example.travelappinterview.common.LanguageManager
import javax.inject.Inject

/**
 * Created by AidenChang 2024/02/26
 */
class SetLanguageUseCase @Inject constructor(
    private val languageManager: LanguageManager
) {
    operator fun invoke(newLanguage: String) {
        languageManager.currentLanguage = newLanguage
    }
}