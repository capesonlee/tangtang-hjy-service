package com.lijuyong.startup.repository;

import com.lijuyong.startup.entity.MemberDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Created by john on 2017/4/16.
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberDO,Integer> {
}
