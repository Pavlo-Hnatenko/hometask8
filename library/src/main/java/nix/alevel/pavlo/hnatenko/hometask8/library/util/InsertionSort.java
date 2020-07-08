package nix.alevel.pavlo.hnatenko.hometask8.library.util;

public class InsertionSort implements SortInterface {

    @Override
    public String getSortName() {
        return "InsertionSort";
    }

    @Override
    public void sort(int[] ints) {
        int n = ints.length;
        for (int i = 1; i < n; ++i) {
            int key = ints[i];
            int j = i - 1;

            while (j >= 0 && ints[j] > key) {
                ints[j + 1] = ints[j];
                j = j - 1;
            }
            ints[j + 1] = key;
        }
    }
}
