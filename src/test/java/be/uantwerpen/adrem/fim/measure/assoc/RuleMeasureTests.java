package mime.plain.measure.assoc;

import mime.plain.measure.assoc.asymmetric.AsymmetricRuleMeasureTests;
import mime.plain.measure.assoc.symmetric.SymmetricRuleMeasureTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AsymmetricRuleMeasureTests.class,
		SymmetricRuleMeasureTests.class })
public class RuleMeasureTests {

	public static double delta = 0.000000001;

}
