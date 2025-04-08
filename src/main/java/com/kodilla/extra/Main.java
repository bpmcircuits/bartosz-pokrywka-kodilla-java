package com.kodilla.extra;

class MinMax {

    private final int[] numbers;

    public MinMax(int[] numbers) {
        this.numbers = numbers;
        quickSort(numbers, 0, numbers.length - 1);
    }

    public int getMin() {
        return numbers[0];
    }

    public int getMax() {
        return numbers[numbers.length - 1];
    }

    private void quickSort(int[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivotIndex = partition(array, startIndex, endIndex);

            quickSort(array, startIndex, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, endIndex);
        }
    }

    private int partition(int[] array, int startIndex, int endIndex) {
        int pivotValue = array[endIndex];
        int smallerElementIndex = startIndex - 1;

        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] < pivotValue) {
                smallerElementIndex++;
                swap(array, smallerElementIndex, i);
            }
        }

        swap(array, smallerElementIndex + 1, endIndex);
        return smallerElementIndex + 1;
    }

    private void swap(int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }
}

public class Main {

    public static void main(String[] args) {
        int[] numbers = {3,1,5,6,7,9,15,3,4,7,10};

        MinMax minMax = new MinMax(numbers);
        System.out.println(minMax.getMin());
        System.out.println(minMax.getMax());
    }
}
