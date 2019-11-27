package org.lf.gmall.manage.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.lf.gmall.api.model.PmsSkuAttrValue;
import org.lf.gmall.api.model.PmsSkuImage;
import org.lf.gmall.api.model.PmsSkuInfo;
import org.lf.gmall.api.model.PmsSkuSaleAttrValue;
import org.lf.gmall.api.service.SkuService;
import org.lf.gmall.manage.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    private PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    private PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    private PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        // 插入sku商品属性
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);

        String skuId = pmsSkuInfo.getId();

        //插入图片
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

        //插入属性值
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        //插入销售属性值
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

    }

    @Override
    public PmsSkuInfo getItemById(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        // 查询图片
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);

        skuInfo.setSkuImageList(pmsSkuImages);

//        // 查询商品属性
//        PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue();
//        pmsSkuAttrValue.setSkuId(skuInfo.getId());
//        List<PmsSkuAttrValue> pmsSkuAttrValues = pmsSkuAttrValueMapper.select(pmsSkuAttrValue);
//        skuInfo.setSkuAttrValueList(pmsSkuAttrValues);
//
//        //查询销售销售属性
//        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
//        pmsSkuSaleAttrValue.setSkuId(skuInfo.getId());
//        List<PmsSkuSaleAttrValue> skuSaleAttrValues = pmsSkuSaleAttrValueMapper.select(pmsSkuSaleAttrValue);
//        skuInfo.setSkuSaleAttrValueList(skuSaleAttrValues);

        return skuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        List<PmsSkuInfo> pmsSkuInfos =pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfos;
    }
}
