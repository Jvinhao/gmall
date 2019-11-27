package org.lf.gmall.api.service;

import org.lf.gmall.api.model.PmsSkuInfo;

import java.util.List;

public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getItemById(String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}
