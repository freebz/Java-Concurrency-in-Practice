// 예제 3.14  동기화 하지 않고 객체를 외부에 공개. 이런 코드는 금물!

// 안전하지 않은 객체 공개
public Holder holder;

public void initialize() {
    holder = new Holder(42);
}
