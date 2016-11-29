// 예제 14.1  상태 종속적인 작업의 동기화 구조

void blockingAction() throws InterruptedException {
    상태 변수에 대한 락 확보
    while (선행 조건이 만족하지 않음) {
	확보했던 락을 풀어줌
	선행 조건이 만족할만한 시간만큼 대기
	인터럽트에 걸리거나 타임아웃이 걸리면 멈춤
	락을 다시 확보
    }
    작업 실행
    락 해제
}
