package main;

import java.util.ArrayList;

//import java.util.Arrays;
import model.Product;
import model.Sale;
import java.util.Scanner;
import model.Amount;
import model.Employee;

public class Shop {

    private Amount cash = new Amount(100.00);
    public static ArrayList<Product> inventory = new ArrayList<>();
    private int numberProducts;
    private static ArrayList<Sale> sales = new ArrayList<>();
    int counterSales = 0;
    private Employee employee1 = null;

    final static double TAX_RATE = 1.04;

    public Shop() {
    }

    public Amount getCash() {
        return cash;
    }

    public void setCash(Amount cash) {
        this.cash = cash;
    }

    public static void main(String[] args) {
        Shop shop = new Shop();

        shop.loadInventory();
        shop.initSession();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean exit = false;

        do {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("Menu principal miTienda.com");
            System.out.println("===========================");
            System.out.println("1) Contar caja");
            System.out.println("2) A\u00f1adir producto");
            System.out.println("3) A\u00f1adir stock");
            System.out.println("4) Marcar producto proxima caducidad");
            System.out.println("5) Ver inventario");
            System.out.println("6) Venta");
            System.out.println("7) Ver ventas");
            System.out.println("8) Todas las ventas");
            System.out.println("9) Eliminar un producto");
            System.out.println("10) Salir programa");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    shop.showCash();
                    break;

                case 2:
                    shop.addProduct();
                    break;

                case 3:
                    shop.addStock();
                    break;

                case 4:
                    shop.setExpired();
                    break;

                case 5:
                    shop.showInventory();
                    break;

                case 6:
                    shop.sale();
                    break;

                case 7:
                    shop.showSales();
                    break;

                case 8:
                    shop.totalAmountOfSales();
                    break;

                case 9:
                    shop.removeProduct();
                    break;

                case 10:
                    exit = true;
                    break;

            }
        } while (!exit);
    }

    private void initSession() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Autenticación de empleado");

        boolean authenticated = false;

        while (!authenticated) {
            System.out.println("Ingrese número de empleado: ");
            int employeeId = scanner.nextInt();

            System.out.println("Ingrese contraseña: ");
            String password = scanner.next();
            scanner.nextLine();

            Employee employee = new Employee(employeeId, password, "Empleado");

            if (employee.login(employeeId, password)) {
                employee1 = employee;
                System.out.println("Bienvenido, empleado " + employeeId);
                System.out.println("Sesión iniciada ");
                authenticated = true;

            } else {
                System.out.println("ERROR: Numero de empleado o contraseña incorrectos.");

            }
        }
    }

    /**
     * load initial inventory to shop
     */
    public void loadInventory() {
        addProduct(new Product("Manzana", 10.00, true, 10));
        addProduct(new Product("Pera", 20.00, true, 20));
        addProduct(new Product("Hamburguesa", 30.00, true, 30));
        addProduct(new Product("Fresa", 5.00, true, 20));
    }

    /**
     * show current total cash
     */
    private void showCash() {
        System.out.println("Dinero actual: " + cash);
    }

    /**
     * add a new product to inventory getting data from console
     */
    public void addProduct() {

//        if (isInventoryFull()) {
//            System.out.println("ERROR: No se pueden añadir más productos");
//            return;
//        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        // check if product already exists BEFORE adding
        if (findProduct(name) != null) {
            System.out.println("ERROR: Este producto ya existe");
            return;
        }

        System.out.print("Precio mayorista: ");
        double wholesalerPrice = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();

        addProduct(new Product(name, wholesalerPrice, true, stock));
    }

    /**
     * add stock for a specific product
     */
    public void addStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.nextLine();
        Product product = findProduct(name);

        if (product != null) {
            // ask for stock
            System.out.print("Seleccione la cantidad a añadir: ");
            int stock = scanner.nextInt();
            // update stock product
            product.setStock(product.getStock() + stock);
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

        } else {
            System.out.println("ERROR: No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * set a product as expired
     */
    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product product = findProduct(name);

        if (product != null) {
            //product.setPublicPrice(0);
            product.expire();
            System.out.println("El producto " + name + " ha sido marcado como caducado");

        }
    }

    /**
     * show all inventory
     */
    public void showInventory() {
        System.out.println("Contenido actual de la tienda:");
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    /**
     * make a sale of products to a client
     */
    public void sale() {
        ArrayList<Product> shoppingcart = new ArrayList<>();

        // ask for client name
        Scanner sc = new Scanner(System.in);
        System.out.println("Realizar venta, escribir nombre cliente");
        String client = sc.nextLine();

        // sale product until input name is not 0
        double totalAmount = 0.0;
        String name = "";
        while (!name.equals("0")) {
            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = sc.nextLine();

            if (name.equals("0")) {
                break;
            }
            Product product = findProduct(name);
            boolean productAvailable = false;
            if (product != null && product.isAvailable()) {
                productAvailable = true;
                shoppingcart.add(product);
                totalAmount += product.getPublicPrice();
                product.setStock(product.getStock() - 1);
                // if no more stock, set as not available to sale
                if (product.getStock() == 0) {
                    product.setAvailable(false);
                }

                System.out.println("Producto añadido con exito");
            }

            if (!productAvailable) {
                System.out.println("ERROR: Producto no encontrado o sin stock");
            }
        }

        // show cost total
        totalAmount = totalAmount * TAX_RATE;
        cash.setValue(cash.getValue() + totalAmount);
        System.out.println("Venta realizada con exito, total: " + totalAmount);
        sales.add(new Sale(client, shoppingcart, new Amount(totalAmount)));
        counterSales++;
    }

    /**
     * show all sales
     */
    private void showSales() {
        System.out.println("Lista de ventas:");
        if (sales != null) {
            for (Sale sale : sales) {

                System.out.println(sale);
            }
        }
    }

    /**
     * delete a product from inventory
     */
    private void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione el nombre del producto a eliminar: ");
        String name = scanner.nextLine();

        Product product = findProduct(name);
        if (product != null) {
            inventory.remove(product);
            System.out.println("Producto " + name + " eliminado con éxito");
        } else {
            System.out.println("ERROR: No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * add a product to inventory
     *
     * @param product
     */
    public void addProduct(Product product) {
//        if (isInventoryFull()) {
//            System.out.println("ERROR: No se pueden añadir mas productos, se ha alcanzado el maximo de " + inventory.length);
//            return;
//        }
        inventory.add(numberProducts, product);
        numberProducts++;
        System.out.println("Producto añadido");
    }

    /**
     * check if inventory is full or not
     *
     * @return true if inventory is full
     */
    // esta funcion es util por si por si proximamente es necesario añadir un limite en el inventario
//    public boolean isInventoryFull() {
//        if (numberProducts == 10) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    /**
     * find product by name
     *
     * @param name
     * @return product found by name
     */
    public Product findProduct(String name) {
        for (Product inventory1 : inventory) {
            if (inventory1 != null && inventory1.getName().equalsIgnoreCase(name)) {
                return inventory1;
            }
        }
        return null;
    }

    public void totalAmountOfSales() {
        if (counterSales == 0) {
            System.out.println("No se han realizado ventas todavía.");
            return;
        }

        double total = 0;
        System.out.println("Lista de ventas realizadas:");

        for (int i = 0; i < counterSales; i++) {
            Sale sale = sales.get(i);
            System.out.println(sale);
            total += sale.getAmount().getValue();
        }

        System.out.println("Número total de ventas: " + counterSales);
        System.out.println("Valor total de todas las ventas: " + total + " ?");
    }

}
