package id.co.indocyber.basemodule

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class MainActivityTest {
    @Test
    fun clickDownload() {
        onView(withId(R.id.btn_download)).perform(click())
    }

}