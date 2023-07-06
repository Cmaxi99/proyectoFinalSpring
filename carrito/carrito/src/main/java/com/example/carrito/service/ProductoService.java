package com.example.carrito.service;

import com.example.carrito.persistence.entities.Producto;
import com.example.carrito.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public String guardar(Producto producto){
        if(productoRepository.save(producto) != null){
            return "OK";
        }
        return null;
    }

    public Optional<Producto> buscarProductoPorId(Integer id){
        Optional<Producto> producto = Optional.empty();
        producto = productoRepository.findById(id);
        return producto;
    }

    public boolean eliminarProducto(Integer id){
        if (buscarProductoPorId(id).isPresent()){
            Producto producto = buscarProductoPorId(id).get();
            producto.setEstado(false);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public boolean reactivarProducto(Integer id){
        if (buscarProductoPorId(id).isPresent()){
            Producto producto = buscarProductoPorId(id).get();
            producto.setEstado(true);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public boolean modificarProducto(Integer id){
        return buscarProductoPorId(id).isPresent();
    }

    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }

    public <T> T existencia (T parametroActualizado, T parametroActualizar){
        if (parametroActualizado != null){
            if (!parametroActualizado.equals(0)){
                return parametroActualizado;
            }
        }
        return parametroActualizar;
    }
}
