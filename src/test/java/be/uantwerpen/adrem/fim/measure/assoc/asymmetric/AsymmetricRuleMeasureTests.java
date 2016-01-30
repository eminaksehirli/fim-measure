package be.uantwerpen.adrem.fim.measure.assoc.asymmetric;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Sandy Moens
 */
@RunWith(Suite.class)
@SuiteClasses({ AccuracyMeasureTest.class, CertaintyFactorMeasureTest.class,
		ConfidenceMeasureTest.class, ConvictionMeasureTest.class,
		LeverageMeasureTest.class, RelativeRiskMeasureTest.class,
		SpecificityMeasureTest.class })
public class AsymmetricRuleMeasureTests {
}
