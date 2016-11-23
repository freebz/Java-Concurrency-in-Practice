// 예제 9.4  화면에서 이벤트가 발생했을 때 장시간 실행되는 작업을 시작하는 방법

ExecutorService backgroundExec = Executors.newCachedThreadPool();
//...
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
	backgroundExec.execute(new Runnable() {
	    public void run() { doBigComputation(); }
	});
}});
