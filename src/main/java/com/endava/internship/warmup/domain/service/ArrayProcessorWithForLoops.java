package com.endava.internship.warmup.domain.service;

import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

public class ArrayProcessorWithForLoops implements ArrayProcessor {

    /**
     * Return true if there are no numbers that divide by 10
     *
     * @param input non-null immutable array of ints
     */
    @Override
    public boolean noneMatch(final int[] input) {

        for (int num : input) {
            if (num % 10 == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Return true if at least one value in input matches the predicate
     *
     * @param input     non-null immutable array of ints
     * @param predicate invoke the predicate.test(int value) on each input element
     */
    @Override
    public boolean someMatch(final int[] input, IntPredicate predicate) {


        for (int num : input) {
            if (predicate.test(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if all values processed by function, matches the predicate
     *
     * @param input     non-null immutable array of Strings. No element is null
     * @param function  invoke function.applyAsInt(String value) to transform all the input elements into an int value
     * @param predicate invoke predicate.test(int value) to test the int value obtained from the function
     */
    @Override
    public boolean allMatch(final String[] input,
                            ToIntFunction<String> function,
                            IntPredicate predicate) {
        for (String stringValue : input) {
            int intValue = function.applyAsInt(stringValue);
            if (!predicate.test(intValue)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Copy values into a separate array from specific index to stopindex
     *
     * @param input          non-null array of ints
     * @param startInclusive the first index of the element from input to be included in the new array
     * @param endExclusive   the last index prior to which the elements are to be included in the new array
     * @throws IllegalArgumentException when parameters are outside of input index bounds
     */
    @Override
    public int[] copyValues(int[] input, int startInclusive, int endExclusive) throws IllegalArgumentException {
        if (endExclusive <= startInclusive || startInclusive < 0) {
            throw new IllegalArgumentException();
        }
        int newArrayLength = endExclusive - startInclusive;
        int[] newArray = new int[newArrayLength];
        int index = 0;
        for (int i = startInclusive; i <= newArrayLength; i++) {
            if (i >= startInclusive && i < endExclusive) {
                newArray[index++] = input[i];
            }

        }
        return newArray;
    }

    /**
     * Replace even index values with their doubles and odd indexed elements with their negative
     *
     * @param input non-null immutable array of ints
     * @return new array with changed elements
     */
    @Override
    public int[] replace(final int[] input) {
        int[] newArray = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) {
                newArray[i] = input[i] * 2;
            } else {
                newArray[i] = -input[i];
            }
        }
        return newArray;
    }

    /**
     * Find the second max value in the array
     *
     * @param input non-null immutable array of ints
     */
    @Override
    public int findSecondMax(final int[] input) {
        int[] sortedArray = sortArray(input);
        int maxValue = sortedArray[sortedArray.length - 1];
        int secondMaxValue = 0;
        if (input.length < 2) {
            System.out.println("Invalid Input");
        }
        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i] == maxValue) {
                if (i == 0) {
                    System.out.println("All the elements are equal! Max value = Second max value = ");
                    return sortedArray[i];
                } else {
                    return sortedArray[i - 1];
                }
            }
        }
        return secondMaxValue;
    }

    /**
     * Return in reverse first negative numbers, then positive numbers from array
     *
     * @param input non-null immutable array of ints.
     * @return example: input {3, -5, 4, -7, 2 , 9}
     * result: {-7, -5, 9, 2, 4, 3}
     */
    @Override
    public int[] rearrange(final int[] input) {
        int[] newArray = new int[input.length];
        int index = 0;
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] < 0) {
                newArray[index++] = input[i];
            }
        }
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] >= 0) {
                newArray[index++] = input[i];
            }
        }
        return newArray;
    }


    /**
     * Remove (filter) all values which are smaller than (input max element - 10)
     *
     * @param input non-null immutable array of ints
     * @return The result array should not contain empty cells!
     */
    @Override
    public int[] filter(final int[] input) {
        int count = 0;
        for (int number : input) {
            if (number < 0) {
                count++;
            }
        }
        if (count == 0) {
            return input;
        }

        int[] result = new int[input.length - count];
        int index = 0;
        for (int value : input) {
            if (value > 0) {
                result[index] = value;
                index++;
            }
        }
        return result;
    }

    /**
     * Insert values into input array at a specific index.
     *
     * @param input          non-null immutable array of ints.
     * @param startInclusive the index of input at which the first element from values array should be inserted
     * @param values         the values to be inserted from startInclusive index
     * @return new array containing the combined elements of input and values
     * @throws IllegalArgumentException when startInclusive is out of bounds for input
     */
    @Override
    public int[] insertValues(final int[] input, int startInclusive, int[] values) throws IllegalArgumentException {
        int j = 0;
        if (startInclusive < 0) throw new IllegalArgumentException();

        int newArrayLength = input.length + values.length;
        int[] newArray = new int[newArrayLength];

        for (int i = 0; i < newArrayLength; i++) {
            if (i < startInclusive) {
                newArray[i] = input[i];
            }
            if (i >= startInclusive && i < startInclusive + values.length) {
                newArray[i] = values[j++];
            }
            if (i >= startInclusive + values.length) {
                newArray[i] = input[startInclusive++];
            }

        }
        return newArray;
    }

    /**
     * Merge two sorted input and input2 arrays so that the return values are also sorted
     *
     * @param input  first non-null array
     * @param input2 second non-null array
     * @return new array containing all elements sorted from input and input2
     * @throws IllegalArgumentException if either input or input are not sorted ascending
     */
    @Override
    public int[] mergeSortedArrays(int[] input, int[] input2) throws IllegalArgumentException {
        int mergedArrayLength = input.length + input2.length;
        int[] mergedArray = new int[mergedArrayLength];
        int position = 0;

        for (int element : input) {
            mergedArray[position] = element;
            position++;
        }

        for (int element : input2) {
            mergedArray[position] = element;
            position++;
        }
        int[] sortedArray = sortArray(mergedArray);
        for (int j : mergedArray) {
            System.out.print(j + " ");
        }
        return sortedArray;
    }


    public static int[] sortArray(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * In order to execute a matrix multiplication, in this method, please validate the input data throwing exceptions for invalid input. If the the
     * input params are satisfactory, do not throw any exception.
     * <p>
     * Please review the matrix multiplication https://www.mathsisfun.com/algebra/matrix-multiplying.html
     *
     * @param leftMatrix  the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException     when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public void validateForMatrixMultiplication(int[][] leftMatrix, int[][] rightMatrix) throws
            NullPointerException, IllegalArgumentException {

        if (leftMatrix.length == 0 || rightMatrix.length == 0) {
            throw new IllegalArgumentException("The matrix must not be null");
        }
        if (leftMatrix[0].length == 0 || rightMatrix[0].length == 0) {
            throw new NullPointerException("Rows must not be null");
        }
        if (leftMatrix.length != rightMatrix[0].length) {
            throw new IllegalArgumentException("The number of columns of the 1st matrix is NOT equal the number of rows of the 2nd matrix.");
        }
    }

    /**
     * Perform the matrix multiplication as described in previous example Please review the matrix multiplication
     * https://www.mathsisfun.com/algebra/matrix-multiplying.html
     *
     * @param leftMatrix  the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException     when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public int[][] matrixMultiplication(final int[][] leftMatrix, final int[][] rightMatrix) throws
            NullPointerException, IllegalArgumentException {
        int nrRows1, nrRows2, nrColumns1, nrColumns2, sum = 0, i, j, k;
        nrRows1 = leftMatrix.length;
        nrColumns1 = leftMatrix[0].length;
        nrRows2 = rightMatrix.length;
        nrColumns2 = rightMatrix[0].length;
        int[][] multiply = new int[nrRows1][nrColumns2];


        if (nrColumns1 != nrRows2) {
            System.out.println("The matrices can't be multiplied with each other.");
        } else {
            for (i = 0; i < nrRows1; i++) {
                for (j = 0; j < nrColumns2; j++) {
                    for (k = 0; k < nrColumns1; k++)
                        sum = sum + leftMatrix[i][k] * rightMatrix[k][j];

                    multiply[i][j] = sum;
                    sum = 0;
                }
            }
        }
        return multiply;
    }


    /**
     * Return only distinct values in an array.
     *
     * @param input non-null immutable array of ints.
     */
    @Override
    public int[] distinct(final int[] input) {
        int index = 0;
        int[] newArray = new int[countDistinct(input)];
        for (int i = 0; i < input.length; i++) {
            boolean isdistinct = true;
            for (int j = 0; j < i; j++) {
                if (input[i] == input[j]) {
                    isdistinct = false;
                    break;
                }
            }
            if (isdistinct) {
                newArray[index++] = input[i];
            }
        }
        return newArray;
    }

    public static int countDistinct(int[] arr) {
        int[] sortedArr = sortArray(arr);
        int res = 0;
        for (int i = 0; i < sortedArr.length; i++) {
            // Move the index ahead while
            // there are duplicates
            while (i < sortedArr.length - 1 &&
                    sortedArr[i] == sortedArr[i + 1]) {
                i++;
            }
            res++;
        }
        return res;
    }
}




