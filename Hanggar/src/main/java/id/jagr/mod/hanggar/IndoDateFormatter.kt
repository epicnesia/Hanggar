package id.jagr.mod.hanggar

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Formatter DateTime ke tanggal Indonesia
 *
 * Utilitas untuk memudahkan format tanggal ke format Indonesia
 * =================================================================================================
 */

/**
 * Penamaan hari dan bulan dalam bahasa Indonesia
 */
var indoDayName =
    arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu")

var indoMonthName = arrayOf(
    "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli",
    "Agustus", "September", "Oktober", "November", "Desember"
)

var indoMonthShortName = arrayOf(
    "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul",
    "Agu", "Sep", "Okt", "Nov", "Des"
)

/**
 * Mengembalikan jam dan menit dari object DateTime
 * - ex: 23:15
 *
 * @param dateTime: DateTime
 *
 * @return String
 */
fun jam(dateTime: DateTime): String {
    val timeString = dateTime.toString(DATETIME_FORMAT)
    return timeString.substring(11, 16)
}

/**
 * Mengembalikan jam dari string DateTime
 * - ex: 23:15
 *
 * @param dateTimeString: String
 *
 * @return String
 */
fun jam(dateTimeString: String): String {
    val dateTime = DateTime.parse(
        dateTimeString,
        DateTimeFormat.forPattern(DATETIME_FORMAT)
    )
    val timeString = dateTime.toString(DATETIME_FORMAT)
    return timeString.substring(11, 16)
}

/**
 * Menampilkan jam dari string DateTime via BindingAdapters
 * - ex: 23:15
 *
 * @param view: TextView
 * @param dateTimeString: String
 *
 * @return void
 */
@BindingAdapter("app:formatStringToJam")
fun formatStringToJam(view: TextView, dateTimeString: String) {
    val dateTime =
        DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
    val timeString = dateTime.toString(DATETIME_FORMAT)
    view.text = timeString.substring(11, 16)
}

/**
 * Mengembalikan nama hari dari object DateTime
 * - ex: Senin
 *
 * @param dateTime: DateTime
 *
 * @return String
 */
fun hari(dateTime: DateTime): String {
    return indoDayName[dateTime.dayOfWeek - 1]
}

/**
 * Mengembalikan tanggal berformat (hari<koma><spasi>tanggal<spasi>nama bulan<spasi>tahun) dari object DateTime
 * -ex: Senin, 01 Januari 2019 atau Senin, 01 Jan 2019
 *
 * @param dateTime: DateTime
 *
 * @return String
 */
fun hariDanTanggal(dateTime: DateTime, fullnameMonth: Boolean = true): String {
    val dateString = dateTime.toString("dd")
    val monthName =
        if (fullnameMonth) indoMonthName[dateTime.monthOfYear - 1] else indoMonthShortName[dateTime.monthOfYear - 1]
    val yearString = dateTime.toString("yyyy")
    return indoDayName[dateTime.dayOfWeek - 1] + ", " + dateString + " " + monthName + " " + yearString
}

/**
 * Mengembalikan tanggal berformat (hari<koma><spasi>tanggal<spasi>nama bulan<spasi>tahun) dari string DateTime
 * -ex: Senin, 01 Januari 2019 atau Senin, 01 Jan 2019
 *
 * @param dateTimeString: String
 *
 * @return String
 */
fun hariDanTanggal(dateTimeString: String, fullnameMonth: Boolean = true): String {
    val dateTime = DateTime.parse(
        dateTimeString,
        DateTimeFormat.forPattern(DATETIME_FORMAT)
    )
    val dateString = dateTime.toString("dd")
    val monthName =
        if (fullnameMonth) indoMonthName[dateTime.monthOfYear - 1] else indoMonthShortName[dateTime.monthOfYear - 1]
    val yearString = dateTime.toString("yyyy")
    return indoDayName[dateTime.dayOfWeek - 1] + ", " + dateString + " " + monthName + " " + yearString
}

/**
 * BindingAdapter untuk mengatur nilai string TextView menjadi tanggal berformat
 * (hari<koma><spasi>tanggal<spasi>nama bulan<spasi>tahun)
 * -ex: Senin, 01 Januari 2019
 *
 * @param view: TextView
 * @param dateTimeString: String
 *
 * @return Void
 */
@BindingAdapter("app:formatStringToHariDanTanggal")
fun formatStringToHariDanTanggal(view: TextView, dateTimeString: String) {
    val dateTime =
        DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
    val dateString = dateTime.toString("dd")
    val monthName = indoMonthName[dateTime.monthOfYear - 1]
    val yearString = dateTime.toString("yyyy")
    val text =
        indoDayName[dateTime.dayOfWeek - 1] + ", " + dateString + " " + monthName + " " + yearString
    view.text = text
}

/**
 * BindingAdapter untuk mengatur nilai string TextView menjadi tanggal berformat
 * (tanggal<spasi>nama pendek bulan<spasi>tahun<spasi>jam)
 * -ex: 01 Jan 2019 01:01:01
 *
 * @param view: TextView
 * @param dateTimeString: String
 *
 * @return Void
 */
@BindingAdapter("app:formatStringToTanggalDanJam")
fun formatStringToTanggalDanJam(view: TextView, dateTimeString: String?) {
    if (dateTimeString.isNullOrBlank()) {
        view.text = "-"
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        val formattedDateString = "$dateString $monthName $yearString $timeString"
        view.text = formattedDateString
    }
}

/**
 * Mengembalikan tanggal dan waktu dari string DateTime
 * -ex: 01 Jan 2019 01:01:01
 *
 * @param dateTimeString: String
 *
 * @return String
 */
fun formatStringToTanggalDanJam(dateTimeString: String?): String {
    return if (dateTimeString.isNullOrBlank()) {
        ""
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$dateString $monthName $yearString $timeString"
    }
}

/**
 * BindingAdapter untuk mengatur nilai DateTime TextView menjadi tanggal berformat
 * (tanggal<spasi>nama pendek bulan<spasi>tahun<spasi>jam)
 * Jika nilai DateTime null maka nilai TextView akan diatur menjadi "-"
 * -ex: 01 Jan 2019 01:01:01 atau -
 *
 * @param view: TextView
 * @param dateTime: DateTime
 *
 * @return Void
 */
@BindingAdapter("app:formatTanggalDanJam")
fun formatTanggalDanJam(view: TextView, dateTime: DateTime?) {
    if (dateTime == null) {
        view.text = "-"
    } else {
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        val formattedDateString = "$dateString $monthName $yearString $timeString"
        view.text = formattedDateString
    }
}

/**
 * Mengembalikan tanggal dan waktu dari object DateTime
 * -ex: 01 Jan 2019 01:01:01
 *
 * @param dateTime: DateTime
 *
 * @return String
 */
fun formatTanggalDanJam(dateTime: DateTime?): String {
    return if (dateTime == null) {
        ""
    } else {
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$dateString $monthName $yearString $timeString"
    }
}