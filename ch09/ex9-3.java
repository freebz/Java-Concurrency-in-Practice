// 예제 9.3  간단한 이벤트 리스너

final Random random = new Random();
final JButton button = new JButton("Change Color");
//...
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
	button.setBackground(new Color(random.nextInt()));
    }
} );
