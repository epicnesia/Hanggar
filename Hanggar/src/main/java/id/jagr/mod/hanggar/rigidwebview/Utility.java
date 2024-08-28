package id.jagr.mod.hanggar.rigidwebview;

import android.os.Handler;
import android.os.Looper;

class Utility {

    private static Handler sMainThreadHandler;

    /**
     * @return a {@link Handler} tied to the main thread.
     */
    public static Handler getMainThreadHandler() {
        if (sMainThreadHandler == null) {
            // No need to synchronize -- it's okay to create an extra Handler, which will be used
            // only once and then thrown away.
            sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
        return sMainThreadHandler;
    }
}
