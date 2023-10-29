package oy.interact.tira.student;

import java.util.Comparator;
import java.lang.reflect.Array;

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

      while (left <= right) {
         int middlePointer = left + ((right - left) / 2);

         if (comparator.compare(aValue, fromArray[middlePointer]) == 0) {
            return middlePointer;
         } else if (comparator.compare(aValue, fromArray[middlePointer]) < 0) {
            right = middlePointer - 1;
         } else {
            left = middlePointer + 1;
         }
      }
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E[] array) {
      int length = array.length;
      mergeSortComparable(array, 0, length);

   }

   public static <E> void fastSort(E[] array, Comparator<E> comparator) {
      mergeSortComparator(array, 0, array.length, comparator);
   }

   public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      mergeSortComparator(array, fromIndex, toIndex, comparator);

   }

   // MERGE SORT METHODS FOR TASK 06
 
   

   @SuppressWarnings("unchecked")
   private static <E extends Comparable<E>> void mergeSortComparable(E[] array, int fromIndex, int length) {
      int arrayLength = length;

      if (arrayLength < 2) {
         return;
      }

      int middlePointer = arrayLength / 2;
      E[] leftHalf = (E[]) Array.newInstance(array.getClass().getComponentType(), middlePointer);
      E[] rightHalf = (E[]) Array.newInstance(array.getClass().getComponentType(), arrayLength - middlePointer);

      // Copy the arrays from the specified range
      for (int index = 0; index < middlePointer; index++) {
         leftHalf[index] = array[fromIndex + index];
      }

      for (int index = middlePointer; index < arrayLength; index++) {
         rightHalf[index - middlePointer] = array[fromIndex + index];
      }

      // Recursive calls to MergeSort both halves
      mergeSortComparable(leftHalf, 0, middlePointer);
      mergeSortComparable(rightHalf, 0, arrayLength - middlePointer);

      // Merge the two halves back into the original array
      mergeComparable(array, leftHalf, rightHalf);
   }

   // helper method for merging two arrays using the Comparable interface
   private static <E extends Comparable<E>> void mergeComparable(E[] array, E[] leftHalf, E[] rightHalf) {
      int leftHalfSize = leftHalf.length;
      int rightHalfSize = rightHalf.length;

      int leftIndex = 0, rightIndex = 0, mergeIndex = 0;

      while (leftIndex < leftHalfSize && rightIndex < rightHalfSize) {
         if ((leftHalf[leftIndex].compareTo(rightHalf[rightIndex])) <= 0) {
            array[mergeIndex++] = leftHalf[leftIndex++];
         } else {
            array[mergeIndex++] = rightHalf[rightIndex++];
         }

      }

      while (leftIndex < leftHalfSize) {
         array[mergeIndex++] = leftHalf[leftIndex++];

      }
      while (rightIndex < rightHalfSize) {
         array[mergeIndex++] = rightHalf[rightIndex++];

      }

   }
  @SuppressWarnings("unchecked")
   private static <E> void mergeSortComparator(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
       int arrayLength = toIndex - fromIndex;
   
       if (arrayLength < 2) {
           return;
       }
   
       int middlePointer = arrayLength / 2;
       E[] leftHalf = (E[]) Array.newInstance(array.getClass().getComponentType(), middlePointer);
       E[] rightHalf = (E[]) Array.newInstance(array.getClass().getComponentType(), arrayLength - middlePointer);
   
       // Copy the arrays from the specified range
       for (int index = 0; index < middlePointer; index++) {
           leftHalf[index] = array[fromIndex + index];
       }
   
       for (int index = middlePointer; index < arrayLength; index++) {
           rightHalf[index - middlePointer] = array[fromIndex + index];
       }
   
       // Recursive calls for both halves within the specified range
       mergeSortComparator(leftHalf, 0, middlePointer, comparator);
       mergeSortComparator(rightHalf, 0, arrayLength - middlePointer, comparator);
   
       // Merge the two halves back into the original array within the specified range
       mergeComparator(array, leftHalf, rightHalf, comparator, fromIndex);
   }
   
   // helper method for the mergeSortAlgorithm using the Comparator
   private static <E> void mergeComparator(E[] array, E[] leftHalf, E[] rightHalf, Comparator<E> comparator, int fromIndex) {
      int leftHalfSize = leftHalf.length;
      int rightHalfSize = rightHalf.length;
  
      int leftIndex = 0, rightIndex = 0, mergeIndex = fromIndex;
  
      while (leftIndex < leftHalfSize && rightIndex < rightHalfSize) {
          if (comparator.compare(leftHalf[leftIndex], rightHalf[rightIndex]) <= 0) {
              array[mergeIndex++] = leftHalf[leftIndex++];
          } else {
              array[mergeIndex++] = rightHalf[rightIndex++];
          }
      }
  
      while (leftIndex < leftHalfSize) {
          array[mergeIndex++] = leftHalf[leftIndex++];
      }
      while (rightIndex < rightHalfSize) {
          array[mergeIndex++] = rightHalf[rightIndex++];
      }
  }
  

}
