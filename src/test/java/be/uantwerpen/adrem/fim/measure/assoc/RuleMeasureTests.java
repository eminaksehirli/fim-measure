package be.uantwerpen.adrem.fim.measure.assoc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.uantwerpen.adrem.fim.measure.assoc.asymmetric.AsymmetricRuleMeasureTests;
import be.uantwerpen.adrem.fim.measure.assoc.symmetric.SymmetricRuleMeasureTests;

/**
 * @author Sandy Moens
 */
@RunWith(Suite.class)
@SuiteClasses({ AsymmetricRuleMeasureTests.class,
		SymmetricRuleMeasureTests.class })
public class RuleMeasureTests {

	public static double delta = 0.000000001;

}
