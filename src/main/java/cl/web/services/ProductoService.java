package cl.web.services;

import java.util.List;

import cl.web.dto.ProductoDTO;

public interface ProductoService {
    List<ProductoDTO> listarProductos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
