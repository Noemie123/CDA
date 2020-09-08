import java.util.Arrays;

public class SupprTableau {

    public static int binarySearch(int tab[], int val){
        int first = 0;
        int last = tab.length;

        int mid = (first + last)/2;
        while(first <= last){
            if (tab[mid] < val){
                first = mid + 1;
            }else if(tab[mid] == val){
                return mid;
            }else{
                last = mid - 1;
            }
            mid = (first + last)/2;
        }
        return -1;
    }

//    public static int findIndexDich (int[] tab, int num) {
//
//        int end = tab.length;
//        int mid = end/2;
//
//
//        while (mid > 0) {
//            if (tab[mid] > num) {
//                mid = mid/2;
//            } else if (tab[mid] < num) {
//                mid += mid/2;
//            } else {
//                return mid;
//            }
//        }
//
//        return -1;
//    }


    public static int findIndex (int[] tab, int num) {
        for (int i = 0; i < tab.length; i++) {
            if (num == tab[i]) {
                return i;
            }
        }

        return -1;

    }

    public static void main(String[] arg0) {
        int[] tableau = {1, 5, 7, 9, 11};
        int[] tableau2 = new int[tableau.length-1];
        int compt = 0;

        for (int i = 0; i < tableau.length; i++) {
            System.out.print(tableau[i] + " ");
        }

        Utility.Print("\nQuel nombre voulez-vous supprimer ?");
        int userNumber = Utility.ScanInt();


        // research with dich
        int ind = binarySearch(tableau, userNumber);

//         research with 1st method (not dich)
//        int index = findIndex(tableau, userNumber);



        if (ind == -1 ) {
            Utility.Print("Nombre pas dans le tableau.");
        } else {
            for (int j = 0; j < ind; j++) {
                tableau2[compt] = tableau[j];
                compt++;
            }
            for (int j = ind+1; j < tableau.length; j++) {
                tableau2[compt] = tableau[j];
                compt++;
            }

            for (int i = 0; i < tableau2.length; i++) {
                System.out.print(tableau2[i] + " ");
            }

        }


    }
}
