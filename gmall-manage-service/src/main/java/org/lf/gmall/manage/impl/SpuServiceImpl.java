package org.lf.gmall.manage.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.lf.gmall.api.model.*;
import org.lf.gmall.api.service.SpuService;
import org.lf.gmall.manage.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    private PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Autowired
    private PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    private PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    private PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    /**
     * 通过id查询本Id的所有商品
     * @param catalog3Id
     * @return
     */
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    /**
     * 查询基本属性
     * @return
     */
    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }

    /**
     * 保存商品Spu
     * @param pmsProductInfo
     */
    @Override
    public void saveSpuInfo(PmsProductInfo pmsProductInfo) {
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        String productId = pmsProductInfo.getId();
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        spuSaleAttrList.forEach((spuSaleAttr) -> {
            spuSaleAttr.setProductId(productId);
            pmsProductSaleAttrMapper.insertSelective(spuSaleAttr);
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            spuSaleAttrValueList.forEach((spuSaleAttrValue) -> {
                spuSaleAttrValue.setProductId(productId);
                pmsProductSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
            });
        });
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        spuImageList.forEach((spuImage) -> {
            spuImage.setProductId(productId);
            pmsProductImageMapper.insertSelective(spuImage);
        });
    }

    /**
     * 查询商品图片
     * @param spuId
     * @return
     */

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        return pmsProductImageMapper.select(pmsProductImage);
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);

        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);

        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();

            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());
            pmsProductSaleAttrValue.setProductId(spuId);

            List<PmsProductSaleAttrValue> productSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);

            productSaleAttr.setSpuSaleAttrValueList(productSaleAttrValues);
        }


        return pmsProductSaleAttrs;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId) {
        List<PmsProductSaleAttr> productSaleAttrs = pmsProductSaleAttrMapper.selectSpuSaleAttrListCheckBySku(productId,skuId);
        return productSaleAttrs;
    }
}
