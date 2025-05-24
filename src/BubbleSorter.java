public class BubbleSorter {
    public static void sortuj(int[] tablica) {
        boolean zmiana;
        int n = tablica.length;

        // Sortowanie bąbelkowe: porównujemy i zamieniamy elementy
        for (int i = 0; i < n - 1; i++) {
            zmiana = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (tablica[j] > tablica[j + 1]) {
                    // Zamiana elementów miejscami
                    int tmp = tablica[j];
                    tablica[j] = tablica[j + 1];
                    tablica[j + 1] = tmp;
                    zmiana = true;
                }
            }
            // Jeśli nie było żadnej zamiany, tablica jest już posortowana
            if (!zmiana) break;
        }
    }
}
