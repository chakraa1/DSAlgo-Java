package com.dsalgo.binarysearch;

/*Problem statement - 

Given 2 integers A and B and an array of integars C of size N.

Element C[i] represents length of ith board.

You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of board.

Calculate and return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board.

2 painters cannot share a board to paint. That is to say, a board
cannot be painted partially by one painter, and partially by another.
A painter will only paint contiguous boards. Which means a
configuration where painter 1 paints board 1 and 3 but not 2 is
invalid.
Return the ans % 10000003




Input Format

The first argument given is the integer A.
The second argument given is the integer B.
The third argument given is the integer array C.
Output Format

Return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board % 10000003.
Constraints

1 <=A <= 1000
1 <= B <= 10^6
1 <= C.size() <= 10^5
1 <= C[i] <= 10^6
For Example

Input 1:
    A = 2
    B = 5
    C = [1, 10]
Output 1:
    50
Explanation 1:
    Possibility 1:- same painter paints both blocks, time taken = 55units
    Possibility 2:- Painter 1 paints block 1, painter 2 paints block 2, time take = max(5, 50) = 50
    There are no other distinct ways to paint boards.
    ans = 50%10000003

Input 2:
    A = 10
    B = 1
    C = [1, 8, 11, 3]
Output 2:
    11

*/
import java.util.*;

class PaintersPartitionProblem {
	ArrayList<Integer> C;

	public int minTime(int A, int B, ArrayList<Integer> C) {

		this.C = new ArrayList<>();

		for (Integer integer : C) {
			this.C.add(B * integer); // We change the C array to hold time taken to paint each board
		}

		int lo = Collections.max(this.C); // Min time will be when each board is assigned to different worker

		int hi = this.C.stream().mapToInt(i -> i).sum(); // Max time will be when there is one worker

		if (predicate(lo, A))
			return lo;

		while (lo < hi - 1) {
			int mid = (lo + hi) / 2;
			if (predicate(mid, A))
				hi = mid;
			else
				lo = mid;
		}

		return hi;
	}

	/*
	 * The predicate function returns true if the number of workers needed to paint
	 * all the boards is less than equals A such that maximum time each worker works
	 * is max_time
	 */
	private boolean predicate(int max_time, int A) {
		int num_workers = numWorkers(max_time);
		return num_workers <= A;
	}

	/*
	 * numWorkers returns the minimum no of workers needed to paint all boards such
	 * that no worker works more than max_time
	 */
	private int numWorkers(int max_time) {
		// We do a greedy job allotment. Try giving the current worker as much as we can
		// and the use a new worker.
		int num_worker = 1;
		int curr_time = 0;
		for (Integer integer : this.C) {
			if ((curr_time + integer) <= max_time) {
				// Current worker can paint this board
				curr_time += integer;
			} else {
				// Current worker cannot take this board. We need to use a new worker
				num_worker += 1;
				curr_time = integer; // The new worker will paint this board
			}
		}
		return num_worker;
	}
	
	public static void main(String[] args) {

//		Scanner sc = new Scanner(System.in);
//		int A = sc.nextInt();
//		int B = sc.nextInt();
//
//		System.out.println(" Please enter list of board lengths (C): \n");
//		int N = sc.nextInt();
//		ArrayList<Integer> C = new ArrayList<>();
//		for (int i = 0; i < N; i++) {
//			int C_i = sc.nextInt();
//			C.add(C_i);
//		}
//		sc.close();
		int A=10;
		int B=1;
		ArrayList<Integer> C = new ArrayList<>();
		C.add(1);
		C.add(8);
		C.add(11);
		C.add(3);
		System.out.println("A --> " + A + " B --> " + B + " C --> " + C.toString());
		
		
		PaintersPartitionProblem sol = new PaintersPartitionProblem();
		System.out.println(sol.minTime(A, B, C));
		
	}
}
