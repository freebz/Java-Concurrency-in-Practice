// 예제 5.4  클라이언트 측 락을 사용해 반복문을 동기화시킨 모습

synchronized (vector) {
    for (int i = 0; i < vector.size(); i++)
	doSomething(vector.get(i));
}
