package nix.alevel.pavlo.hnatenko.hometask8;

import nix.alevel.pavlo.hnatenko.hometask8.library.util.*;
import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    static int[] ints = new int[1000];
    static Set<SortInterface> sorts = new HashSet<>();
    static Map<String, Long> sortRating = new HashMap<>();
    static long beforeSort;
    static long collectionsSortTime = getAverageCollectionsSortNanoTime();


    public static void main(String[] args) {

        sorts.add(new BubbleSort());
        sorts.add(new InsertionSort());
        sorts.add(new MergeSort());
        sorts.add(new QuickSort());
        sorts.add(new SelectionSort());
        sorts.add(new ShellSort());
        sorts.add(new ShuttleSort());

        logger.info("Collections sort average sort time at 100 iteration: " + collectionsSortTime + "\n");
        for (SortInterface sort : sorts) {
            compareWithCollectionsSort(sort);
        }

        getSortRatingLog();

    }

    static void fullFillArray(int[] array) {
        int index;
        for (int i = 0; i < array.length; i++) {
            index = (int) Math.floor(Math.random() * 1000000);
            array[i] = index;
        }
    }

    static long getNanoTime() {
        return System.nanoTime() - beforeSort;
    }

    static void setBeforeSort() {
        beforeSort = System.nanoTime();
    }

    static long getSortNanoTime(SortInterface implementor) {
        fullFillArray(ints);
        setBeforeSort();
        implementor.sort(ints);
        return getNanoTime();
    }

    static long getAverageSortNanoTime(SortInterface implementor) {
        int i = 0;
        long averageTime = 0;
        while (i < 100) {
            averageTime += getSortNanoTime(implementor);
            i++;
        }
        averageTime /= i;

        return averageTime;
    }

    static long getCollectionsSortNanoTime() {
        fullFillArray(ints);
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        setBeforeSort();
        Collections.sort(list);
        return getNanoTime();
    }

    static long getAverageCollectionsSortNanoTime() {
        int i = 0;
        long averageTime = 0;
        while (i < 100) {
            averageTime += getCollectionsSortNanoTime();
            i++;
        }
        averageTime /= i;

        return averageTime;
    }

    static void compareWithCollectionsSort(SortInterface implementor) {
        long ourSortTime = getAverageSortNanoTime(implementor);
        logger.info(implementor.getSortName() + " average sort time at 100 iteration: " + ourSortTime);

        if (ourSortTime > collectionsSortTime) {
            double quotient = (double) ourSortTime / collectionsSortTime;
            String formattedDouble = new DecimalFormat("#0.00").format(Math.abs(quotient));
            logger.info("Our sort " + implementor.getSortName() + " is slower than Collections sort in " + formattedDouble + " times. \n");
        } else if (ourSortTime < collectionsSortTime) {
            double quotient = (double) collectionsSortTime / ourSortTime;
            String formattedDouble = new DecimalFormat("#0.00").format(Math.abs(quotient));
            logger.info("Our sort " + implementor.getSortName() + " is faster than Collections sort in " + formattedDouble + " times. \n");
        } else {
            logger.info("Our sort " + implementor.getSortName() + " and Collections sort is equals! Incredible! \n");
        }

        getSortRatingEntry(implementor, ourSortTime);
    }

    static void getSortRatingEntry(SortInterface implementor, long sortTime) {
        sortRating.put(implementor.getSortName(), sortTime);
    }

    static void getSortRatingLog() {
        List<Map.Entry<String, Long>> list = new ArrayList<>(sortRating.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<String, Long> result = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        int i = 1;
        logger.info("Our sort top is following: \n");
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            logger.info(i + ". " + entry.getKey() + ": " + entry.getValue());
            i++;
        }

    }

}
