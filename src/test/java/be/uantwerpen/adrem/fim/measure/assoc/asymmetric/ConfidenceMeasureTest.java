package be.uantwerpen.adrem.fim.measure.assoc.asymmetric;

import static be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureTests.delta;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureTestBase;

public class ConfidenceMeasureTest extends RuleMeasureTestBase {

	private ConfidenceMeasure m;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		m = new ConfidenceMeasure(db);
	}

	@Override
	@Test
	public void test_empty_empty() {
		Assert.assertEquals(1.0, m.evaluate(sEmpty, sEmpty), delta);
	}

	@Override
	@Test
	public void test_empty_set() {
		Assert.assertEquals(3.0 / 5.0, m.evaluate(sEmpty, s0), delta);
		Assert.assertEquals(3.0 / 5.0, m.evaluate(sEmpty, s01), delta);
		Assert.assertEquals(2.0 / 5.0, m.evaluate(sEmpty, s012), delta);
		Assert.assertEquals(1.0 / 5.0, m.evaluate(sEmpty, s47), delta);
		Assert.assertEquals(2.0 / 5.0, m.evaluate(sEmpty, s56), delta);
	}

	@Override
	@Test
	public void test_set_empty() {
		Assert.assertEquals(1.0, m.evaluate(s0, sEmpty), delta);
		Assert.assertEquals(1.0, m.evaluate(s01, sEmpty), delta);
		Assert.assertEquals(1.0, m.evaluate(s012, sEmpty), delta);
		Assert.assertEquals(1.0, m.evaluate(s47, sEmpty), delta);
		Assert.assertEquals(1.0, m.evaluate(s56, sEmpty), delta);

	}

	@Override
	@Test
	public void test_set_set_equal() {
		Assert.assertEquals(1.0, m.evaluate(s0, s0), delta);
		Assert.assertEquals(1.0, m.evaluate(s01, s01), delta);
		Assert.assertEquals(1.0, m.evaluate(s012, s012), delta);
		Assert.assertEquals(1.0, m.evaluate(s47, s47), delta);
		Assert.assertEquals(1.0, m.evaluate(s56, s56), delta);
	}

	@Override
	@Test
	public void test_set_set_closed() {
		Assert.assertEquals(1.0, m.evaluate(s0, s01), delta);
		Assert.assertEquals(1.0, m.evaluate(s01, s0), delta);
	}

	@Override
	@Test
	public void test_set_set_include() {
		Assert.assertEquals(2.0 / 3.0, m.evaluate(s0, s012), delta);
		Assert.assertEquals(2.0 / 3.0, m.evaluate(s01, s012), delta);

		Assert.assertEquals(1.0, m.evaluate(s012, s0), delta);
		Assert.assertEquals(1.0, m.evaluate(s012, s01), delta);
	}

	@Override
	@Test
	public void test_set_set_overlap() {
		Assert.assertEquals(1.0 / 3.0, m.evaluate(s0, s56), delta);

		Assert.assertEquals(1.0 / 3.0, m.evaluate(s01, s56), delta);

		Assert.assertEquals(1.0 / 2.0, m.evaluate(s56, s0), delta);
		Assert.assertEquals(1.0 / 2.0, m.evaluate(s56, s01), delta);
	}

	@Override
	@Test
	public void test_set_set_no_overlap() {
		Assert.assertEquals(0.0, m.evaluate(s0, s47), delta);

		Assert.assertEquals(0.0, m.evaluate(s01, s47), delta);

		Assert.assertEquals(0.0, m.evaluate(s012, s47), delta);
		Assert.assertEquals(0.0, m.evaluate(s012, s56), delta);

		Assert.assertEquals(0.0, m.evaluate(s47, s0), delta);
		Assert.assertEquals(0.0, m.evaluate(s47, s01), delta);
		Assert.assertEquals(0.0, m.evaluate(s47, s012), delta);
		Assert.assertEquals(0.0, m.evaluate(s47, s56), delta);

		Assert.assertEquals(0.0, m.evaluate(s56, s012), delta);
		Assert.assertEquals(0.0, m.evaluate(s56, s47), delta);
	}
}
