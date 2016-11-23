// 예제 6.17  제한된 시간 안에 여행 관련 입찰 정보를 가져오도록 요청하는 코드

private class QuoteTask implements Callable<TravelQuote> {
    private final TravelCompany company;
    private final TravelInfo travelInfo;
    // ...
    public TravelQuote call() throws Exception {
	return company.solictQuote(travelInfo);
    }
}

public List<TravelQuote> getRankedTravelQuotes(
        TravelInfo travelInfo, Set<TravelCompany> companies,
	Comparator<TravelQuote> ranking, long time, TimeUnit unit)
        throws InterruptedException {
    List<QuoteTask> tasks = new ArrayList<QuoteTask>();
    for (TravelCompany company : companies)
	tasks.add(new QuoteTask(company, travelInfo));

    List<Future<TravelQuote>> futures =
	exec.invokeAll(tasks, time, unit);

    List<TravelQuote> quotes =
	new ArrayList<TravelQuote>(tasks.size());
    Iterator<QuoteTask> taskIter = tasks.iterator();
    for (Future<TravelQuote> f : futures) {
	QuoteTask task = taskIter.next();
	try {
	    quotes.add(f.get());
	} catch (ExecutionException e) {
	    quotes.add(task.getFailureQuote(e.getCause()));
	} catch (CancellationException e) {
	    quotes.add(task.getTimeoutQuote(e));
	}
    }

    Collections.sort(quotes, ranking);
    return quotes;
}
