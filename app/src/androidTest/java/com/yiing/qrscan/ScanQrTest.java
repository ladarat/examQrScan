package com.yiing.qrscan;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class ScanQrTest {
    @Rule
    public ActivityTestRule testRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void scanQrBBBB() {
        onView(withText("Hover to QR-code"));
        final Activity currentActivity = getActivityInstance();
        ((MainActivity) currentActivity).scanQr("00020101021230510016A000000677010112011509940009777019602030010301053037645802TH63041FF1");
        onView(withText("00020101021230510016A000000677010112011509940009777019602030010301053037645802TH63041FF1"));
    }

    private Activity currentActivity = null;
    public Activity getActivityInstance() {
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivities =
                        ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity activity : resumedActivities) {
                    currentActivity = activity;
                    break;
                }
            }
        });

        return currentActivity;
    }
}
