package nix.alevel.pavlo.hnatenko.hometask8;

import nix.alevel.pavlo.hnatenko.hometask8.library.util.*;
import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    static int[] ints = new int[10000];
    static long beforeSort;

    public static void main(String[] args) {

        Set<SortInterface> sorts = new HashSet<>();
        sorts.add(new BubbleSort());
        sorts.add(new InsertionSort());
        sorts.add(new MergeSort());
        sorts.add(new QuickSort());
        sorts.add(new SelectionSort());
        sorts.add(new ShellSort());
        sorts.add(new ShuttleSort());

        for (SortInterface sort : sorts){
            getSortNanoTimeLog(sort);
            getCollectionsSortNanoTimeLog();
            compareWithCollectionsSort(sort);
        }

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

    static void setBeforeSort(){
        beforeSort = System.nanoTime();
    }

    static void getSortNanoTimeLog(SortInterface implementor){
        logger.info(implementor.getSortName() + ": " + getSortNanoTime(implementor));
    }

    static long getSortNanoTime(SortInterface implementor){
        fullFillArray(ints);
        setBeforeSort();
        implementor.sort(ints);
        return getNanoTime();
    }

    static long getCollectionsSortNanoTime(){
        fullFillArray(ints);
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        setBeforeSort();
        Collections.sort(list);
        return getNanoTime();
    }

    static void getCollectionsSortNanoTimeLog(){
        logger.info("Collections sort: " + getCollectionsSortNanoTime());
    }

    static void compareWithCollectionsSort(SortInterface implementor){
        long ourSortTime = getSortNanoTime(implementor);
        long collectionsSortTime = getCollectionsSortNanoTime();

        if (ourSortTime > collectionsSortTime){
            double quotient  = (double) ourSortTime / collectionsSortTime;
            String formattedDouble = new DecimalFormat("#0.00").format(Math.abs(quotient));
            logger.info("Our sort " + implementor.getSortName() + " is slower than Collections sort in " + formattedDouble  + " times.");
        } else if (ourSortTime < collectionsSortTime){
            double quotient  = (double) collectionsSortTime / ourSortTime;
            String formattedDouble = new DecimalFormat("#0.00").format(Math.abs(quotient));
            logger.info("Our sort " + implementor.getSortName() + " is faster than Collections sort in " + formattedDouble + " times.");
        } else {
            logger.info("Our sort " + implementor.getSortName() + " and Collections sort is equals! Incredible!");
        }


    }


}
