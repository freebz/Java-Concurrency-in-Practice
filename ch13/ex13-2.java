// 예제 13.2  ReentrantLock을 사용한 객체 동기화

Lock lock = new ReentrantLock();
//...
lock.lock();
try {
    // 객체 내부 값을 사용
    // 예외가 발생한 경우, 적절하게 내부 값을 복원해야 할 수도 있음
} finally {
    lock.unlock();
}
