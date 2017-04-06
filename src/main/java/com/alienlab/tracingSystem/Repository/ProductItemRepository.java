package com.alienlab.tracingSystem.Repository;

import com.alienlab.tracingSystem.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by master on 2017/4/6.
 */
@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    List<ProductItem> findProductItemsByProductInfoByProductInfoIdOrderByIdDesc(ProductInfo productInfo);
    @Transactional
    void deleteByDescItemByDetailcontentId(DescItem item);
}
