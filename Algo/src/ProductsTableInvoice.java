import java.util.ArrayList;

class Products {
    String name;
    Integer price;
    Integer quantity;

    public String getNom() {
        return this.name ;
    }

    public void setNom(String nom) {
        this.name = nom ;
    }

    public Integer getPrice() {
        return this.price ;
    }

    public void setPrice(Integer prix) {
        this.price = prix ;
    }

    public Integer getQuantity() {
        return this.quantity ;
    }

    public void setQuantity(Integer qty) {
        this.quantity = qty ;
    }

    int calculatePrice () {
        return this.price*this.quantity;
    }

}


public class ProductsTableInvoice {


    public static void main(String[] arg0) {

        // THIRD VERSION
        ArrayList<Products> productList = new ArrayList<>();

        Products tomatoes = new Products();
        tomatoes.setNom("tomates");
        tomatoes.setPrice(2);
        tomatoes.setQuantity(0);
        productList.add(tomatoes);

        Products potatoes = new Products();
        potatoes.setNom("pommes de terre");
        potatoes.setPrice(1);
        potatoes.setQuantity(0);
        productList.add(potatoes);

        Products peppers = new Products();
        peppers.setNom("poivrons");
        peppers.setPrice(4);
        peppers.setQuantity(0);
        productList.add(peppers);

        Products onions = new Products();
        onions.setNom("oignons");
        onions.setPrice(1);
        onions.setQuantity(0);
        productList.add(onions);


        // display the choices
        int userChoice = 0;


        while (userChoice != 4) {
            int choicePossibility = 1;
            Utility.Print("Faites un choix. (5 pour arrêter)");
            for (Products products : productList) {
                Utility.Print(choicePossibility + ". " + products.getNom() + " à " + products.getPrice() + "€/kg.");
                choicePossibility++;
            }
            userChoice = Utility.ScanInt();
            userChoice = userChoice-1;

            if (userChoice == 0 || userChoice == 1 || userChoice == 2 || userChoice == 3) {
                // set new quantity
                Utility.Print("Vous avez choisi " + productList.get(userChoice).getNom() + ". Quelle quantité ?");
                Integer userQuantity = Utility.ScanInt();

                int oldQuant = productList.get(userChoice).getQuantity();
                int newQuant = oldQuant + userQuantity;
                productList.get(userChoice).setQuantity(newQuant);
            }
        }

        // calculate the price
        int prixFinal = 0;

        for (Products prod : productList) {
            if (!(prod.getQuantity() == 0)) {
                int prix = prod.calculatePrice();
                Utility.Print(prod.getNom() + "(" + prod.getQuantity() + "kg à " + prod.getPrice() + "€/kg)" + " ......... " + prix + "€");
                prixFinal += prix;
            }
        }

        if (prixFinal != 0) {
            Utility.Print("Prix final de " + prixFinal + "€");
        } else {
            Utility.Print("Vous n'avez rien commandé.");
        }



        // SECOND VERSION
//        String[][] productsTable = {{"tomates", "2", "0"}, {"pommes de terre", "1", "0"}, {"poivrons", "4", "0"}, {"oignons", "1", "0"}};
//
//        for (int i = 0; i <productsTable.length; i++) {
//            Utility.Print("Voulez-vous des " + productsTable[i][0] + " à " + productsTable[i][1] + " €/kg ? (oui/non)");
//            String userAnswer = Utility.Scan();
//
//            // check if yes and if so add quantity
//            if (userAnswer.equals("oui")) {
//                Utility.Print("Quelle quantité (en kilos)?");
//                String userQuantity = Utility.Scan();
//
//                productsTable[i][2] = String.valueOf(Integer.parseInt(userQuantity));
//            } else if (userAnswer.equals("non")) {
//                Utility.Print("Suivant.");
//            } else {
//                ProductsTableInvoice.main(arg0);
//            }
//
//        }
//
//        int prixFinal = 0;
//
//        for (int i = 0; i < productsTable.length; i++) {
//            if (!productsTable[i][2].equals("0")){
//                int prix = Integer.parseInt(String.valueOf(productsTable[i][1])) * Integer.parseInt(String.valueOf(productsTable[i][2]));
//                Utility.Print(productsTable[i][0] + "(" + productsTable[i][2] + "kg à " + productsTable[i][1] + "€/kg)" + " ......... " + prix + "€");
//                prixFinal += prix;
//            }
//
//        }
//
//        Utility.Print("Prix final de " + prixFinal + "€");


        // FIRST VERSION

//        final int tomatoesPrice = 2;
//        final int pdtPrice = 1;
//        final int pepperPrice = 4;
//        final int onionPrice = 1;
//
//        Object[][] productsTable = {{"tomates", tomatoesPrice}, {"pommes de terre", pdtPrice}, {"poivrons", pepperPrice}, {"oignons", onionPrice}};
//        Object[][] invoiceTable = new Object[4][3];
//        int j = 0;
//
//        for (int i = 0; i < productsTable.length; i++) {
//            Utility.Print("Voulez-vous des " + productsTable[i][0] + " à " + productsTable[i][1] + " €/kg ? (oui/non)");
//            String userAnswer = Utility.Scan();
//
//            if (userAnswer.equals("oui")) {
//                Utility.Print("Quelle quantité (en kilos)?");
//                Integer userQuantity = Utility.ScanInt();
//
//                invoiceTable[j][0] = productsTable[i][0];
//                invoiceTable[j][1] = productsTable[i][1];
//                invoiceTable[j][2] = userQuantity;
//
//                j++;
//            } else if (userAnswer.equals("non")) {
//                Utility.Print("Suivant.");
//            } else {
//                ProductsTableInvoice.main(arg0);
//            }
//        }
//
//        if (j == 0) {
//            Utility.Print("Vous n'avez rien commandé.");
//        } else {
//            int prixFinal = 0;
//
//            for (int i = 0; i < j; i++) {
//
//                int prix = Integer.parseInt(String.valueOf(invoiceTable[i][1])) * Integer.parseInt(String.valueOf(invoiceTable[i][2]));
//                Utility.Print(invoiceTable[i][0] + "(" + invoiceTable[i][2]  + "kg à " + invoiceTable[i][1] + "€/kg)" + " ......... " + prix + "€");
//
//                prixFinal += prix;
//            }
//
//            Utility.Print("Prix final de " + prixFinal + "€");
//        }

    }

}
