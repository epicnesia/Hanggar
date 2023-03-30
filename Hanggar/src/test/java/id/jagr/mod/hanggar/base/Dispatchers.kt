package id.jagr.mod.hanggar.base

import id.jagr.mod.hanggar.base.MockWebServerUtil.enqueueResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

val mockWebServerDispatcher = object : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        when (request.path) {
            "/" -> return enqueueResponse("error-520.json", 520)
        }
        return MockResponse().setResponseCode(404)
    }
}