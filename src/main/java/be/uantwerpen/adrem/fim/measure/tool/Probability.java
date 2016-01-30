package be.uantwerpen.adrem.fim.measure.tool;

import java.util.BitSet;

import be.uantwerpen.adrem.fim.model.Itemset;

/**
 * Probabilistic Measures
 * 
 * @author Sandy Moens
 */
public class Probability {

	public static double P_A(Itemset A, int dbSize) {
		if (A.isEmpty()) {
			return 1.0;
		}
		return (1.0 * A.getTIDs().cardinality()) / dbSize;
	}

	public static double P_NOT_A(Itemset A, int dbSize) {
		return 1.0 - P_A(A, dbSize);
	}

	public static double P_AB(Itemset A, Itemset B, int dbSize) {
		if (A.isEmpty() && B.isEmpty()) {
			return 1;
		} else if (A.isEmpty()) {
			return P_A(B, dbSize);
		} else if (B.isEmpty()) {
			return P_A(A, dbSize);
		}
		Itemset intersection = new Itemset(A);
		intersection.addAll(B);
		return P_A(intersection, dbSize);
	}

	public static double P_NOT_A_B(Itemset A, Itemset B, int dbSize) {
		if (A.isEmpty()) {
			return 0.0;
		}
		if (B.isEmpty()) {
			return P_NOT_A(A, dbSize);
		}
		BitSet bits = (BitSet) B.getTIDs().clone();
		bits.andNot(A.getTIDs());
		return (1.0 * bits.cardinality()) / dbSize;
	}

	public static double P_NOT_A_NOT_B(Itemset A, Itemset B,
			int dbSize) {
		if (A.isEmpty() || B.isEmpty()) {
			return 0.0;
		}

		BitSet bits = (BitSet) A.getTIDs().clone();
		bits.or(B.getTIDs());
		return 1.0 * (dbSize - bits.cardinality()) / dbSize;
	}

	public static double P_A_GIVEN_B(Itemset A, Itemset B, int dbSize) {
		return P_AB(A, B, dbSize) / P_A(B, dbSize);
	}

	public static double P_A_GIVEN_NOT_B(Itemset A, Itemset B,
			int dbSize) {
		return P_NOT_A_B(B, A, dbSize) / P_NOT_A(B, dbSize);
	}

	public static double P_NOT_A_GIVEN_NOT_B(Itemset A, Itemset B,
			int dbSize) {
		return P_NOT_A_NOT_B(A, B, dbSize) / P_NOT_A(B, dbSize);
	}

	public static double P_A_OR_B(Itemset A, Itemset B, int dbSize) {
		if (A.isEmpty() || B.isEmpty()) {
			return 1.0;
		}

		BitSet bits = (BitSet) A.getTIDs().clone();
		bits.or(B.getTIDs());
		return 1.0 * bits.cardinality() / dbSize;
	}
}
