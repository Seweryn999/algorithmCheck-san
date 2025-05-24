import java.util.Arrays;

class PorownanieSortowania {

    public static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static void main(String[] args) {
        int[] data = new int[2500];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 2500);
        }

        int[] copy = Arrays.copyOf(data, data.length);

        System.out.println("Algorytm porównujący dwa podejścia do sortowania tablicy");

        System.out.println("\nPrzed sortowaniem: " + Arrays.toString(data));

        System.gc();
        long memBeforeBubble = getUsedMemory();

        long startTime = System.nanoTime();
        int[] bubbleSorted = bubbleSort(data);
        long timeBubble = System.nanoTime() - startTime;

        long memAfterBubble = getUsedMemory();

        System.gc();
        long memBeforeOptimal = getUsedMemory();
        startTime = System.nanoTime();
        Arrays.sort(copy);
        long timeOptimal = System.nanoTime() - startTime;
        long memAfterOptimal = getUsedMemory();

        System.out.println("\n--- Bubble Sort ---");
        System.out.println("Po sortowaniu: " + Arrays.toString(bubbleSorted));
        System.out.println("Czas: " + timeBubble + " ns");
        System.out.println("Pamięć przed: " + memBeforeBubble + " B");
        System.out.println("Pamięć po: " + memAfterBubble + " B");
        System.out.println("Różnica: " + (memAfterBubble - memBeforeBubble) + " B");

        System.out.println("\n--- Arrays.sort() ---");
        System.out.println("Po sortowaniu: " + Arrays.toString(copy));
        System.out.println("Czas: " + timeOptimal + " ns");
        System.out.println("Pamięć przed: " + memBeforeOptimal + " B");
        System.out.println("Pamięć po: " + memAfterOptimal + " B");
        System.out.println("Różnica: " + (memAfterOptimal - memBeforeOptimal) + " B");

        System.out.println("\nWniosek: Bubble Sort był " +
                (timeBubble > timeOptimal ? "wolniejszy" : "szybszy") +
                " niż Arrays.sort()");

    }

    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        int compareCount = 0;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                compareCount++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                    swapCount++;
                }
            }
            if (!swapped) break;
        }

        System.out.println("\nStatystyki sortowania:");
        System.out.println("Porównań: " + compareCount);
        System.out.println("Zamian: " + swapCount);

        return arr;
    }
}
