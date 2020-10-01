package com.example.marta.ui.dashboard

object RjexNumber {
    fun IntToString(n: Int): String {
        val s = n.toString()
        return StringToString(s)
    }

    fun StringToString(s: String): String {
        var s = s
        var s2 = ""
        var l = s.length
        for (i in l - 1 downTo 0) s2 += s[i]
        s = ""
        run {
            var i = 0
            while (i + 3 <= l) {
                s += s2.substring(i, i + 3) + " "
                i += 3
            }
        }
        l = l % 3
        s += s2.substring(s2.length - l)
        s2 = ""
        l = s.length
        for (i in l - 1 downTo 0) s2 += s[i]
        return s2.trim { it <= ' ' }
    }
}