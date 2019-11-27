package org.lf.gmall.api.service;

import org.lf.gmall.api.model.PmsBaseSaleAttr;
import org.lf.gmall.api.model.PmsProductImage;
import org.lf.gmall.api.model.PmsProductInfo;
import org.lf.gmall.api.model.PmsProductSaleAttr;

import java.util.List;


public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId);
}
