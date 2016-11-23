// 예제 6.16  제한된 시간 안에 광고 가져오기

Page renderPageWithAd() throws InterruptedException {
    long endNanos = System.nanoTime() + TIME_BUDGET;
    Future<Ad> f = exec.submit(new FetchAdTask());
    // 광고 가져오는 작업을 등록했으니, 원래 페이지를 작업한다
    Page page = renderPageBody();
    Ad ad;
    try {
	// 남은 시간 만큼만 대기한다
	long timeLeft = endNanos - System.nanoTime();
	ad = f.get(timeLeft, NANOSECONDS);
    } catch (ExecutionException e) {
	ad = DEFAULT_AD;
    } catch (TimeoutException e) {
	ad = DEFAULT_AD;
	f.cancel(true);
    }
    page.setAd(ad);
    return page;
}
