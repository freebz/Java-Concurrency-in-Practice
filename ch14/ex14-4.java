// 예제 14.4  GrumpyBoundedBuffer를 호출하기 위한 호출자 측의 코드

while (true) {
    try {
	V item = buffer.take();
	// 값을 사용한다
	break;
    } catch (BufferEmptyExcepiton e) {
	Thread.sleep(SLEEP_GRANULARITY);
    }
}
