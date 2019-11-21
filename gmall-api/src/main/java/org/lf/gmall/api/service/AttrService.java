package org.lf.gmall.api.service;

import org.lf.gmall.api.model.PmsBaseAttrInfo;
import org.lf.gmall.api.model.PmsBaseAttrValue;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    void saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);
}
