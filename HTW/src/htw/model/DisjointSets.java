package htw.model;

class DisjointSets {
	Subset[] subsets;
	int n;

	class Subset {
		int parent;
		int rank;

		Subset(int parent, int rank) {
			this.parent = parent;
			this.rank = rank;
		}
	}

	DisjointSets(int n) {
		this.n = n;
		this.subsets = new Subset[n];
		makeSet();
	}

	void makeSet() {
		for (int i = 0; i < n; i++) {
			subsets[i] = new Subset(i, 0);
		}
	}

	void union(int x, int y) {
		int representativeX = findSet(x);
		int representativeY = findSet(y);
		if (subsets[representativeX].rank < subsets[representativeY].rank) {
			subsets[representativeX].parent = representativeY;
		} else {
			subsets[representativeY].parent = representativeX;
			if (subsets[representativeX].rank == subsets[representativeY].rank) {
				subsets[representativeX].rank++;
			}
		}
	}

	int findSet(int x) {
		if (x != subsets[x].parent) {
			subsets[x].parent = findSet(subsets[x].parent);
		}
		return subsets[x].parent;
	}
}
