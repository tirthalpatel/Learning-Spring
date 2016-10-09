package com.tirthal.learning.repository.springdata;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tirthal.learning.model.Model;

public interface ModelJpaRepository extends JpaRepository<Model, Long>, ModelJpaRepositoryCustom {
	List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal lowest, BigDecimal highest);		
	
	@Query("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
	List<Model> queryByPriceRangeAndWoodType(@Param("lowest") BigDecimal l,
											 @Param("highest") BigDecimal h,
											 @Param("wood") String w);	
	
	@Query(name="Model.findAllModelsByType")
	List<Model> findModelsByType(@Param("name") String n);
	
	// Additional method to see how to add paging support
	@Query("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
	Page<Model> queryByPriceRangeAndWoodTypeWithPagination(@Param("lowest") BigDecimal l,
														   @Param("highest") BigDecimal h,
														   @Param("wood") String w,
														   Pageable page);
}
