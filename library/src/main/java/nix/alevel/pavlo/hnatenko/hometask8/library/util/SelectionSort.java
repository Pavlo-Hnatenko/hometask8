package nix.alevel.pavlo.hnatenko.hometask8.library.util;

public class SelectionSort implements SortInterface {

    @Override
    public String getSortName() {
        return "SelectionSort";
    }

    @Override
    public void sort(int[] ints) {
        int n = ints.length;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (ints[j] < ints[min_idx])
                    min_idx = j;

            int temp = ints[min_idx];
            ints[min_idx] = ints[i];
            ints[i] = temp;
        }
    }

}
