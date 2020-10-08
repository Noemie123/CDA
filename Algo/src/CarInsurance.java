public class CarInsurance {

    public static void main(String[] arg0) {
        Utility.Print("Quel est votre age ?");
        int userAge = Utility.ScanInt();

        Utility.Print("Depuis combien d'années vous avez le permis ?");
        int userYear = Utility.ScanInt();

        Utility.Print("Combien accident responsable ?");
        int userAccNumber = Utility.ScanInt();

        Utility.Print("Depuis quand êtes-vous chez nous ?");
        int userAnciennete = Utility.ScanInt();

        String color = null;
        int price = 0;


        if (userAge > 25) {
            if (userYear > 2) {
                if (userAccNumber == 0) {
                    color = "vert";
                    price = 100;
                } else if (userAccNumber == 1) {
                    color = "orange";
                    price = 150;
                } else if (userAccNumber == 2) {
                    color = "rouge";
                    price = 200;
                } else {
                    Utility.Print("Vous n'êtes pas assurable.");
                    return;
                }
            } else {
                if (userAccNumber >= 1) {
                    color = "rouge";
                    price = 200;
                } else {
                    color = "orange";
                    price = 150;
                }
            }
        } else {
            if (userYear > 2) {
                if (userAccNumber >= 1) {
                    color = "rouge";
                    price = 200;
                } else {
                    color = "orange";
                    price = 150;
                }
            } else {
                if (userAccNumber >= 1) {
                    Utility.Print("Vous n'êtes pas assurable.");
                    return;
                } else {
                    color = "rouge";
                    price = 200;
                }
            }
        }


        // upgrade if here long enough (5+)
        String finalColor = color;

        if (userAnciennete > 5) {
            switch (color) {
                case "rouge":
                    finalColor = "orange";
                    price = 150;
                    break;
                case "orange":
                    finalColor = "vert";
                    price = 100;
                    break;
                case "vert":
                    finalColor = "bleu";
                    price = 50;
                    break;
            }

        }

        // percent off if between 0 to 10 years (50%)
        int newPrice = price;
        if (userYear > 0 && userYear <= 10) {
            newPrice = price - ((price * userYear) / 100);
        }


        Utility.Print("Vous bénéficiez du contrat " + finalColor + ". Vous paierez " + newPrice + " euros par mois.");
    }
}
