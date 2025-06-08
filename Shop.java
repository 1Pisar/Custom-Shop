import java.util.Scanner;

public class Shop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Koszyk koszyk = new Koszyk();

        System.out.println("Witaj w sklepie odzieżowym!");

        boolean zakupy = true;
        while (zakupy) {
            System.out.println("\n== MENU GŁÓWNE ==");
            System.out.println("1. Dodaj produkt do koszyka");
            System.out.println("2. Przejdź do koszyka");
            System.out.println("3. Zakończ bez zakupów");

            int wybor = getChoice(scanner, 3);
            switch (wybor) {
                case 1 -> {
                    Produkt p = stworzProdukt(scanner);
                    koszyk.dodajProdukt(p);
                }
                case 2 -> przejdzDoKoszyka(scanner, koszyk);
                case 3 -> {
                    System.out.println("Dziękujemy, do zobaczenia!");
                    zakupy = false;
                }
            }
        }
    }

    public static void przejdzDoKoszyka(Scanner scanner, Koszyk koszyk) {
        boolean wKoszyku = true;
        while (wKoszyku) {
            koszyk.wyswietlKoszyk();
            System.out.println("\n== KOSZYK ==");
            System.out.println("1. Usuń produkt");
            System.out.println("2. Przejdź do płatności");
            System.out.println("3. Wróć do zakupów");

            int wybor = getChoice(scanner, 3);
            switch (wybor) {
                case 1 -> {
                    if (koszyk.czyPusty()) {
                        System.out.println("Koszyk jest pusty.");
                    } else {
                        System.out.print("Podaj numer produktu do usunięcia: ");
                        int nr = getChoice(scanner, Integer.MAX_VALUE) - 1;
                        koszyk.usunProdukt(nr);
                    }
                }
                case 2 -> {
                    koszyk.finalizujZakup();
                    wKoszyku = false;
                    System.exit(0); // kończy działanie programu po zakupie
                }
                case 3 -> wKoszyku = false;
            }
        }
    }

    public static Produkt stworzProdukt(Scanner scanner) {
        TypUbrania[] types = TypUbrania.values();
        System.out.println("Wybierz typ odzieży:");
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }
        int typeChoice = getChoice(scanner, types.length);
        TypUbrania selectedType = types[typeChoice - 1];

        String selectedSize;

        // Rozmiar zależny od typu odzieży
        if (selectedType == TypUbrania.BUTY) {
            System.out.print("Podaj swój rozmiar butów (np. 42): ");
            selectedSize = scanner.nextLine();
        } else if (selectedType == TypUbrania.CZAPKA) {
            selectedSize = "Uniwersalny";
            System.out.println("Rozmiar czapki ustawiono na: Uniwersalny");
        } else {
            String[] sizes = {"XS", "S", "M", "L", "XL", "XXL"};
            System.out.println("Wybierz rozmiar:");
            for (int i = 0; i < sizes.length; i++) {
                System.out.println((i + 1) + ". " + sizes[i]);
            }
            int sizeChoice = getChoice(scanner, sizes.length);
            selectedSize = sizes[sizeChoice - 1];
        }

        String selectedMaterialOrType;

        // Dla butów wybieramy typ, dla reszty materiał
        if (selectedType == TypUbrania.BUTY) {
            String[] shoeTypes = {"Sneakersy", "Trampki", "Klapki", "Sneakersy zimowe"};
            System.out.println("Wybierz typ butów:");
            for (int i = 0; i < shoeTypes.length; i++) {
                System.out.println((i + 1) + ". " + shoeTypes[i]);
            }
            int shoeChoice = getChoice(scanner, shoeTypes.length);
            selectedMaterialOrType = shoeTypes[shoeChoice - 1];
        } else {
            String[] materials = {"Bawełna", "Poliester", "Skóra", "Jeans"};
            System.out.println("Wybierz materiał:");
            for (int i = 0; i < materials.length; i++) {
                System.out.println((i + 1) + ". " + materials[i]);
            }
            int materialChoice = getChoice(scanner, materials.length);
            selectedMaterialOrType = materials[materialChoice - 1];
        }

        System.out.println("Czy chcesz dodać własną grafikę?");
        System.out.println("1. Tak");
        System.out.println("2. Nie, wybieram grafikę sklepu");
        int graphicChoice = getChoice(scanner, 2);

        boolean userGraphic = (graphicChoice == 1);
        String graphic;
        if (userGraphic) {
            System.out.print("Podaj nazwę pliku/grafiki: ");
            graphic = scanner.nextLine();
        } else {
            graphic = proposeStoreGraphic(selectedType);
            System.out.println("Zaproponowana grafika sklepu: " + graphic);
        }

        return new Produkt(selectedType, selectedSize, selectedMaterialOrType, userGraphic, graphic);
    }

    public static String proposeStoreGraphic(TypUbrania type) {
        return switch (type) {
            case KOSZULKA -> "CoolWave.png";
            case SPODNIE -> "UrbanStyle.jpg";
            case BUTY -> "SneakerFire.png";
            case KURTKA -> "MountainForce.jpg";
            case CZAPKA -> "WinterVibes.png";
            case BLUZA -> "StreetWear2025.png";
        };
    }

    public static int getChoice(Scanner scanner, int max) {
        int choice = 0;
        while (choice < 1 || choice > max) {
            System.out.print("Wybierz (1-" + (max == Integer.MAX_VALUE ? "N" : max) + "): ");
            while (!scanner.hasNextInt()) {
                System.out.print("To nie jest liczba. Spróbuj ponownie: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Czyści bufor
        }
        return choice;
    }
}
