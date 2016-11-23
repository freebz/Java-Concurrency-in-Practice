// 예제 5.13  Throable을 RuntimeException으로 변환

/** 변수 t의 내용이 Error라면 그대로 throw한다. 변수 t의 내용이
 * RuntimeException이라면 그대로 리턴한다. 다른 모든 경우에는
 * IllegalStateException을 throw한다.
 */
public static RuntimeException launderThrowable(Throwable t) {
    if (t instanceof RuntimeException )
	return (RuntimeException ) t;
    else if (t instanceof Error)
	throw (Error) t;
    else
	throw new IllegalStateException("RuntimeException이 아님", t);
}
