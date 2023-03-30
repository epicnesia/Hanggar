package id.jagr.mod.hanggar.test.miscTest

import android.content.Context
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import id.jagr.mod.hanggar.*
import id.jagr.mod.hanggar.base.BaseTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class IndoDateFormatterTest : BaseTest() {

    private val appContext: Context = ApplicationProvider.getApplicationContext()
    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    override fun teardown() {
        super.teardown()
        Dispatchers.resetMain()
    }

    @Test
    fun `Testing IndoDateFormatter var indoDayName nilainya sudah sesuai`() {
        runTest {
            val dayNameArrayList: ArrayList<String> = arrayListOf()
            dayNameArrayList.addAll(indoDayName)
            val expectedValue = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu", "Senin")
            dayNameArrayList.add("Senin")
            Truth.assertThat(dayNameArrayList.toArray()).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun jam(dateTime) nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val dateTime = DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
            val expectedValue = "13:16"
            Truth.assertThat(jam(dateTime)).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun jam(dateTimeString) nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val expectedValue = "13:16"
            Truth.assertThat(jam(dateTimeString)).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun formatStringToJam(view, dateTimeString), teks view sudah sesuai`() {
        runTest {
            val view = TextView(appContext)
            val dateTimeString = "2022-07-01 13:16:13"
            val expectedValue = "13:16"
            formatStringToJam(view, dateTimeString)
            Truth.assertThat(view.text.toString()).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun hari(dateTime), nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val dateTime = DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
            val expectedValue = "Jum'at"
            Truth.assertThat(hari(dateTime)).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun hariDanTanggal(dateTime), nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val dateTime = DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
            val expectedValue = "Jum'at, 01 Juli 2022"
            Truth.assertThat(hariDanTanggal(dateTime)).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun hariDanTanggal(dateTimeString), nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val expectedValue = "Jum'at, 01 Juli 2022"
            Truth.assertThat(hariDanTanggal(dateTimeString)).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun hariDanTanggal(dateTimeString, lang = ID), nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val expectedValue = "Jum'at, 01 Juli 2022"
            Truth.assertThat(hariDanTanggal(dateTimeString)).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun formatStringToHariDanTanggal(view, dateTimeString), teks view sudah sesuai`() {
        runTest {
            val view = TextView(appContext)
            val dateTimeString = "2022-07-01 13:16:13"
            val expectedValue = "Jum'at, 01 Juli 2022"
            formatStringToHariDanTanggal(view, dateTimeString)
            Truth.assertThat(view.text.toString()).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun formatStringToTanggalDanJam(view, dateTimeString), teks view sudah sesuai`() {
        runTest {
            val view = TextView(appContext)
            val dateTimeString = "2022-07-01 13:16:13"
            val expectedValue = "01 Jul 2022 13:16:13"
            formatStringToTanggalDanJam(view, dateTimeString)
            Truth.assertThat(view.text.toString()).isEqualTo(expectedValue)
            formatStringToTanggalDanJam(view, "")
            Truth.assertThat(view.text.toString()).isEqualTo("-")
            formatStringToTanggalDanJam(view, null)
            Truth.assertThat(view.text.toString()).isEqualTo("-")
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun formatTanggalDanJam(view, dateTime), teks view sudah sesuai`() {
        runTest {
            val view = TextView(appContext)
            val dateTimeString = "2022-07-01 13:16:13"
            val dateTime = DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
            val expectedValue = "01 Jul 2022 13:16:13"
            formatTanggalDanJam(view, dateTime)
            Truth.assertThat(view.text.toString()).isEqualTo(expectedValue)
            formatTanggalDanJam(view, null)
            Truth.assertThat(view.text.toString()).isEqualTo("-")
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun formatTanggalDanJam(dateTime), nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val dateTime = DateTime.parse(dateTimeString, DateTimeFormat.forPattern(DATETIME_FORMAT))
            val expectedValue = "01 Jul 2022 13:16:13"
            Truth.assertThat(formatTanggalDanJam(dateTime)).isEqualTo(expectedValue)
            Truth.assertThat(formatTanggalDanJam(dateTime = null)).isEqualTo("")
        }
    }

    @Test
    fun `Testing IndoDateFormatter fun formatStringToTanggalDanJam(dateTimeString), nilai balikannya sudah sesuai`() {
        runTest {
            val dateTimeString = "2022-07-01 13:16:13"
            val expectedValue = "01 Jul 2022 13:16:13"
            Truth.assertThat(formatStringToTanggalDanJam(dateTimeString)).isEqualTo(expectedValue)
            Truth.assertThat(formatStringToTanggalDanJam(dateTimeString = "")).isEqualTo("")
            Truth.assertThat(formatStringToTanggalDanJam(dateTimeString = null)).isEqualTo("")
        }
    }

    @Test
    fun `Testing Common fun durasi(menit), nilai balikannya sudah sesuai`() {
        runTest {
            val menit1 = 3600
            val expectedValue1 = "2 hari 12 jam"
            val menit2 = 3615
            val expectedValue2 = "2 hari 12 jam 15 menit"
            val menit3 = 515
            val expectedValue3 = "8 jam 35 menit"
            val menit4 = 540
            val expectedValue4 = "9 jam"
            val menit5 = 15
            val expectedValue5 = "15 menit"
            val menit6 = 0
            val expectedValue6 = "0 menit"
            Truth.assertThat(durasi(menit1)).isEqualTo(expectedValue1)
            Truth.assertThat(durasi(menit2)).isEqualTo(expectedValue2)
            Truth.assertThat(durasi(menit3)).isEqualTo(expectedValue3)
            Truth.assertThat(durasi(menit4)).isEqualTo(expectedValue4)
            Truth.assertThat(durasi(menit5)).isEqualTo(expectedValue5)
            Truth.assertThat(durasi(menit6)).isEqualTo(expectedValue6)
        }
    }

    override fun isMockServerEnabled(): Boolean = true
}