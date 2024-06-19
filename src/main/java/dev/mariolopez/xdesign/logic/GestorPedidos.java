/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.mariolopez.xdesign.Logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mariolopez
 */

public class GestorPedidos {
    private static GestorPedidos instance;
    private ArrayList<Pedido> listaPedidos;
    private int nextId = 1;

    
    public static GestorPedidos getInstance() {
        if (instance == null) {
            instance = new GestorPedidos();
        }
        return instance;
    }
    
    public ArrayList<Pedido> getListaPedidos() {
    return listaPedidos;
}

    // Constructor
    public GestorPedidos() {
        listaPedidos = new ArrayList<>();
    }

    // Agregar un pedido
    public void agregarPedido(Pedido pedido) {
        if (pedido.getId() == 0) { // Nueva orden, asigna un ID
            pedido.setId(nextId++);
        }
        listaPedidos.add(pedido);
    }
    
    
    public void actualizarPedido(Pedido pedidoActualizado) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getId() == pedidoActualizado.getId()) {
                listaPedidos.set(i, pedidoActualizado);
                return;
            }
        }
    }
    
    // Buscar pedidos por número de teléfono del cliente
    public List<Pedido> buscarPedidosPorTelefono(String numeroTelefono) {
    int telefonoInt;
    try {
        telefonoInt = Integer.parseInt(numeroTelefono);
    } catch (NumberFormatException e) {
        System.out.println("Número de teléfono no válido");
        return new ArrayList<>();  // Retorna lista vacía
    }
    List<Pedido> resultados = new ArrayList<>();
    for (Pedido pedido : listaPedidos) {
        if (pedido.getNumeroTelefonicoCliente() == telefonoInt) {
            resultados.add(pedido);
        }
    }
    return resultados;
}


    // Ordenamiento por inserción
    public void ordenarPorTelefonoInsercion() {
        for (int i = 1; i < listaPedidos.size(); i++) {
            Pedido actual = listaPedidos.get(i);
            int j = i - 1;
            while (j >= 0 && listaPedidos.get(j).getNumeroTelefonicoCliente() > actual.getNumeroTelefonicoCliente()) {
                listaPedidos.set(j + 1, listaPedidos.get(j));
                j--;
            }
            listaPedidos.set(j + 1, actual);
        }
    }

    // Ordenamiento por mezcla (Merge Sort)
    public void ordenarPorCodigoDisenoMergeSort() {
        if (listaPedidos.size() < 2) {
            return;
        }
        int mid = listaPedidos.size() / 2;
        ArrayList<Pedido> leftHalf = new ArrayList<>(listaPedidos.subList(0, mid));
        ArrayList<Pedido> rightHalf = new ArrayList<>(listaPedidos.subList(mid, listaPedidos.size()));

        GestorPedidos left = new GestorPedidos();
        left.listaPedidos = leftHalf;
        left.ordenarPorCodigoDisenoMergeSort();

        GestorPedidos right = new GestorPedidos();
        right.listaPedidos = rightHalf;
        right.ordenarPorCodigoDisenoMergeSort();

        merge(listaPedidos, left.listaPedidos, right.listaPedidos);
    }

    private void merge(ArrayList<Pedido> orders, ArrayList<Pedido> left, ArrayList<Pedido> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getCodigoDiseno().compareTo(right.get(j).getCodigoDiseno()) > 0) {
                orders.set(k++, left.get(i++));
            } else {
                orders.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            orders.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            orders.set(k++, right.get(j++));
        }
    }
    
    // Método para buscar un pedido por ID
    public Pedido findPedidoById(int id) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;  // Retornar null si no se encuentra el pedido
    }
    
    // Listar todos los pedidos
    public void listarPedidos() {
        Iterator<Pedido> it = listaPedidos.iterator();
        while (it.hasNext()) {
            Pedido pedido = it.next();
            System.out.println(pedido);
        }
    }
    
    // Editar un pedido
    public void editarPedido(int idPedido, Pedido nuevosDatos) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getId() == idPedido) {
                listaPedidos.set(i, nuevosDatos);
                break;
            }
        }
    }
    
}
