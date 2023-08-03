package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.dto.CustomerDto;
import com.example.graduationprojectbe.dto.projection.CustomerInfo;
import com.example.graduationprojectbe.dto.projection.CustomerProjection;
import com.example.graduationprojectbe.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);

    @Query("select c from Customer c where c.id = ?1 and c.delete = true ")
    Optional<CustomerInfo> getCustomerById(Integer id);

    @Query("select new com.example.graduationprojectbe.dto.dto.CustomerDto" +
            "(c.id, c.fullName, c.phoneNumber, c.email, c.address, count(p)) " +
            "from Customer c " +
            "left join Product p on p.customer.id = c.id " +
            "where c.id = ?1 and c.delete = true " +
            "group by c.id ")
    CustomerDto searchProductByCustomerID(Integer id);

    @Query("select new com.example.graduationprojectbe.dto.dto.CustomerDto" +
            "(c.id, c.fullName, c.phoneNumber, c.email, c.address, count(p)) " +
            "from Customer c " +
            "left join Product p on p.customer.id = c.id " +
            "where c.delete = true " +
            "group by c.id order by count(p) desc ")
    List<CustomerDto> productByCustomerAll();

    @Query("select c from Customer c where (c.email like %?1% or c.phoneNumber like %?1% or c.fullName like %?1% ) and c.delete = true ")
    Page<CustomerProjection> findProductByCustomers(Pageable pageable, String term);
}