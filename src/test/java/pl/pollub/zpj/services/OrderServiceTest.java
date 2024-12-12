package pl.pollub.zpj.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.crossstore.ChangeSetPersister;
import pl.pollub.zpj.DTOs.OrderDTO;
import pl.pollub.zpj.models.Orders;
import pl.pollub.zpj.repository.KamperRepository;
import pl.pollub.zpj.repository.OrderRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest{
    private OrderRepository orderRepository;
    private KamperRepository kamperRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp(){
        orderRepository = Mockito.mock(OrderRepository.class);
        kamperRepository = Mockito.mock(KamperRepository.class);
        orderService = new OrderService(orderRepository, kamperRepository);
    }

    @Test
    @DisplayName("Create order successfully")
    void testCreateOrder(){
        OrderDTO orderDTO = new OrderDTO();
        Orders order = new Orders();
        when(kamperRepository.findById(orderDTO.getKamperid())).thenReturn(null);
        when(orderRepository.save(any(Orders.class))).thenReturn(order);
        Orders createdOrder = orderService.createOrder(orderDTO);
        assertNotNull(createdOrder);
        verify(orderRepository, times(1)).save(any(Orders.class));
    }

    @Test
    @DisplayName("Throw exception for invalid order ID in update")
    void testUpdateOrderInvalidId(){
        int invalidId = 999;
        OrderDTO orderDTO = new OrderDTO();
        when(orderRepository.findById(invalidId)).thenReturn(java.util.Optional.empty());
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> orderService.updateOrder(invalidId, orderDTO));
        verify(orderRepository, times(1)).findById(invalidId);
    }

    @Test
    @DisplayName("Delete order successfully")
    void testDeleteOrderSuccess() throws ChangeSetPersister.NotFoundException{
        int validId = 1;
        Orders order = new Orders();
        when(orderRepository.findById(validId)).thenReturn(java.util.Optional.of(order));
        doNothing().when(orderRepository).delete(order);
        String result = orderService.deleteOrder(validId);
        assertEquals("success", result);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    @DisplayName("Throw exception when deleting non existent order")
    void testDeleteOrderNotFound(){
        int invalidId = 999;
        when(orderRepository.findById(invalidId)).thenReturn(java.util.Optional.empty());
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> orderService.deleteOrder(invalidId));
        verify(orderRepository, times(1)).findById(invalidId);
    }

    @Test
    @DisplayName("Handle finding orders by Kamper ID with no results")
    void testFindOrdersByKamperIdEmpty(){
        int kamperId = 1;
        when(orderRepository.findByKamperId(kamperId)).thenReturn(Collections.emptyList());
        Iterable<Orders> result = orderService.findByKamperId(kamperId);
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
        verify(orderRepository, times(1)).findByKamperId(kamperId);
    }

    @Test
    @DisplayName("Return empty list when no orders found")
    void testFindAllOrdersEmpty(){
        when(orderRepository.findAll()).thenReturn(Collections.emptyList());
        Iterable<Orders> orders = orderService.findAll();

        assertNotNull(orders);
        assertFalse(orders.iterator().hasNext());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Return order by valid ID")
    void testUpdateOrderSuccess() throws ChangeSetPersister.NotFoundException{
        int validId = 1;
        OrderDTO orderDTO = new OrderDTO();
        Orders order = new Orders();
        when(orderRepository.findById(validId)).thenReturn(java.util.Optional.of(order));
        when(kamperRepository.findById(orderDTO.getKamperid())).thenReturn(null);
        Orders result = orderService.updateOrder(validId, orderDTO);

        assertNotNull(result);
        verify(orderRepository, times(1)).findById(validId);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    @DisplayName("Handle order deletion when ID does not exist")
    void testDeleteOrderNonExistent(){
        int nonExistentId = 999;
        when(orderRepository.findById(nonExistentId)).thenReturn(java.util.Optional.empty());

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> orderService.deleteOrder(nonExistentId));
        verify(orderRepository, times(1)).findById(nonExistentId);
    }
    @Test
    @DisplayName("Find orders by Kamper ID when multiple orders exist")
    void testFindOrdersByKamperIdWithExistingOrders(){
        int kamperId = 1;
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        when(orderRepository.findByKamperId(kamperId)).thenReturn(Arrays.asList(order1, order2));
        Iterable<Orders> orders = orderService.findByKamperId(kamperId);

        assertNotNull(orders);
        assertTrue(orders.iterator().hasNext());
        verify(orderRepository, times(1)).findByKamperId(kamperId);
    }

}