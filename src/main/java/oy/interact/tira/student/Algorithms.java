package oy.interact.tira.student;

import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array) {

      /*
       * for-loop, jonka avulla lähdetään käymään taulukkoa läpi toisesta indeksistä,
       * koska ensimmäinen indeksi voidaan jo pitää järjestettynä, tämä nopeuttaa
       * algoritmin suoritusta
       */
      for (int index = 1; index < array.length; index++) {
         T point = array[index]; // tässä tallennetaan index-muuttujan osoittama alkoi vertailtavaksi
                                 // point-nimiseen muuttujaan
         int currentIndex = index - 1; // alustetaan toiseksi vertailtavaksi arvoksi index-muuttujan edellä oleva
                                       // taulukon alkio

         /*
          * while-loop, jossa tapahtuu vertailu point-elementin ja taulukon aikasempien
          * alkioiden kanssa, silmukka siirtää
          * suuremmaksi havaittuja alkioita oikealle niin kauan kuin on tarpeen
          */
         while (currentIndex >= 0 && array[currentIndex].compareTo(point) > 0) {
            array[currentIndex + 1] = array[currentIndex];
            currentIndex--;
         }
         // tässä vaiheessa iteraattori on löytänyt oikean paikan point-alkiolle ja
         // siirtää sen nyt oikeaan paikkaan
         array[currentIndex + 1] = point;
      }

   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {
      /*
       * Algoritmi poikkeaa hieman ylemmästä toteutuksesta. Tässä lajitellaan taulukko
       * tiettyjen indeksien välillä.
       * Tämän otin huomioon alustaessani for-silmukkaa, sillä käytännössä vain siinä
       * kohtaa taulukon läpikäyntiin voidaan vaikuttaa.
       * Muutoin koodi on samanlaista kuin aiemmassa insertionSort-metodissa.
       */
      for (int index = fromIndex + 1; index < toIndex; index++) {
         T point = array[index];
         int currentIndex = index - 1;
         while (currentIndex >= 0 && array[currentIndex].compareTo(point) > 0) {
            array[currentIndex + 1] = array[currentIndex];
            currentIndex--;
         }
         array[currentIndex + 1] = point;
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
      // Ei vielä dokumentoitu... osa 02 kesken
      for (int index = 1; index < array.length; index++) {
         T point = array[index];
         int currentIndex = index - 1;

         while (currentIndex >= 0 && comparator.compare(array[currentIndex], point) > 0) {
            array[currentIndex + 1] = array[currentIndex];
            currentIndex--;
         }
         array[currentIndex + 1] = point;
      }

   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      // ei vielä dokumentoitu.. osa 02 kesken
      for (int i = fromIndex + 1; i < toIndex; i++) {
         T point = array[i];
         int currentIndex = i - 1;
         while (currentIndex >= 0 && comparator.compare(array[currentIndex], point) > 0) {
            array[currentIndex + 1] = array[currentIndex];
            currentIndex--;
         }
         array[currentIndex + 1] = point;
      }
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
      // Ensin määritellään tarvittavat muuttujat eli tässä tapauksessa taulukon
      // aloituspiste eli 0-indeksi ja loppupiste eli taulukon pituus-1.
      int startPoint;
      int endPoint = array.length - 1;

      // käydään taulukko läpi aloittaen alkupisteestä
      for (startPoint = 0; startPoint < endPoint; startPoint++, endPoint--) {
         // käytetään swap-nimistä metodia joka vaihtaa kahden osoitetun taulukon alkion
         // paikan
         swap(array, startPoint, endPoint);
      }
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      // Tämä ei eroa ylläolevasta metodista muutoin kuin aloitus- ja lopetuspisteiden
      // määrittelyssä.
      int startPoint = fromIndex;
      int endPoint = toIndex - 1;
      for (startPoint = 0; startPoint < endPoint; startPoint++, endPoint--) {
         swap(array, startPoint, endPoint);
      }
   }

   // Oma metodi:
   private static <T> void swap(T[] array, int firstIndex, int secondIndex) {
      T pointer = array[firstIndex]; // otetaan muuttujaan pointer taulukon ensimmäinen vaihdettava indeksi
      array[firstIndex] = array[secondIndex]; // vaihdetaan toisen indeksi paikka ensimmäisen indeksin kohdalle
      array[secondIndex] = pointer; // vaihdetaan toisen indeksin paikalle pointer-muuttujan sisältämä ensimmäisen
                                    // indeksin arvo
   }

   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {

      int left = fromIndex;
      int right = toIndex - 1;

      while (left <= right) {
         int middlePointer = left + (right - left) / 2;

         if (aValue.compareTo(fromArray[middlePointer]) == 0)
            return middlePointer;
         else if (aValue.compareTo(fromArray[middlePointer]) < 0)
            right = middlePointer - 1;
         else
            left = middlePointer + 1;
      }
      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      int left = fromIndex;
      int right = toIndex - 1;

      while(left <= right){
         int middlePointer = (left + right) / 2;

         if(comparator.compare(aValue, fromArray[middlePointer]) == 0){
            return middlePointer;
         } else if(comparator.compare(aValue, fromArray[middlePointer]) < 0){
            right = middlePointer - 1;
         } else{
            left = middlePointer + 1;
         }
      }
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E[] array) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E[] array, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

}
