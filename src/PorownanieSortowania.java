import java.util.Arrays;

public class PorownanieSortowania {

    public static void main(String[] args) {
        final int ROZMIAR = 2500; // Ustalamy rozmiar tablicy
        int[] oryginal = generujTablice(ROZMIAR); // Tworzymy tablicę z losowymi liczbami
        int[] kopia = Arrays.copyOf(oryginal, oryginal.length); // Kopiujemy tablicę do drugiego sortowania

        System.out.println("=== Analiza sortowania ===\n");

        // Porównujemy dwa rodzaje sortowania
        wynikSortowania("BubbleSort", oryginal, BubbleSorter::sortuj);
        wynikSortowania("Arrays.sort()", kopia, Arrays::sort);
    }

    // Metoda tworzy tablicę z losowymi liczbami
    static int[] generujTablice(int n) {
        int[] tablica = new int[n];
        for (int i = 0; i < n; i++) {
            tablica[i] = (int) (Math.random() * n);
        }
        return tablica;
    }

    // Metoda wykonuje sortowanie, mierzy czas i zużycie pamięci
    static void wynikSortowania(String nazwa, int[] tablica, SortujacaFunkcja sortujaca) {
        System.gc(); // Prosimy system o oczyszczenie pamięci (GC)
        long pamiecPrzed = uzytaPamiec(); // Pamięć przed sortowaniem
        long czasStart = System.nanoTime(); // Czas startu

        sortujaca.sort(tablica); // Wywołujemy przekazaną metodę sortującą

        long czasKoniec = System.nanoTime(); // Czas zakończenia
        long pamiecPo = uzytaPamiec(); // Pamięć po sortowaniu

        // Wyświetlamy wyniki
        System.out.println(">> " + nazwa);
        System.out.println("Czas: " + (czasKoniec - czasStart) + " ns");
        System.out.println("Pamięć: " + (pamiecPo - pamiecPrzed) + " B");
        System.out.println("Posortowana tablica (pierwsze 20): " + Arrays.toString(Arrays.copyOf(tablica, 20)));
        System.out.println();
    }

    // Metoda zwraca aktualnie używaną pamięć RAM przez program
    static long uzytaPamiec() {
        Runtime r = Runtime.getRuntime();
        return r.totalMemory() - r.freeMemory();
    }

    // Interfejs do przekazywania funkcji sortujących jako argumentów
    @FunctionalInterface
    interface SortujacaFunkcja {
        void sort(int[] tablica);
    }
}
