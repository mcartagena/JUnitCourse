package com.mcartagena.junit.suite;

import com.mcartagena.junit.helper.QuickBeforeAfterTest;
import com.mcartagena.junit.helper.StringHelperTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({QuickBeforeAfterTest.class, StringHelperTest.class})
@SuiteDisplayName("JUnit Platform Suite Demo")
public class DummyTestSuite {
}
