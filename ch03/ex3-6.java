// 예제 3.6  내부적으로 사용할 변수를 외부에 공개. 이런 코드는 금물!

class UnsafeStates {
    private String[] states = new String[] {
	"AK", "AL" // ...
    };
    public String[] getStates() { return states; }
}
