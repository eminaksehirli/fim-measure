package be.uantwerpen.adrem.fim.measure.assoc.symmetric;

import static be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureTests.delta;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureTestBase;

/**
 * @author Sandy Moens
 * @author Emin Aksehirli
 */
public class KulcinskyMeasureTest extends RuleMeasureTestBase {

	private KulcinskyMeasure m;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		m = new KulcinskyMeasure(db);
	}

	@Override
	@Test
	public void test_empty_empty() {
		assertEquals(1.0, m.evaluate(sEmpty, sEmpty), delta);
	}

	@Override
	@Test
	public void test_empty_set() {
		assertEquals(8.0 / 10.0, m.evaluate(sEmpty, s0), delta);
		assertEquals(8.0 / 10.0, m.evaluate(sEmpty, s01), delta);
		assertEquals(7.0 / 10.0, m.evaluate(sEmpty, s012), delta);
		assertEquals(6.0 / 10.0, m.evaluate(sEmpty, s47), delta);
		assertEquals(7.0 / 10.0, m.evaluate(sEmpty, s56), delta);
	}

	@Override
	@Test
	public void test_set_empty() {
		assertEquals(8.0 / 10.0, m.evaluate(s0, sEmpty), delta);
		assertEquals(8.0 / 10.0, m.evaluate(s01, sEmpty), delta);
		assertEquals(7.0 / 10.0, m.evaluate(s012, sEmpty), delta);
		assertEquals(6.0 / 10.0, m.evaluate(s47, sEmpty), delta);
		assertEquals(7.0 / 10.0, m.evaluate(s56, sEmpty), delta);
	}

	@Override
	@Test
	public void test_set_set_equal() {
		assertEquals(1.0, m.evaluate(s0, s0), delta);
		assertEquals(1.0, m.evaluate(s01, s01), delta);
		assertEquals(1.0, m.evaluate(s012, s012), delta);
		assertEquals(1.0, m.evaluate(s47, s47), delta);
		assertEquals(1.0, m.evaluate(s56, s56), delta);
	}

	@Override
	@Test
	public void test_set_set_closed() {
		assertEquals(1.0, m.evaluate(s0, s01), delta);
		assertEquals(1.0, m.evaluate(s01, s0), delta);
	}

	@Override
	@Test
	public void test_set_set_include() {
		assertEquals(5.0 / 6.0, m.evaluate(s0, s012), delta);
		assertEquals(5.0 / 6.0, m.evaluate(s01, s012), delta);

		assertEquals(5.0 / 6.0, m.evaluate(s012, s0), delta);
		assertEquals(5.0 / 6.0, m.evaluate(s012, s01), delta);
	}

	@Override
	@Test
	public void test_set_set_overlap() {
		assertEquals(5.0 / 12.0, m.evaluate(s0, s56), delta);

		assertEquals(5.0 / 12.0, m.evaluate(s01, s56), delta);

		assertEquals(5.0 / 12.0, m.evaluate(s56, s0), delta);
		assertEquals(5.0 / 12.0, m.evaluate(s56, s01), delta);
	}

	@Override
	@Test
	public void test_set_set_no_overlap() {
		assertEquals(0.0, m.evaluate(s0, s47), delta);

		assertEquals(0.0, m.evaluate(s01, s47), delta);

		assertEquals(0.0, m.evaluate(s012, s47), delta);
		assertEquals(0.0, m.evaluate(s012, s56), delta);

		assertEquals(0.0, m.evaluate(s47, s0), delta);
		assertEquals(0.0, m.evaluate(s47, s01), delta);
		assertEquals(0.0, m.evaluate(s47, s012), delta);
		assertEquals(0.0, m.evaluate(s47, s56), delta);

		assertEquals(0.0, m.evaluate(s56, s012), delta);
		assertEquals(0.0, m.evaluate(s56, s47), delta);
	}
}
