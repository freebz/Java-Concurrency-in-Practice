// 예제 7.6  InterruptedException을 상위 메소드로 전달

BlockingQueue<Task> queue;
//...
public Task getNextTask() throws InterruptedException {
    return queue.take();
}
