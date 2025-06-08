public class Produkt {
    private TypUbrania type;
    private String size;
    private String material;
    private boolean userGraphic;
    private String graphic;

    public Produkt(TypUbrania type, String size, String material, boolean userGraphic, String graphic) {
        this.type = type;
        this.size = size;
        this.material = material;
        this.userGraphic = userGraphic;
        this.graphic = graphic;
    }

    public void displayDetails() {
        System.out.println("=== Szczegóły produktu ===");
        System.out.println("Typ: " + type);
        System.out.println("Rozmiar: " + size);
        System.out.println("Materiał: " + material);
        System.out.println("Grafika: " + (userGraphic ? "Własna – " + graphic : "Sklepowa – " + graphic));
    }
}