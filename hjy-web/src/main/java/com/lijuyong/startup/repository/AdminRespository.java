package com.lijuyong.startup.repository;

import com.lijuyong.startup.entity.AdminDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by john on 2017/4/19.
 */
@Repository
public interface AdminRespository extends JpaRepository<AdminDO,String> {
    AdminDO findByName(String loginName);
}
