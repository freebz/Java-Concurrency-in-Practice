// 예제 3.9  기본 변수형의 로컬 변수와 객체형의 로컬 변수에 대한 스택 한정

public int loadTheArk(Collection<Animal> candidates) {
    SortedSet<Animal> animals;
    int numPairs = 0;
    Animal candidate = null;

    // animals 변수는 메소드에 한정되어 있으며, 유출돼서는 안 된다.
    animals = new TreeSet<Animal>(new SpeciesGenderComparator());
    animals.addAll(candidates);
    for (Animal a : animals) {
	if (candidate == null || !candidate.isPotentialMate(a))
	    candidate = a;
	else {
	    ark.load(new AnimalPair(candidate, a));
	    ++numPairs;
	    candidate = null;
	}
    }
    return numPairs;
}
