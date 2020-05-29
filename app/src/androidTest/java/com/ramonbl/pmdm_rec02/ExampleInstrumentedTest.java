package com.ramonbl.pmdm_rec02;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <Act_UsuarioAnonimo href="http://d.android.com/tools/testing">Testing documentation</Act_UsuarioAnonimo>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

		assertEquals("com.ramonbl.pmdm_rec02", appContext.getPackageName());
	}
}
