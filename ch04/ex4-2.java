// 예제 4.2  한정 기법으로 스레드 안전성 확보

@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
	mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
	return mySet.contains(p);
    }
}
