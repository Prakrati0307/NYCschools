package code.challange.a20190417_prakratisharma_nycschools;

import android.content.ComponentName;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import code.challange.a20190417_prakratisharma_nycschools.activities.SchoolDetailActivity;
import code.challange.a20190417_prakratisharma_nycschools.activities.SchoolListActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SchoolListInstrumentedTest {

    @Rule
    public ActivityTestRule<SchoolListActivity> activityTestRule = new ActivityTestRule<>(SchoolListActivity.class);

    @Before
    public void init(){
        Intents.init();
    }

    @Test
    public void checkListIsPopulated(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.schools_recycler_view)).check(new RecyclerViewItemsSizeAssertion());
    }

    @Test
    public void launchSchoolDetailsPageTest(){
        onView(withId(R.id.schools_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        intended(hasComponent(SchoolDetailActivity.class.getName()));
    }

    @After
    public void teardown(){
        Intents.release();
    }


}
