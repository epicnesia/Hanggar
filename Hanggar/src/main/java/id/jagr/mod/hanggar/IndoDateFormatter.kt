package id.jagr.mod.hanggar

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

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
 * Fungsi untuk mengubah format tanggal
 *
 * @param dateString: String
 * @param currentPattern: String
 * @param targetPattern: String
 *
 * @return String
 */
fun formatDate(dateString: String, currentPattern: String, targetPattern: String): String {
    val parser = SimpleDateFormat(currentPattern, Locale.getDefault())
    val formatter = SimpleDateFormat(targetPattern, Locale.getDefault())

    return try {
        val date = parser.parse(dateString)
        formatter.format(date)
    } catch (e: Exception) {
        dateString // Kembalikan string asli jika terjadi kesalahan
    }
}

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
 * Mengembalikan string tanggal dari LocalDate
 * - ex: 23:15
 *
 * @param dateTime: LocalDate
 *
 * @return String
 */
fun tanggal(dateTime: LocalDate): String {
    return dateTime.toString(DATE_FORMAT)
}

/**
 * Mengembalikan LocalDate dari string tanggal
 * - ex: 23:15
 *
 * @param dateString: String
 * @param pattern: String = "yyyy-MM-dd"
 *
 * @return LocalDate
 */
fun tanggal(dateString: String?, pattern: String = DATE_FORMAT): LocalDate {
    return if (dateString != null) {
        LocalDate.parse(dateString, DateTimeFormat.forPattern(pattern))
    } else {
        LocalDate.now()
    }
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
 * Menampilkan tanggal berformat (hari<koma><spasi>tanggal<spasi>nama bulan<spasi>tahun) dari string dateTime
 * -ex: Senin, 01 Januari 2019 atau Senin, 01 Jan 2019 atau Senin, 01 January 2019
 *
 * @param dateTimeString: String
 * @param lang: String = "ID"
 * @param useShortMonthName: Boolean = false
 * @param pattern: String = "yyyy-MM-dd HH:mm:ss"
 *
 * @return String
 */
fun hariDanTanggal(
    dateTimeString: String?,
    lang: String = "ID",
    useShortMonthName: Boolean = false,
    pattern: String = DATETIME_FORMAT
): String {
    return if(dateTimeString.isNullOrBlank()) {
        "-"
    } else {
        val dateTime = DateTime.parse(
            dateTimeString,
            DateTimeFormat.forPattern(pattern)
        )
        if (lang == "EN") {
            indoDayName = arrayOf(
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
            )
            indoMonthName = arrayOf(
                "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"
            )
            indoMonthShortName = arrayOf(
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec"
            )
        } else {
            indoDayName = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu")
            indoMonthName = arrayOf(
                "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli",
                "Agustus", "September", "Oktober", "November", "Desember"
            )
            indoMonthShortName = arrayOf(
                "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul",
                "Agu", "Sep", "Okt", "Nov", "Des"
            )
        }
        val dateString = dateTime.toString("dd")
        val monthName = if(useShortMonthName) indoMonthShortName[dateTime.monthOfYear - 1] else indoMonthName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        indoDayName[dateTime.dayOfWeek - 1] + ", " + dateString + " " + monthName + " " + yearString
    }
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

/**
 * Mengembalikan tanggal dan waktu dari String dateTimeString
 * -ex: 01 Jan 2019 01:01:01
 *
 * @param dateTimeString: String
 * @param useShortMonthName: Boolean = false
 * @param pattern: String = "yyyy-MM-dd HH:mm:ss"
 *
 * @return String
 */
fun formatStringToTanggalDanJam(
    dateTimeString: String?,
    useShortMonthName: Boolean = false,
    pattern: String = DATETIME_FORMAT
): String {
    return if (dateTimeString.isNullOrBlank()) {
        "-"
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(pattern))
        val timeString = dateTime.toString(TIME_FORMAT)
        val dateString = dateTime.toString("dd")
        val monthName = if(useShortMonthName) indoMonthShortName[dateTime.monthOfYear - 1] else indoMonthName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$dateString $monthName $yearString $timeString"
    }
}

/**
 * Mengembalikan tanggal dari String dateTimeString
 * -ex: 01 Jan 2019
 *
 * @param dateTimeString: String
 * @param pattern: String = "yyyy-MM-dd HH:mm:ss"
 *
 * @return String
 */
fun formatStringToTanggal(
    dateTimeString: String?,
    pattern: String = DATETIME_FORMAT
): String {
    return if (dateTimeString.isNullOrBlank()) {
        "-"
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(pattern))
        val dateString = dateTime.toString("dd")
        val monthName = indoMonthShortName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$dateString $monthName $yearString"
    }
}

/**
 * Mengembalikan bulan dan tahun dari String dateTimeString
 * -ex: Januari 2019 atau Jan 2019
 *
 * @param dateTimeString: String
 * @param useShortMonthName: Boolean = false
 * @param pattern: String = "yyyy-MM-dd HH:mm:ss"
 *
 * @return String
 */
fun formatStringToBulanTahun(
    dateTimeString: String?,
    useShortMonthName: Boolean = false,
    pattern: String = DATETIME_FORMAT
): String {
    return if (dateTimeString.isNullOrBlank()) {
        ""
    } else {
        val dateTime =
            DateTime.parse(dateTimeString, DateTimeFormat.forPattern(pattern))
        val monthName =
            if (useShortMonthName) indoMonthShortName[dateTime.monthOfYear - 1] else indoMonthName[dateTime.monthOfYear - 1]
        val yearString = dateTime.toString("yyyy")
        "$monthName $yearString"
    }
}

/**
 * Memeriksa apakah target LocalDateTime berada di antara start dan end LocalDateTime
 *
 * @param start: LocalDateTime?
 * @param end: LocalDateTime?
 * @param target: LocalDateTime?
 *
 * @return Boolean
 */
fun isBetweenInclusive(start: LocalDateTime?, end: LocalDateTime?, target: LocalDateTime): Boolean {
    return !target.isBefore(start) && !target.isAfter(end)
}

val daysOfWeek: Array<String>
    @RequiresApi(Build.VERSION_CODES.O)
    get() {
        val daysOfWeek = Array(7) { "" }

        for (dayOfWeek in DayOfWeek.entries) {
            val localizedDayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("ID"))
            daysOfWeek[dayOfWeek.value - 1] = localizedDayName
        }

        return daysOfWeek
    }

@RequiresApi(Build.VERSION_CODES.O)
fun YearMonth.getDayOfMonthStartingFromMonday(): List<java.time.LocalDate> {
    val firstDayOfMonth = java.time.LocalDate.of(year, month, 1)
    val firstMondayOfMonth = firstDayOfMonth.with(DayOfWeek.MONDAY)
    val firstDayOfNextMonth = firstDayOfMonth.plusMonths(1)

    return generateSequence(firstMondayOfMonth) { it.plusDays(1) }
        .takeWhile { it.isBefore(firstDayOfNextMonth) }
        .toList()
}

@RequiresApi(Build.VERSION_CODES.O)
fun YearMonth.getDisplayName(): String {
    return "${month.getDisplayName(TextStyle.FULL, Locale("ID"))} $year"
}