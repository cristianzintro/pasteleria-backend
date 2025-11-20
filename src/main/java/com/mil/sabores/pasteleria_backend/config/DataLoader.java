package com.mil.sabores.pasteleria_backend.config;

import com.mil.sabores.pasteleria_backend.model.Producto;
import com.mil.sabores.pasteleria_backend.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initProductos(ProductoRepository repo) {
        return args -> {

            if (repo.count() == 0) {

                repo.save(new Producto(
                        1,
                        "TC001",
                        "Torta Cuadrada de Chocolate",
                        "Tortas Cuadradas",
                        45000,
                        "Deliciosa torta de chocolate con ganache y avellanas.",
                        "https://images.unsplash.com/photo-1605478059449-4f02a1e8e3b7?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        2,
                        "TC002",
                        "Torta Redonda de Frutilla",
                        "Tortas Redondas",
                        42000,
                        "Bizcocho esponjoso con crema y frutillas frescas.",
                        "https://images.unsplash.com/photo-1601972599720-3ad0f352e6ce?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        3,
                        "TC003",
                        "Cheesecake de Maracuyá",
                        "Cheesecakes",
                        48000,
                        "Cheesecake cremoso con salsa de maracuyá natural.",
                        "https://images.unsplash.com/photo-1612198791318-b8131e5c36ad?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        4,
                        "TC004",
                        "Torta de Frambuesa y Crema",
                        "Tortas Frutales",
                        43000,
                        "Torta fresca con crema batida y frambuesas naturales.",
                        "https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        5,
                        "TC005",
                        "Torta Oreo",
                        "Tortas Especiales",
                        47000,
                        "Bizcocho de chocolate con crema Oreo y topping crujiente.",
                        "https://images.unsplash.com/photo-1584432810601-6c7f27a02e8c?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        6,
                        "TC006",
                        "Torta Tres Leches",
                        "Tortas Tradicionales",
                        39000,
                        "Torta húmeda clásica bañada en tres leches y merengue.",
                        "https://images.unsplash.com/photo-1617196037300-6e3c3b9c7f67?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        7,
                        "TC007",
                        "Torta Selva Negra",
                        "Tortas Premium",
                        52000,
                        "Bizcocho de chocolate con crema chantilly y cerezas.",
                        "https://images.unsplash.com/photo-1551024601-bec78aea704b?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        8,
                        "TC008",
                        "Pie de Limón",
                        "Pies",
                        35000,
                        "Clásico pie de limón con merengue tostado.",
                        "https://images.unsplash.com/photo-1608198093002-ad4e00548400?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        9,
                        "TC009",
                        "Torta Tiramisú",
                        "Tortas Premium",
                        51000,
                        "Tiramisú tradicional con café, cacao y queso mascarpone.",
                        "https://images.unsplash.com/photo-1598373182133-43d41c2c3e6e?auto=format&fit=crop&w=600&q=80"
                ));

                repo.save(new Producto(
                        10,
                        "TC010",
                        "Torta de Vainilla con Buttercream",
                        "Tortas Clásicas",
                        41000,
                        "Torta suave de vainilla con buttercream casero.",
                        "https://images.unsplash.com/photo-1599785209707-28c93a64c40f?auto=format&fit=crop&w=600&q=80"
                ));
            }
        };
    }
}
