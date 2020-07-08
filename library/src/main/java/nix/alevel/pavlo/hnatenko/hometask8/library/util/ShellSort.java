package nix.alevel.pavlo.hnatenko.hometask8.library.util;

public class ShellSort implements SortInterface {

    @Override
    public String getSortName() {
        return "ShellSort";
    }

    @Override
    public void sort(int[] ints) {
        int n = ints.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = ints[i];
                int j = i;
                while (j >= gap && ints[j - gap] > key) {
                    ints[j] = ints[j - gap];
                    j -= gap;
                }
                ints[j] = key;
            }
        }
    }

}
