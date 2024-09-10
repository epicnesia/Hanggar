package id.jagr.mod.hanggar.base

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
abstract class BaseTest {

    lateinit var mockWebServer: MockWebServer

    @Before
    open fun setup() {
        this.configureMockServer()
    }

    @After
    open fun teardown() {
        this.stopMockServer()
    }

    // MOCK SERVER
    abstract fun isMockServerEnabled(): Boolean // Because we don't want it always enabled on all tests

    open fun configureMockServer() {
        if (isMockServerEnabled()) {
            mockWebServer = MockWebServer()
            mockWebServer.dispatcher = mockWebServerDispatcher
            mockWebServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()) {
            mockWebServer.shutdown()
        }
    }

    // RETROFIT CLIENT
//    fun getClient(baseUrl: HttpUrl): ApiService {
//        val apiService: ApiService
//        val buildHttpClient = getNewHttpClient()
//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(buildHttpClient)
//            .build()
//        apiService = retrofit.create(ApiService::class.java)
//        return apiService
//    }
//
//    fun getOauthClient(baseUrl: HttpUrl): OauthService {
//        val oauthService: OauthService
//        val buildHttpClient = getNewHttpClient()
//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(buildHttpClient)
//            .build()
//        oauthService = retrofit.create(OauthService::class.java)
//        return oauthService
//    }
//
//    private fun getNewHttpClient(): OkHttpClient {
//        val client = OkHttpClient.Builder()
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        client.addInterceptor(interceptor)
//        client
//            .followRedirects(true)
//            .followSslRedirects(true)
//            .retryOnConnectionFailure(true)
//            .cache(null)
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .writeTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//
//        return client.build()
//    }
//
//    fun provideDb(app: Context): AppDB {
//        return Room
//            .inMemoryDatabaseBuilder(app, AppDB::class.java)
//            .setTransactionExecutor(Executors.newSingleThreadExecutor())
//            .fallbackToDestructiveMigration()
//            .build()
//    }
}
