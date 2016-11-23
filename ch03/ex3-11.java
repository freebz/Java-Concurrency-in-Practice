// 예제 3.11  일반 객체를 사용해 불변 객체를 구성한 모습

@Immutable
public final class ThreeStooges {
    private final<String> stooges = new HashSet<String>();

    public ThreeStooges() {
	stooges.add("Moe");
	stooges.add("Larry");
	stooges.add("Curly");
    }

    public boolean isStooge(String name) {
	return stooges.contains(name);
    }
}
