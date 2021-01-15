package com.example.demo.infra;


import com.example.demo.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tao.lin
 * @date 2021/1/15
 */
public interface LoanJpa extends JpaRepository<LoanEntity, Long> {

}
