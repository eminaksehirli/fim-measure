package be.uantwerpen.adrem.fim.measure.tool;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_AB;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A_GIVEN_B;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A_GIVEN_NOT_B;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A_OR_B;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_B;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_GIVEN_NOT_B;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_NOT_B;
import static java.lang.Double.NaN;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.Transaction;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * 
 * @author Sandy Moens
 * @author M. Emin Aksehirli
 * 
 */
public class ProbabilityTest
{
	private static double delta = 0.000000001;

	private static int[][] theDb = new int[][]
	{
	{ 0, 1, 2, 3, 4 },
	{ 0, 1, 2, 4, 5 },
	{ 0, 1, 5, 6, 7 },
	{ 1, 2, 4, 5, 7 },
	{ 1, 3, 5, 6, 7 } };

	private TransactionDB db;
	private int size;
	private Itemset sEmpty, s0, s01, s012, s47, s56;

	@Before
	public void setUp()
	{
		db = new TransactionDB();

		for (int[] transaction : theDb)
		{
			Transaction tx = new Transaction();
			for (int id : transaction)
			{
				tx.add(db.getItem(id));
			}
			db.add(tx);
		}

		size = db.getNumberOfTransactions();

		sEmpty = getSet(new int[] {});
		s0 = getSet(new int[]
		{ 0 });
		s01 = getSet(new int[]
		{ 0, 1 });
		s012 = getSet(new int[]
		{ 0, 1, 2 });
		s47 = getSet(new int[]
		{ 4, 7 });
		s56 = getSet(new int[]
		{ 5, 6 });
	}

	private Itemset getSet(int[] items)
	{
		Itemset set = new Itemset();
		for (int item : items)
		{
			set.add(db.getItem(item));
		}
		return set;
	}

	@Test
	public void testP_A()
	{
		assertEquals(1.0, P_A(sEmpty, size), delta);
		assertEquals(3.0 / size, P_A(s0, size), delta);
		assertEquals(3.0 / size, P_A(s01, size), delta);
		assertEquals(2.0 / size, P_A(s012, size), delta);
		assertEquals(1.0 / size, P_A(s47, size), delta);
		assertEquals(2.0 / size, P_A(s56, size), delta);
	}

	@Test
	public void testP_NOT_A()
	{
		assertEquals(0.0, P_NOT_A(sEmpty, size), delta);
		assertEquals(1.0 - 3.0 / size, P_NOT_A(s0, size), delta);
		assertEquals(1.0 - 3.0 / size, P_NOT_A(s01, size), delta);
		assertEquals(1.0 - 2.0 / size, P_NOT_A(s012, size), delta);
		assertEquals(1.0 - 1.0 / size, P_NOT_A(s47, size), delta);
		assertEquals(1.0 - 2.0 / size, P_NOT_A(s56, size), delta);
	}

	@Test
	public void testP_AB()
	{
		assertEquals(1.0, P_AB(sEmpty, sEmpty, size), delta);
		assertEquals(3.0 / size, P_AB(sEmpty, s0, size), delta);
		assertEquals(3.0 / size, P_AB(sEmpty, s01, size), delta);
		assertEquals(2.0 / size, P_AB(sEmpty, s012, size), delta);
		assertEquals(1.0 / size, P_AB(sEmpty, s47, size), delta);
		assertEquals(2.0 / size, P_AB(sEmpty, s56, size), delta);

		assertEquals(3.0 / size, P_AB(s0, sEmpty, size), delta);
		assertEquals(3.0 / size, P_AB(s0, s0, size), delta);
		assertEquals(3.0 / size, P_AB(s0, s01, size), delta);
		assertEquals(2.0 / size, P_AB(s0, s012, size), delta);
		assertEquals(0.0, P_AB(s0, s47, size), delta);
		assertEquals(1.0 / size, P_AB(s0, s56, size), delta);

		assertEquals(3.0 / size, P_AB(s01, sEmpty, size), delta);
		assertEquals(3.0 / size, P_AB(s01, s0, size), delta);
		assertEquals(3.0 / size, P_AB(s01, s01, size), delta);
		assertEquals(2.0 / size, P_AB(s01, s012, size), delta);
		assertEquals(0.0, P_AB(s01, s47, size), delta);
		assertEquals(1.0 / size, P_AB(s01, s56, size), delta);

		assertEquals(2.0 / size, P_AB(s012, sEmpty, size), delta);
		assertEquals(2.0 / size, P_AB(s012, s0, size), delta);
		assertEquals(2.0 / size, P_AB(s012, s01, size), delta);
		assertEquals(2.0 / size, P_AB(s012, s012, size), delta);
		assertEquals(0.0, P_AB(s012, s47, size), delta);
		assertEquals(0.0, P_AB(s012, s56, size), delta);

		assertEquals(1.0 / size, P_AB(s47, sEmpty, size), delta);
		assertEquals(0.0, P_AB(s47, s0, size), delta);
		assertEquals(0.0, P_AB(s47, s01, size), delta);
		assertEquals(0.0, P_AB(s47, s012, size), delta);
		assertEquals(1.0 / size, P_AB(s47, s47, size), delta);
		assertEquals(0.0 / size, P_AB(s47, s56, size), delta);

		assertEquals(2.0 / size, P_AB(s56, sEmpty, size), delta);
		assertEquals(1.0 / size, P_AB(s56, s0, size), delta);
		assertEquals(1.0 / size, P_AB(s56, s01, size), delta);
		assertEquals(0.0, P_AB(s56, s012, size), delta);
		assertEquals(0.0, P_AB(s56, s47, size), delta);
		assertEquals(2.0 / size, P_AB(s56, s56, size), delta);
	}

	@Test
	public void testP_NOT_A_B()
	{
		assertEquals(0.0, P_NOT_A_B(sEmpty, sEmpty, size), delta);
		assertEquals(0.0, P_NOT_A_B(sEmpty, s0, size), delta);
		assertEquals(0.0, P_NOT_A_B(sEmpty, s01, size), delta);
		assertEquals(0.0, P_NOT_A_B(sEmpty, s012, size), delta);
		assertEquals(0.0, P_NOT_A_B(sEmpty, s47, size), delta);
		assertEquals(0.0, P_NOT_A_B(sEmpty, s56, size), delta);

		assertEquals(2.0 / size, P_NOT_A_B(s0, sEmpty, size), delta);
		assertEquals(0.0, P_NOT_A_B(s0, s0, size), delta);
		assertEquals(0.0, P_NOT_A_B(s0, s01, size), delta);
		assertEquals(0.0, P_NOT_A_B(s0, s012, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s0, s47, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s0, s56, size), delta);

		assertEquals(2.0 / size, P_NOT_A_B(s01, sEmpty, size), delta);
		assertEquals(0.0, P_NOT_A_B(s01, s0, size), delta);
		assertEquals(0.0, P_NOT_A_B(s01, s01, size), delta);
		assertEquals(0.0, P_NOT_A_B(s01, s012, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s01, s47, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s01, s56, size), delta);

		assertEquals(3.0 / size, P_NOT_A_B(s012, sEmpty, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s012, s0, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s012, s01, size), delta);
		assertEquals(0.0, P_NOT_A_B(s012, s012, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s012, s47, size), delta);
		assertEquals(2.0 / size, P_NOT_A_B(s012, s56, size), delta);

		assertEquals(4.0 / size, P_NOT_A_B(s47, sEmpty, size), delta);
		assertEquals(3.0 / size, P_NOT_A_B(s47, s0, size), delta);
		assertEquals(3.0 / size, P_NOT_A_B(s47, s01, size), delta);
		assertEquals(2.0 / size, P_NOT_A_B(s47, s012, size), delta);
		assertEquals(0.0, P_NOT_A_B(s47, s47, size), delta);
		assertEquals(2.0 / size, P_NOT_A_B(s47, s56, size), delta);

		assertEquals(3.0 / size, P_NOT_A_B(s56, sEmpty, size), delta);
		assertEquals(2.0 / size, P_NOT_A_B(s56, s0, size), delta);
		assertEquals(2.0 / size, P_NOT_A_B(s56, s01, size), delta);
		assertEquals(2.0 / size, P_NOT_A_B(s56, s012, size), delta);
		assertEquals(1.0 / size, P_NOT_A_B(s56, s47, size), delta);
		assertEquals(0.0, P_NOT_A_B(s56, s56, size), delta);
	}

	@Test
	public void testP_NOT_A_NOT_B()
	{
		assertEquals(0.0, P_NOT_A_NOT_B(sEmpty, sEmpty, size), delta);
		assertEquals(0.0, P_NOT_A_NOT_B(sEmpty, s0, size), delta);
		assertEquals(0.0, P_NOT_A_NOT_B(sEmpty, s01, size), delta);
		assertEquals(0.0, P_NOT_A_NOT_B(sEmpty, s012, size), delta);
		assertEquals(0.0, P_NOT_A_NOT_B(sEmpty, s47, size), delta);
		assertEquals(0.0, P_NOT_A_NOT_B(sEmpty, s56, size), delta);

		assertEquals(0.0, P_NOT_A_NOT_B(s0, sEmpty, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s0, s0, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s0, s01, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s0, s012, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s0, s47, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s0, s56, size), delta);

		assertEquals(0.0, P_NOT_A_NOT_B(s01, sEmpty, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s01, s0, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s01, s01, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s01, s012, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s01, s47, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s01, s56, size), delta);

		assertEquals(0.0, P_NOT_A_NOT_B(s012, sEmpty, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s012, s0, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s012, s01, size), delta);
		assertEquals(3.0 / size, P_NOT_A_NOT_B(s012, s012, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s012, s47, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s012, s56, size), delta);

		assertEquals(0.0 / size, P_NOT_A_NOT_B(s47, sEmpty, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s47, s0, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s47, s01, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s47, s012, size), delta);
		assertEquals(4.0 / size, P_NOT_A_NOT_B(s47, s47, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s47, s56, size), delta);

		assertEquals(0.0, P_NOT_A_NOT_B(s56, sEmpty, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s56, s0, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s56, s01, size), delta);
		assertEquals(1.0 / size, P_NOT_A_NOT_B(s56, s012, size), delta);
		assertEquals(2.0 / size, P_NOT_A_NOT_B(s56, s47, size), delta);
		assertEquals(3.0 / size, P_NOT_A_NOT_B(s56, s56, size), delta);
	}

	@Test
	public void testP_A_GIVEN_B()
	{
		assertEquals(1.0, P_A_GIVEN_B(sEmpty, sEmpty, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(sEmpty, s0, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(sEmpty, s01, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(sEmpty, s012, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(sEmpty, s47, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(sEmpty, s56, size), delta);

		assertEquals(3.0 / size, P_A_GIVEN_B(s0, sEmpty, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s0, s0, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s0, s01, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s0, s012, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s0, s47, size), delta);
		assertEquals(1.0 / 2.0, P_A_GIVEN_B(s0, s56, size), delta);

		assertEquals(3.0 / size, P_A_GIVEN_B(s01, sEmpty, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s01, s0, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s01, s01, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s01, s012, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s01, s47, size), delta);
		assertEquals(1.0 / 2.0, P_A_GIVEN_B(s01, s56, size), delta);

		assertEquals(2.0 / size, P_A_GIVEN_B(s012, sEmpty, size), delta);
		assertEquals(2.0 / 3.0, P_A_GIVEN_B(s012, s0, size), delta);
		assertEquals(2.0 / 3.0, P_A_GIVEN_B(s012, s01, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s012, s012, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s012, s47, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s012, s56, size), delta);

		assertEquals(1.0 / size, P_A_GIVEN_B(s47, sEmpty, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s47, s0, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s47, s01, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s47, s012, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s47, s47, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s47, s56, size), delta);

		assertEquals(2.0 / size, P_A_GIVEN_B(s56, sEmpty, size), delta);
		assertEquals(1.0 / 3.0, P_A_GIVEN_B(s56, s0, size), delta);
		assertEquals(1.0 / 3.0, P_A_GIVEN_B(s56, s01, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s56, s012, size), delta);
		assertEquals(0.0, P_A_GIVEN_B(s56, s47, size), delta);
		assertEquals(1.0, P_A_GIVEN_B(s56, s56, size), delta);
	}

	@Test
	public void testP_A_GIVEN_NOT_B()
	{
		assertEquals(NaN, P_A_GIVEN_NOT_B(sEmpty, sEmpty, size), delta);
		assertEquals(1.0, P_A_GIVEN_NOT_B(sEmpty, s0, size), delta);
		assertEquals(1.0, P_A_GIVEN_NOT_B(sEmpty, s01, size), delta);
		assertEquals(1.0, P_A_GIVEN_NOT_B(sEmpty, s012, size), delta);
		assertEquals(1.0, P_A_GIVEN_NOT_B(sEmpty, s47, size), delta);
		assertEquals(1.0, P_A_GIVEN_NOT_B(sEmpty, s56, size), delta);

		assertEquals(NaN, P_A_GIVEN_NOT_B(s0, sEmpty, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s0, s0, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s0, s01, size), delta);
		assertEquals(1.0 / 3.0, P_A_GIVEN_NOT_B(s0, s012, size), delta);
		assertEquals(3.0 / 4.0, P_A_GIVEN_NOT_B(s0, s47, size), delta);
		assertEquals(2.0 / 3.0, P_A_GIVEN_NOT_B(s0, s56, size), delta);

		assertEquals(NaN, P_A_GIVEN_NOT_B(s01, sEmpty, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s01, s0, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s01, s01, size), delta);
		assertEquals(1.0 / 3.0, P_A_GIVEN_NOT_B(s01, s012, size), delta);
		assertEquals(3.0 / 4.0, P_A_GIVEN_NOT_B(s01, s47, size), delta);
		assertEquals(2.0 / 3.0, P_A_GIVEN_NOT_B(s01, s56, size), delta);

		assertEquals(NaN, P_A_GIVEN_NOT_B(s012, sEmpty, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s012, s0, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s012, s01, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s012, s012, size), delta);
		assertEquals(2.0 / 4.0, P_A_GIVEN_NOT_B(s012, s47, size), delta);
		assertEquals(2.0 / 3.0, P_A_GIVEN_NOT_B(s012, s56, size), delta);

		assertEquals(NaN, P_A_GIVEN_NOT_B(s47, sEmpty, size), delta);
		assertEquals(1.0 / 2.0, P_A_GIVEN_NOT_B(s47, s0, size), delta);
		assertEquals(1.0 / 2.0, P_A_GIVEN_NOT_B(s47, s01, size), delta);
		assertEquals(1.0 / 3.0, P_A_GIVEN_NOT_B(s47, s012, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s47, s47, size), delta);
		assertEquals(1.0 / 3.0, P_A_GIVEN_NOT_B(s47, s56, size), delta);

		assertEquals(NaN, P_A_GIVEN_NOT_B(s56, sEmpty, size), delta);
		assertEquals(1.0 / 2.0, P_A_GIVEN_NOT_B(s56, s0, size), delta);
		assertEquals(1.0 / 2.0, P_A_GIVEN_NOT_B(s56, s01, size), delta);
		assertEquals(2.0 / 3.0, P_A_GIVEN_NOT_B(s56, s012, size), delta);
		assertEquals(2.0 / 4.0, P_A_GIVEN_NOT_B(s56, s47, size), delta);
		assertEquals(0.0, P_A_GIVEN_NOT_B(s56, s56, size), delta);
	}

	@Test
	public void testP_NOT_A_GIVEN_NOT_B()
	{
		assertEquals(NaN, P_NOT_A_GIVEN_NOT_B(sEmpty, sEmpty, size), delta);
		assertEquals(0.0, P_NOT_A_GIVEN_NOT_B(sEmpty, s0, size), delta);
		assertEquals(0.0, P_NOT_A_GIVEN_NOT_B(sEmpty, s01, size), delta);
		assertEquals(0.0, P_NOT_A_GIVEN_NOT_B(sEmpty, s012, size), delta);
		assertEquals(0.0, P_NOT_A_GIVEN_NOT_B(sEmpty, s47, size), delta);
		assertEquals(0.0, P_NOT_A_GIVEN_NOT_B(sEmpty, s56, size), delta);

		assertEquals(NaN, P_NOT_A_GIVEN_NOT_B(s0, sEmpty, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s0, s0, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s0, s01, size), delta);
		assertEquals(2.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s0, s012, size), delta);
		assertEquals(1.0 / 4.0, P_NOT_A_GIVEN_NOT_B(s0, s47, size), delta);
		assertEquals(1.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s0, s56, size), delta);

		assertEquals(NaN, P_NOT_A_GIVEN_NOT_B(s01, sEmpty, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s01, s0, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s01, s01, size), delta);
		assertEquals(2.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s01, s012, size), delta);
		assertEquals(1.0 / 4.0, P_NOT_A_GIVEN_NOT_B(s01, s47, size), delta);
		assertEquals(1.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s01, s56, size), delta);

		assertEquals(NaN, P_NOT_A_GIVEN_NOT_B(s012, sEmpty, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s012, s0, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s012, s01, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s012, s012, size), delta);
		assertEquals(2.0 / 4.0, P_NOT_A_GIVEN_NOT_B(s012, s47, size), delta);
		assertEquals(1.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s012, s56, size), delta);

		assertEquals(NaN, P_NOT_A_GIVEN_NOT_B(s47, sEmpty, size), delta);
		assertEquals(1.0 / 2.0, P_NOT_A_GIVEN_NOT_B(s47, s0, size), delta);
		assertEquals(1.0 / 2.0, P_NOT_A_GIVEN_NOT_B(s47, s01, size), delta);
		assertEquals(2.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s47, s012, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s47, s47, size), delta);
		assertEquals(2.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s47, s56, size), delta);

		assertEquals(NaN, P_NOT_A_GIVEN_NOT_B(s56, sEmpty, size), delta);
		assertEquals(1.0 / 2.0, P_NOT_A_GIVEN_NOT_B(s56, s0, size), delta);
		assertEquals(1.0 / 2.0, P_NOT_A_GIVEN_NOT_B(s56, s01, size), delta);
		assertEquals(1.0 / 3.0, P_NOT_A_GIVEN_NOT_B(s56, s012, size), delta);
		assertEquals(2.0 / 4.0, P_NOT_A_GIVEN_NOT_B(s56, s47, size), delta);
		assertEquals(1.0, P_NOT_A_GIVEN_NOT_B(s56, s56, size), delta);
	}

	@Test
	public void testP_A_OR_B()
	{
		assertEquals(1.0, P_A_OR_B(sEmpty, sEmpty, size), delta);
		assertEquals(1.0, P_A_OR_B(sEmpty, s0, size), delta);
		assertEquals(1.0, P_A_OR_B(sEmpty, s01, size), delta);
		assertEquals(1.0, P_A_OR_B(sEmpty, s012, size), delta);
		assertEquals(1.0, P_A_OR_B(sEmpty, s47, size), delta);
		assertEquals(1.0, P_A_OR_B(sEmpty, s56, size), delta);

		assertEquals(1.0, P_A_OR_B(s0, sEmpty, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s0, s0, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s0, s01, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s0, s012, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s0, s47, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s0, s56, size), delta);

		assertEquals(1.0, P_A_OR_B(s01, sEmpty, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s01, s0, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s01, s01, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s01, s012, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s01, s47, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s01, s56, size), delta);

		assertEquals(1.0, P_A_OR_B(s012, sEmpty, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s012, s0, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s012, s01, size), delta);
		assertEquals(2.0 / 5.0, P_A_OR_B(s012, s012, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s012, s47, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s012, s56, size), delta);

		assertEquals(1.0, P_A_OR_B(s47, sEmpty, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s47, s0, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s47, s01, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s47, s012, size), delta);
		assertEquals(1.0 / 5.0, P_A_OR_B(s47, s47, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s47, s56, size), delta);

		assertEquals(1.0, P_A_OR_B(s56, sEmpty, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s56, s0, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s56, s01, size), delta);
		assertEquals(4.0 / 5.0, P_A_OR_B(s56, s012, size), delta);
		assertEquals(3.0 / 5.0, P_A_OR_B(s56, s47, size), delta);
		assertEquals(2.0 / 5.0, P_A_OR_B(s56, s56, size), delta);
	}
}
