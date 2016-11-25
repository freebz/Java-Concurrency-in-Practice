// 예제 11.2  아무런 의미가 없는 동기화 구문. 이런 코드는 금물!

synchronized (new Object()) {
    // 작업 진행
}
