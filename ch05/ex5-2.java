// 예제 5.2  클라이언트 측 락을 활용해 getLast와 deleteLast를 동기화시킨 모습

public static Object getLast(Vector list) {
    synchronized (list) {
	int lastIndex = list.size() - 1;
	return list.get(lastIndex);
    }
}

public static void deleteLast(Vector list) {
    synchronized (list) {
	int lastIndex = list.size() - 1;
	list.remove(lastIndex);
    }
}
