// 예제 12.4  테스트 프로그램에 적합한 중간 품질의 난수 발생기

static int xorShift(int y) {
    y ^= (y << 6);
    y ^= (y >>> 21);
    y ^= (y << 7);
    return y;
}
