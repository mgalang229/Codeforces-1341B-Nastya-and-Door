import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

- peak: a[i-1] < a[i] and a[i] > a[i+1]
- for a chosen [l, r] segment, l and r are not peaks
- door will break into (p+1) parts, where p = # of peaks in a chosen segment of length k

8 6
	v	  v
1 2 3 4 5 6 7 8 -> i
1 2 4 1 2 4 1 2 -> a[i]
0 0 1 1 1 2 2 2 -> p

[2, 7]
[l, l+k-1]
start at (k-1)
check a[l] and a[r] if they're peaks
goal: get max # of peaks and min value of l

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), k = fs.nextInt();
			int[] a = fs.readArray(n);
			int[] peaks = new int[n];
			Arrays.fill(peaks, 0);
			for (int i = 1; i < n; i++) {
				peaks[i] = peaks[i-1];
				if (i + 1 < n && a[i-1] < a[i] && a[i] > a[i+1]) {
					peaks[i]++;
				}
			}
			int max = 0;
			for (int i = k - 1; i < n; i++) {
				int l = i - k + 1;
				int numPeaks = peaks[i] - peaks[l];
				if (i - 1 >= 0 && i + 1 < n && a[i-1] < a[i] && a[i] > a[i+1]) {
					numPeaks--;
				}
				if (l - 1 >= 0 && l + 1 < n && a[l-1] < a[l] && a[l] > a[l+1]) {
					numPeaks--;
				}
				max = Math.max(max, numPeaks + 1);
			}
			int min = Integer.MAX_VALUE;
			for (int i = k - 1; i < n; i++) {
				int l = i - k + 1;
				int numPeaks = peaks[i] - peaks[l];
				if (i - 1 >= 0 && i + 1 < n && a[i-1] < a[i] && a[i] > a[i+1]) {
					numPeaks--;
				}
				if (l - 1 >= 0 && l + 1 < n && a[l-1] < a[l] && a[l] > a[l+1]) {
					numPeaks--;
				}
				if (numPeaks + 1 == max) {
					min = Math.min(min, l + 1);
				}
			}
			System.out.println(max + " " + min);
		}
		out.close();
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
