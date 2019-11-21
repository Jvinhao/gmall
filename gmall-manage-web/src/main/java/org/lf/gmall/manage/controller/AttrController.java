package org.lf.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.lf.gmall.api.model.PmsBaseAttrInfo;
import org.lf.gmall.api.model.PmsBaseAttrValue;
import org.lf.gmall.api.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {

    @Reference
    private AttrService attrService;

    @RequestMapping("/attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(@RequestParam("catalog3Id") String catalog3Id) {
        return attrService.getAttrInfoList(catalog3Id);
    }

    @RequestMapping("/getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(@RequestParam("attrId") String attrId) {
        return attrService.getAttrValueList(attrId);
    }

    @RequestMapping("/saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        attrService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }

}
