import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Customer> users = entityManager.createQuery("from Customer", Customer.class).getResultList();
        List<Product> products = entityManager.createQuery("from Product", Product.class).getResultList();
        System.out.println(users.toString());
        System.out.println(products.toString());
        entityManager.getTransaction().commit();
        entityManager.close();

        int choice = 0;

        while (choice != 5) {

            System.out.println("Введите номер операции:\n1 - найти товары, купленные клиентом;\n2 - найти клиентов, купивших определенный товар;" +
                    "\n3 - удалить клиента;\n4 - удалить товар;\n5 - выйти;");

            Scanner in = new Scanner(System.in);
            Scanner inText = new Scanner(System.in);
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Введите имя клиента");
                    String customerName = inText.nextLine();
                    entityManager = entityManagerFactory.createEntityManager();
                    entityManager.getTransaction().begin();
                    try {
                        Customer customer = entityManager.createQuery("from Customer where name = :name", Customer.class)
                                .setParameter("name", customerName)
                                .getSingleResult();
                        System.out.println(customer);
                        for (Product p : customer.getProductsCustomer()) {
                            System.out.println(p.getTitle());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    } finally {
                        entityManager.getTransaction().commit();
                        entityManager.close();
                    }
                    break;
                case 2:
                    System.out.println("Введите наименование товара");
                    String productTitle = inText.nextLine();
                    entityManager = entityManagerFactory.createEntityManager();
                    entityManager.getTransaction().begin();
                    try {
                        Product product = entityManager.createQuery("from Product where title = :title", Product.class)
                                .setParameter("title", productTitle)
                                .getSingleResult();
                        for (Customer c : product.getCustomerList()) {
                            System.out.println(c.getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    } finally {
                        entityManager.getTransaction().commit();
                        entityManager.close();
                    }
                    break;
                case 3:
                    System.out.println("Введите имя клиента");
                    customerName = inText.nextLine();
                    entityManager = entityManagerFactory.createEntityManager();
                    entityManager.getTransaction().begin();
                    try {
                        Customer customer = entityManager.createQuery("from Customer where name = :name", Customer.class)
                                .setParameter("name", customerName)
                                .getSingleResult();
                        entityManager.remove(customer);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    } finally {
                        entityManager.getTransaction().commit();
                        entityManager.close();
                    }
                    break;
                case 4:
                    System.out.println("Введите id товара");
                    entityManager = entityManagerFactory.createEntityManager();
                    entityManager.getTransaction().begin();
                    try {
                        int product_id = in.nextInt();
                        Product product = entityManager.find(Product.class, product_id);
                        entityManager.remove(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    } finally {
                        entityManager.getTransaction().commit();
                        entityManager.close();
                    }
                    break;
                case 5:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Недопустимый номер операции: " + choice);
                    break;
            }
        }
    }
}
