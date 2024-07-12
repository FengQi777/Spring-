package com.example.demo.data;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.TacoOrder;


public interface OrderRepository extends CrudRepository<TacoOrder,Long>{

  List<TacoOrder> findByDeliveryZip(String deliveryZip);

}