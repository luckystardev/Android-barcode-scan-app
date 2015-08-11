package com.aperfectpc.nuera_id.barcode.reader;

import android.test.ActivityInstrumentationTestCase2;

public class NuEraAppTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public NuEraAppTest() {
        super(MainActivity.class);
    }

    public void testActivityTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }
}
