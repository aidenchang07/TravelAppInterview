package com.example.travelappinterview.domain.use_case

import com.example.travelappinterview.common.LanguageManager
import javax.inject.Inject

/**
 * Created by AidenChang 2024/02/26
 */
class GetLanguageUseCase @Inject constructor(
    private val languageManager: LanguageManager
) {
    operator fun invoke(): String {
        return languageManager.currentLanguage
    }
}