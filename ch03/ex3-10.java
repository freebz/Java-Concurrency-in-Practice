// 예제 3.10  ThreadLocal을 사용해 스레드 한정 상태를 유지

private static ThreadLocal<Connection> connectionHolder
    = new ThreadLocal<Connection>() {
	    public Connection initialValue() {
		return DriverManager.getConnection (DB_URL);
	    }
	};

public static Connection getConnection() {
    return connectionHolder.get();
}
