package com.geekbrains.person.seller;

import com.geekbrains.person.Person;
import com.geekbrains.person.customer.Customer;
import com.geekbrains.product.Product;

import java.util.List;
import java.util.Objects;

public class Seller extends Person {
    private String name;
    private String lastName;
    private List<Product> products;

    public Seller() {

    }

    public Seller(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return name.equals(seller.name) && lastName.equals(seller.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    public boolean sellProducts(Customer customer, Product expectedProduct) {
//        for(Product product: products) {
//            // Проверяем по имени товара что у продавца есть продукт
//            if(product.getName().equals(expectedProduct.getName())) {
//                // Проверяем что количество товара >= чем мы хотим купить
//                if(product.getQuantity() >= expectedProduct.getQuantity()) {
//                    // Проверяем что кэш покупателя позволяет купить товар
//                    long requiredCash = (long) product.getPrice() * expectedProduct.getQuantity();
//                    if(customer.getCash() >= requiredCash) {
//                        // Уменьшаем количество продукта у продавца
//                        product.setQuantity(product.getQuantity() - expectedProduct.getQuantity());
//
//                        //Создаем новый объект для покупателя, чтобы ссылка не дублировалась
//                        Product customerProduct = new Product();
//                        customerProduct.setQuantity(expectedProduct.getQuantity());
//                        customerProduct.setName(expectedProduct.getName());
//
//                        //Добавляем количество продуктов у покупателя
//                        customer.addPurchase(customerProduct);
//                        //Увеличиваем кэш продавца
//                        setCash(getCash() + requiredCash);
//                        //Уменьшаем кэш покупателя
//                        customer.setCash(customer.getCash() - requiredCash);
//                        //Сообщаем потребителю метода, что покупка совершена
//                        return true;
//                    }
//                }
//            }
//        }
        //рефакторинг кода - вынес валидации продукта в отдельный метод validate
        for (Product product : products) {
            boolean isValid = validate(product, expectedProduct, customer);
            if (isValid) {
                // Уменьшаем количество продукта у продавца
                product.setQuantity(product.getQuantity() - expectedProduct.getQuantity());

                //Создаем новый объект для покупателя, чтобы ссылка не дублировалась
                Product customerProduct = new Product();
                customerProduct.setQuantity(expectedProduct.getQuantity());
                customerProduct.setName(expectedProduct.getName());

                //Добавляем количество продуктов у покупателя
                customer.addPurchase(customerProduct);
                //Увеличиваем кэш продавца
                long requiredCash = (long) product.getPrice() * expectedProduct.getQuantity();
                setCash(getCash() + requiredCash);
                //Уменьшаем кэш покупателя
                customer.setCash(customer.getCash() - requiredCash);
                //Сообщаем потребителю метода, что покупка совершена
                return true;
            }
        }
        return false;
    }

    private boolean validate(Product product, Product expectedProduct, Customer customer) {
        // Проверяем по имени товара что у продавца есть продукт
        if (!product.getName().equals(expectedProduct.getName())) {
            return false;
        }
        // Проверяем что количество товара >= чем мы хотим купить
        if (product.getQuantity() < expectedProduct.getQuantity()) {
            return false;
        }
        // Проверяем что кэш покупателя позволяет купить товар
        long requiredCash = (long) product.getPrice() * expectedProduct.getQuantity();
        if (customer.getCash() < requiredCash) {
            return false;
        }

        return true;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
