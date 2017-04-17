package com.lijuyong.startup.repository;

import com.lijuyong.startup.entity.WeChatDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by john on 2017/4/17.
 */
@Repository
public interface WeChatRepository extends JpaRepository<WeChatDO,Integer> {
}
