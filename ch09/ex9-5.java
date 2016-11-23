// 예제 9.5  장시간 실행되는 작업의 결과를 화면에 표시하는 코드

button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
	button.setEnabled(false);
	label.setText("busy");
	backgroundExec.execute(new Runnable() {
	    public void run() {
		try {
		    doBigComputation();
		} finally {
		    GuiExecutor.instance().execute(new Runnable() {
			public void run() {
			    button.setEnabled(true);
			    label.setText("idle");
			}
		    });
		}
	    }
	});
    }
});
