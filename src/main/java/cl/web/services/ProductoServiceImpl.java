package cl.web.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.web.dto.ProductoDTO;
import cl.web.models.Producto;
import cl.web.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
    private ProductoRepository productoRepository;

	@Override
	public List<ProductoDTO> listarProductos() {
		return productoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
	}

	@Override
	public ProductoDTO obtenerPorId(Long id) {
		return productoRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
	}

	@Override
	public ProductoDTO crearProducto(ProductoDTO productoDTO) {
		Producto producto = mapToEntity(productoDTO);
        return mapToDTO(productoRepository.save(producto));
	}

	@Override
	public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
		Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		
		producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        return mapToDTO(productoRepository.save(producto));
	}

	@Override
	public void eliminarProducto(Long id) {
		productoRepository.deleteById(id);
		
	}
	
	//Métodos utilitarios para conversión de entidad a DTO y viceversa
    private Producto mapToEntity(ProductoDTO dto) {
        return new Producto(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getStock()
        );
    }
    
    private ProductoDTO mapToDTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
        );
    }

}
