package nix.alevel.pavlo.hnatenko.hometask8.library.util;

public class BubbleSort implements SortInterface {

    @Override
    public String getSortName() {
        return "BubbleSort";
    }

    @Override
    public void sort(int[] ints) {
        int n = ints.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
    }
}
