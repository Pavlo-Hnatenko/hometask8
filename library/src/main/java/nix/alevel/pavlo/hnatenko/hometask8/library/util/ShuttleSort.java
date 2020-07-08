package nix.alevel.pavlo.hnatenko.hometask8.library.util;

public class ShuttleSort implements SortInterface {

    @Override
    public String getSortName() {
        return "ShuttleSort";
    }

    @Override
    public void sort(int[] ints) {
        int size = ints.length;
        boolean swapped;
        for (int k = size - 1; k > 0; k--) {
            swapped = false;
            for (int i = k; i > size - 1 - k; i--)
                if (ints[i] < ints[i - 1]) {
                    int temp = ints[i];
                    ints[i] = ints[i - 1];
                    ints[i - 1] = temp;
                    swapped = true;
                }

            for (int i = size - k; i < k; i++)
                if (ints[i] > ints[i + 1]) {
                    int temp = ints[i];
                    ints[i] = ints[i + 1];
                    ints[i + 1] = temp;
                    swapped = true;
                }

            if (!swapped)
                break;
        }
    }

}
