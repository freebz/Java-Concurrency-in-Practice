// 예제 3.12  입력 값과 인수분해된 결과를 묶는 불변 객체

@Immutable
class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i,
			 BigInteger[] factors) {
	lastNumber = i;
	lastFactor = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
	if (lastNumber == null || !lastNumber.equals(i))
	    return null;
	else
	    return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
