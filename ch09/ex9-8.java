// 예제 9.8  BackgroundTask를 활용해 장시간 실행되며 중단 가능한 작업 실행

startButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
	class CancelListener implements ActionListener {
	    BackgroundTask<?> task;
	    public void actionPerformed(ActionEvent event) {
		if (task != null)
		    task.cancel(true);
	    }
	}
	final CancelListener listener = new CancelListener();
	listener.task = new BackgroundTask<Void>() {
	    public Void compute() {
		while (moreWork() && !isCancelled())
		    doSomeWork();
		return null;
	    }
	    public void onCompletion(boolean cancelled, String s,
				     Throwable exception) {
		cancelButton.removeActionListener(listener);
		label.setText("done");
	    }
	};
	cancelButton.addActionListener(listener);
	backgroundExec.excute(listener.task);
    }
});
