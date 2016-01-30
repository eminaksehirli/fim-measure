package be.uantwerpen.adrem.tool;

import static java.util.Arrays.fill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility functions
 * 
 * @author Sandy Moens
 * @author Emin Aksehirli
 */
public class Utils {

	/**
	 * Computes the factorial of a positive integer
	 * 
	 * @param num
	 *            number of which the factorial is to be computed
	 * @return the factorial
	 */
	public static int factorial(int num) {
		if (num <= 1) {
			return 1;
		}
		int result = 1;
		for (int i = 2; i <= num; i++) {
			result *= i;
		}
		return result;
	}

	/**
	 * Computes the sum of a positive integer
	 * 
	 * @param num
	 *            number of which the sum is to be computed
	 * @return the sum
	 */
	public static int sum(int num) {
		if (num <= 1) {
			return 1;
		}
		return num * (num + 1) / 2;
	}

	/**
	 * Computes the combination of bottom items out of top choices
	 * 
	 * @param top
	 *            the number of items from which can be chosen
	 * @param bottom
	 *            the number of items that need to be chosen
	 * @return the number of possible combinations with given top and bottom
	 *         value
	 */
	public static double combination(double top, double bottom) {
		if (bottom > top)
			return 0;

		double r = (bottom > top - bottom ? bottom : top - bottom) + 1;
		if (bottom == top)
			return 1;

		double d = 2;
		for (double m = r + 1; m <= top; m++, d++) {
			r *= m;
			r /= d;
		}
		return r;
	}

	public static double combinationWithRepetition(double top, double bottom) {
		return combination(top + bottom - 1, bottom);
	}

	public static int countNumberOfLines(String fileName) {
		int size = 0;
		LineNumberReader lnr;
		try {
			lnr = new LineNumberReader(new FileReader(fileName));
			while (lnr.readLine() != null) {
			}
			size = lnr.getLineNumber();
			lnr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return size;
	}

	public static int logIndexSearch(double[] values, double toSearch) {
		double left = 0;
		double right = values.length;
		double split;

		if (right % 2 == 0) {
			while (right - left > 3) {
				split = (left + (right - left) / 2.0);
				if (values[(int) split] >= toSearch) {
					right = split;
				} else {
					left = split;
				}
			}
			int end = values.length - (int) left;
			for (int i = 0; i < end; i++) {
				if (values[(int) left + i] >= toSearch) {
					right = left + i;
					break;
				}
			}
		} else {
			while (right - left >= 1) {
				split = (left + (right - left) / 2.0);
				if (values[(int) split] >= toSearch) {
					right = split;
				} else {
					left = split;
				}
			}
		}
		return (int) (right);
	}

	public static int logIndexSearch(double[] values, double theEnd,
			double toSearch) {
		double left = 0;
		double right = theEnd;
		double split;

		if (right % 2 == 0) {
			while (right - left > 3) {
				split = (left + (right - left) / 2.0);
				if (values[(int) split] >= toSearch) {
					right = split;
				} else {
					left = split;
				}
			}
			int end = values.length - (int) left;
			for (int i = 0; i < end; i++) {
				if (values[(int) left + i] >= toSearch) {
					right = left + i;
					break;
				}
			}
		} else {
			while (right - left >= 1) {
				split = (left + (right - left) / 2.0);
				if (values[(int) split] >= toSearch) {
					right = split;
				} else {
					left = split;
				}
			}
		}
		return (int) (right);
	}

	public static double log(double x, double base) {
		return Math.log(x) / Math.log(base);
	}

	public static boolean checkFileExistance(String filePath) {
		return new File(filePath).exists();
	}

	/**
	 * Returns a range of int's between start (inclusive) and end (exclusive).
	 * 
	 * @param start
	 *            Starting number (inclusive)
	 * @param end
	 *            Ending number (exclusive)
	 * @param increment
	 *            Step size
	 * @return int array containing numbers between start and end incremented by
	 *         increment
	 */
	public static int[] range(int start, int end, int increment) {
		int[] range = new int[(int) Math.ceil((end - start)
				/ (double) increment)];

		int currentValue = start;
		for (int i = 0; i < range.length; i++) {
			range[i] = currentValue;
			currentValue += increment;
		}

		return range;
	}

	/**
	 * Returns a range of int's between start (inclusive) and end (exclusive).
	 * 
	 * @param start
	 *            Starting number (inclusive)
	 * @param end
	 *            Ending number (exclusive)
	 * @return int array containing numbers between start and end incremented by
	 *         1
	 */
	public static int[] range(int start, int end) {
		return range(start, end, 1);
	}

	/**
	 * Returns a range of int's between 0 (inclusive) and end (exclusive).
	 * 
	 * @param end
	 *            Ending number (exclusive)
	 * @return int array containing numbers between 0 and end incremented by 1
	 */
	public static int[] range(int end) {
		return range(0, end, 1);
	}

	public static int sum(Collection<Integer> numbers) {
		int sum = 0;
		for (int i : numbers) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Gives equal sized partitions for n. If n is not dividable by p, the last
	 * partition is smaller. Useful method for partitioning for
	 * Multi-processing.
	 * 
	 * @param n
	 *            Number to partition.
	 * @param p
	 *            Number of partitions.
	 * @return An p-sized int array that has the partition sizes.
	 */
	public static int[] partition(int n, int p) {
		final int shareSize = n / p;
		final int leftOver = n - (p - 1) * shareSize;

		final int[] shareSizes = new int[p];
		fill(shareSizes, shareSize);
		shareSizes[shareSizes.length - 1] = leftOver;
		return shareSizes;
	}

	public static List<String> convertToStringsList(List<?> list) {
		List<String> stringsList = new LinkedList<String>();
		for (Iterator<?> it = list.iterator(); it.hasNext();) {
			stringsList.add(it.next().toString());
		}
		return stringsList;
	}
}
