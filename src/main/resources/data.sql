/* Script de inicialización para H2 Database (Pastelería Mil Sabores) */

-- 1. Eliminar tabla si existe (limpia la BD en cada inicio)
DROP TABLE IF EXISTS productos;

-- 2. Crear la tabla (Crucial para H2)
CREATE TABLE productos (
    codigo VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),
    precio INT,
    descripcion VARCHAR(1000)
);

-- 3. Insertar los 16 productos (corregidos)
INSERT INTO productos (codigo, nombre, categoria, precio, descripcion) VALUES
('TC001', 'Torta Cuadrada de Chocolate', 'Tortas Cuadradas', 45000, 'Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales.'),
('TC002', 'Torta Cuadrada de Frutas', 'Tortas Cuadradas', 50000, 'Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones.'),
('TT001', 'Torta Circular de Vainilla', 'Tortas Circulares', 40000, 'Bizcocho de vainilla clásico relleno con crema pastelera y cubierto con un glaseado dulce, perfecto para cualquier ocasión.'),
('TT002', 'Torta Circular de Manjar', 'Tortas Circulares', 42000, 'Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos.'),
('PI001', 'Mousse de Chocolate', 'Postres Individuales', 5000, 'Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate.'),
('PI002', 'Tiramisú Clásico', 'Postres Individuales', 5500, 'Un postre italiano individual con capas de café, mascarpone y cacao, perfecto para finalizar cualquier comida.'),
('PSA001', 'Torta Sin Azúcar de Naranja', 'Productos Sin Azúcar', 48000, 'Torta ligera y deliciosa, endulzada naturalmente, ideal para quienes buscan opciones más saludables.'),
('PSA002', 'Cheesecake Sin Azúcar', 'Productos Sin Azúcar', 47000, 'Suave y cremoso, este cheesecake es una opción perfecta para disfrutar sin culpa.'),
('PT001', 'Empanada de Manzana', 'Pastelería Tradicional', 3000, 'Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda.'),
('PT002', 'Tarta de Santiago', 'Pastelería Tradicional', 6000, 'Tradicional tarta española hecha con almendras, azúcar, y huevos, una delicia para los amantes de los postres clásicos.'),
('PG001', 'Brownie Sin Gluten', 'Productos Sin Gluten', 4000, 'Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor.'),
('PG002', 'Pan Sin Gluten', 'Productos Sin Gluten', 3500, 'Suave y esponjoso, ideal para sandwiches o para acompañar cualquier comida.'),
('PV001', 'Torta Vegana de Chocolate', 'Productos Vegana', 50000, 'Torta de chocolate húmeda y deliciosa, hecha sin productos de origen animal, perfecta para veganos.'),
('PV002', 'Galletas Veganas de Avena', 'Productos Vegana', 4500, 'Crujientes y sabrosas, estas galletas son una excelente opción para un snack saludable y vegano.'),
('TE001', 'Torta Especial de Cumpleaños', 'Tortas Especiales', 55000, 'Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos.'),
('TE002', 'Torta Especial de Boda', 'Tortas Especiales', 60000, 'Elegante y deliciosa, esta torta está diseñada para ser el centro de atención en cualquier boda.');