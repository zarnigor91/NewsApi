package com.example.marta.ext

import java.text.MessageFormat

object TextUtils {

    fun removeAllWhiteSpaces(value: String) =
        value.replace("\\s".toRegex(), "")

    fun replaceAllLetters(value: String, exceptDot: Boolean = false): String =
        value.replace((if (exceptDot) "[^0-9,.]+" else "[^0-9]+").toRegex(), "")

    fun toHiddenPhoneNumber(value: String): String {
        return if (value.length == 12) {
            val array: Array<String> =
                arrayOf(
                    value.substring(0, 3),
                    value.substring(3, 5),
                    "***",
                    "**",
                    value.substring(10, 12)
                )
            MessageFormat("+{0} {1} {2} {3} {4}").format(array)
        } else value
    }

    fun toPhoneNumber(value: String): String {
        return if (value.length == 12) {
            val array: Array<String> =
                arrayOf(
                    value.substring(0, 3),
                    value.substring(3, 5),
                    value.substring(5, 8),
                    value.substring(8, 10),
                    value.substring(10, 12)
                )
            MessageFormat("+{0} ({1}) {2}-{3}-{4}").format(array)
        } else value
    }
}