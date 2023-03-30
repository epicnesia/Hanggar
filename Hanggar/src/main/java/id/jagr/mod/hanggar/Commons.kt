package id.jagr.mod.hanggar

/**
 * Kumpulan berbagai utilitas
 * =================================================================================================
 */

/**
 * Mengembalikan durasi dari jumlah menit dalan type integer
 * - ex:
 *  -> 1 hari 1 jam 1 menit
 *  -> 1 jam 1 menit
 *  -> 51 menit
 *
 * @param menit: Int
 *
 * @return String
 */
fun durasi(menit: Int): String {
    val res = StringBuilder()
    val day = menit / 1440
    val rem = menit % 1440
    val hour = rem / 60
    val min = rem % 60
    when {
        day > 0 -> {
            res.append("$day hari")
            if (min > 0) {
                res.append(" $hour jam $min menit")
            } else {
                res.append(" $hour jam")
            }
        }
        hour > 0 -> {
            res.append("$hour jam")
            if (min > 0) {
                res.append(" $min menit")
            }
        }
        else -> {
            res.append("$min menit")
        }
    }
    return res.toString()
}