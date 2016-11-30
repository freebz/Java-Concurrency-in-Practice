// 예제 16.8  불변 객체의 초기화 안전성

@ThreadSafe
public class SafeStates {
    private final Map<String, String> states;

    public SafeStates() {
	states = new HashMap<String, String>();
	states.put("alaska", "AK");
	states.put("alabama", "AL");
	//...
	states.put("wyoming", "WY");
    }

    public String getAbbreviation(String s) {
	return states.get(s);
    }
}
