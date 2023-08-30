package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.Catalog;
import ra.repository.impl.CatalogRepository;
import ra.service.ICatalog;

import java.util.List;
@Service
public class CatalogService implements ICatalog {
    @Autowired
    private CatalogRepository catalogRepository;
    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog findById(Long id) {
        return catalogRepository.findById(id);
    }

    @Override
    public void save(Catalog catalog) {
        catalogRepository.save(catalog);
    }

    @Override
    public void delete(Long id) {
        catalogRepository.delete(id);
    }
}
