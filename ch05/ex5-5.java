// 예제 5.5  Iterator를 사용해 List 클래스의 값을 반복해 뽑아내는 모습

List<Widget> widgetList
    = Collections.synchronizedList(new ArrayList<Widget>());
// ...
// ConcurrentModificationException이 발생할 수 있다.
kfor (Widget w : widgetList)
    doSomething(w);
