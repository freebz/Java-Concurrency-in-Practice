// 예제 9.6  장시간 실행되는 작업 중단하기

Future<?> runningTask = null;  // 스레드 한정
//...
startButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
	if (runningTask == null) {
	    runningTask = backgroundExec.submit(new Runnable() {
		public void run() {
		    while (moreWork()) {
			if (Thread.interrupted()) {
			    cleanUpPartialWork();
			    break;
			}
			doSomeWork();
		    }
		}
	    });	
    };
}});

cancelButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent event) {
	if (runningTask != null)
	    runningTask.cancel(true);
}});
