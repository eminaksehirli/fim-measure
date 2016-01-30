package mime.plain.measure.assoc;

import org.junit.Before;
import org.junit.Test;

import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.Transaction;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public abstract class RuleMeasureTestBase {

	private static int[][] theDb = new int[][] { { 0, 1, 2, 3, 4 },
			{ 0, 1, 2, 4, 5 }, { 0, 1, 5, 6, 7 }, { 1, 2, 4, 5, 7 },
			{ 1, 3, 5, 6, 7 } };

	protected TransactionDB db;
	protected Itemset sEmpty, s0, s01, s012, s47, s56;

	@Before
	public void setUp() throws Exception {
		db = new TransactionDB();

		for (int[] transaction : theDb) {
			Transaction tx = new Transaction();
			for (int id : transaction) {
				tx.add(db.getItem(id));
			}
			db.add(tx);
		}

		sEmpty = getSet(new int[] {});
		s0 = getSet(new int[] { 0 });
		s01 = getSet(new int[] { 0, 1 });
		s012 = getSet(new int[] { 0, 1, 2 });
		s47 = getSet(new int[] { 4, 7 });
		s56 = getSet(new int[] { 5, 6 });
	}

	private Itemset getSet(int[] items) {
		Itemset set = new Itemset();
		for (int item : items) {
			set.add(db.getItem(item));
		}
		return set;
	}

	@Test
	public abstract void test_empty_empty();

	@Test
	public abstract void test_empty_set();

	@Test
	public abstract void test_set_empty();

	@Test
	public abstract void test_set_set_equal();

	@Test
	public abstract void test_set_set_closed();

	@Test
	public abstract void test_set_set_include();

	@Test
	public abstract void test_set_set_overlap();

	@Test
	public abstract void test_set_set_no_overlap();

}