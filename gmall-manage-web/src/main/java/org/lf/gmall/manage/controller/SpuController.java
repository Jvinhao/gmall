package org.lf.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.lf.gmall.api.model.PmsBaseSaleAttr;
import org.lf.gmall.api.model.PmsProductImage;
import org.lf.gmall.api.model.PmsProductInfo;
import org.lf.gmall.api.model.PmsProductSaleAttr;
import org.lf.gmall.api.service.SpuService;
import org.lf.gmall.manage.utils.ProImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {

    @Reference
    private SpuService spuService;

    @RequestMapping("/spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(@RequestParam("catalog3Id") String catalog3Id) {
        return spuService.spuList(catalog3Id);
    }

    @RequestMapping("/baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return spuService.baseSaleAttrList();
    }

    /**
     * 图片上传
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        return ProImageUtil.upLoadImage(multipartFile);
    }

    @RequestMapping("/saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }

    /**
     * 获取图片
     * @param spuId
     * @return
     */

    @RequestMapping("/spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(@RequestParam("spuId") String spuId) {
        return spuService.spuImageList(spuId);
    }

    @RequestMapping("/spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(@RequestParam("spuId") String spuId) {
        return spuService.spuSaleAttrList(spuId);
    }


}
