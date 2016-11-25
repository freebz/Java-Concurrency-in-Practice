// 예제 11.4  필요 이상으로 락을 잡고 있는 모습

@ThreadSafe
public class AttributeStore {
    @GuardedBy("this") private final Map<String, String>
	attributes = new HashMap<String, String>();

    public synchronized boolean userLocationMatches(String name,
						    String regexp) {
	String key = "users." + name + ".location";
	String location = attributes.get(key);
	if (location == null)
	    return false;
	else
	    return Pattern.matches(regexp, location);
    }
}
