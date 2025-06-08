import java.util.ArrayList;

public class Koszyk {
    private ArrayList<Produkt> produkty = new ArrayList<>();

    public void dodajProdukt(Produkt produkt) {
        produkty.add(produkt);
        System.out.println("Produkt dodany do koszyka.\n");
    }

    public void wyswietlKoszyk() {
        if (produkty.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
            return;
        }
        System.out.println("\n=== Zawartość koszyka ===");
        for (int i = 0; i < produkty.size(); i++) {
            System.out.print((i + 1) + ". ");
            produkty.get(i).displayDetails();
        }
    }

    public void usunProdukt(int index) {
        if (index >= 0 && index < produkty.size()) {
            produkty.remove(index);
            System.out.println("Produkt usunięty z koszyka.\n");
        } else {
            System.out.println("Nieprawidłowy numer produktu.");
        }
    }

    public boolean czyPusty() {
        return produkty.isEmpty();
    }

    public void finalizujZakup() {
        if (produkty.isEmpty()) {
            System.out.println("Koszyk jest pusty. Nie można sfinalizować zakupu.");
        } else {
            System.out.println("\nDziękujemy za zakupy! Oto twoje zamówienie:");
            wyswietlKoszyk();
        }
    }
}
