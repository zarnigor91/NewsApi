package uz.mayasoft.marta.wallet.utils

object StringUtils {
    fun formatPhoneNumber(phone: String): String {
        var phone = phone
        phone = phone.replace("+", "").replace("\\s".toRegex(), "").trim { it <= ' ' }
        val l = phone.length
        val sb = StringBuilder(phone.substring(l - 2, l))
        sb.insert(0, " ")
        sb.insert(0, phone.substring(l - 4, l - 2))
        sb.insert(0, " ")
        sb.insert(0, phone.substring(l - 7, l - 4))
        sb.insert(0, " ")
        when (l) {
            12 -> {
                sb.insert(0, phone.substring(l - 9, l - 7))
                sb.insert(0, " ")
                sb.insert(0, phone.substring(l - 12, l - 9))
            }
            11 -> {
                sb.insert(0, phone.substring(l - 10, l - 7))
                sb.insert(0, " ")
                sb.insert(0, phone.substring(l - 11, l - 10))
            }
        }
        sb.insert(0, "+")
        return sb.toString()
    }
}