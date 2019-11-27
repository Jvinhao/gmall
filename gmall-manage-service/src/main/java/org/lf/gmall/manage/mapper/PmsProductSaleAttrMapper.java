package org.lf.gmall.manage.mapper;

import org.apache.ibatis.annotations.Param;
import org.lf.gmall.api.model.PmsProductSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId") String skuId);
}
