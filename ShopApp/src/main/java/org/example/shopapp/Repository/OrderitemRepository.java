package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderitemRepository extends JpaRepository<Orderitem, Integer> {
}