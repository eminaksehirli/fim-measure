package mime.plain.tool;

import java.util.BitSet;

public class BitSetUtils {
	public static BitSet intersect(BitSet t1, BitSet t2) {
		BitSet i;

		if (t1.cardinality() < t2.cardinality()) {
			i = (BitSet) t1.clone();
			i.and(t2);
		} else {
			i = (BitSet) t2.clone();
			i.and(t1);
		}
		return i;
	}
}
