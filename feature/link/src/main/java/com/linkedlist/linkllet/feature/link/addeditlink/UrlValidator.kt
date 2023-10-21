package com.linkedlist.linkllet.feature.link.addeditlink

import java.util.regex.Pattern

class UrlValidator {
    fun validateUrl(url: String): Boolean {
        val pattern = Pattern.compile(
            "^(https?|ftp)://[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?\$",
            Pattern.CASE_INSENSITIVE
        )
        val matcher = pattern.matcher(url)
        return matcher.matches()
    }
}