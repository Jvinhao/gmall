package org.lf.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.lf.gmall.api.model.PmsProductSaleAttr;
import org.lf.gmall.api.model.PmsSkuInfo;
import org.lf.gmall.api.model.PmsSkuSaleAttrValue;
import org.lf.gmall.api.service.SkuService;
import org.lf.gmall.api.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class ItemController {

    @Reference
    private SkuService skuService;

    @Reference
    private SpuService spuService;

    @RequestMapping("/{skuId}.html")
    public String item(@PathVariable("skuId") String skuId, Model model) {
        //获得当前商品信息
        PmsSkuInfo skuInfo = skuService.getItemById(skuId);
        model.addAttribute("skuInfo",skuInfo);
        //获得当前商品销售属性
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(skuInfo.getProductId(),skuId);
        model.addAttribute("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);

        Map<String,String> skuSaleAttrHash = new HashMap<>();


        List<PmsSkuInfo> pmsSkuInfos = skuService.getSkuSaleAttrValueListBySpu(skuInfo.getProductId());

        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            String k = "";
            String v = pmsSkuInfo.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k += pmsSkuSaleAttrValue.getSaleAttrValueId() + "|";
            }
            skuSaleAttrHash.put(k,v);
        }
        String skuSaleAttrHashStr = JSON.toJSONString(skuSaleAttrHash);
        model.addAttribute("skuSaleAttrHashStr",skuSaleAttrHashStr);

        return "item";
    }
}
