import java.util.ArrayList;
import java.util.Arrays;

public class fusionTableau {



    public static void main(String[] arg0) {
        int[] tableau2 = {-40, -10, 18, 29, 55};
        int[] tableau1 = {-55, -2, 4, 57, 99};
        int lengthTab3 = tableau1.length+tableau2.length;
        int[] tableau3 = new int[lengthTab3];


        // sort while merging
        int j = 0;
        int k = 0;

        for (int i = 0; i < lengthTab3; i++) {
            if (j > tableau1.length-1) {
                tableau3[i] = tableau2[k];
                k++;
            } else if ( k > tableau2.length-1) {
                tableau3[i] = tableau1[j];
                j++;
            } else if (tableau1[j] <= tableau2[k]) {
                tableau3[i] = tableau1[j];
                j++;
            } else if (tableau1[j] > tableau2[k]) {
                tableau3[i] = tableau2[k];
                k++;
            }
        }




        // merge and then sort

//        /*Concaténation*/
//        for(int i = 0 ; i < tableau1.length; i++) {
//            tableau3[i] = tableau1[i];
//        }
//
//        for(int i = 0 ; i < tableau2.length; i++) {
//            tableau3[i + tableau1.length] = tableau2[i];
//        }
//
//        Arrays.sort(tableau3);
        for(int i = 0 ; i < tableau3.length; i++) {
            System.out.print(tableau3[i] + " ");
        }
    }
}
