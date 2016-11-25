// 예제 11.7  락이 분할된 ServerStatus 클래스

@ThreadSafe
public class ServerStatus {
    @GuardedBy("users") public final Set<String> users;
    @GuardedBy("queries") public final Set<String> queries;
    //...
    public void addUser(String u) {
	synchronized (users) {
	    users.add(u);
	}
    }
    public void addQuery(String q) {
	synchronized (queries) {
	    queries.add(q);
	}
    }
    // remove 메소드 역시 락을 분할시켜 만들 수 있다.
}
