import java.util.Arrays;

public class SortTable {

    public static void main(String[] args) {
        Integer[] intTable = {22, 49, -1, 0, 55, -22, 101};
        Integer temp;
        Integer min;
        int indiceMin;

        // using collections
//        Arrays.sort(intTable);
//        System.out.print(Arrays.toString(intTable));

        for (int i = 0 ; i < intTable.length-2; i++) {
            min = intTable[i];
            indiceMin = i;

            for (int j = i+1; j < intTable.length-1; j++) {
                if (min > intTable[j]) {
                    min = intTable[j];
                    indiceMin = j;
                }
            }

            if (i != indiceMin) {
                temp = intTable[i];
                intTable[i] = min;
                intTable[indiceMin] = temp;
            }
        }

        System.out.print(Arrays.toString(intTable));
    }
}
