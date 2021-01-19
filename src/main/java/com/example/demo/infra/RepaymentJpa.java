package com.example.demo.infra;

import com.example.demo.entity.RepaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tao.lin
 * @date 2021/1/19
 */
public interface RepaymentJpa extends JpaRepository<RepaymentEntity, Long> {
}
