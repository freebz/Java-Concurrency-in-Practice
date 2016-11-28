// 예제 12.13  TimedPutTakeTest 실행 프로그램

public static void main(String[] args) throws Exception {
    int tpt = 100000; // 스레드별 실행 횟수
    for (int cap = 1; cap <= 1000; cap *= 10) {
	System.out.println("Capacity: "+ cap);
	for (int pairs = 1; pairs <= 128; pairs *= 2) {
	    TimedPutTakeTest t =
		new TimedPutTakeTest(cap, pairs, tpt);
	    System.out.print("Pairs: " + pairs + "\t");
	    t.test();
	    System.out.print("\t");
	    Thread.sleep(1000);
	    t.test();
	    System.out.println();
	    Thread.sleep(1000);
	}
    }
    pool.shutdown();
}
