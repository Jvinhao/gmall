package org.lf.gmall.api.service;

import org.lf.gmall.api.model.PmsBaseCatalog1;
import org.lf.gmall.api.model.PmsBaseCatalog2;
import org.lf.gmall.api.model.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {

    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
