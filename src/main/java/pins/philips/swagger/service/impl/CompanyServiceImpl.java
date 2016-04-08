package pins.philips.swagger.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pins.philips.swagger.dao.CompanyDAO;
import pins.philips.swagger.entity.CompanyEntity;
import pins.philips.swagger.service.CompanyService;

import java.util.List;

/**
 * Created by 310231492 on 2016/4/5.
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDAO companyDAO;


    public CompanyEntity getCompany(int index) {
        return companyDAO.getCompany(index);
    }

    public List<CompanyEntity> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    public void addCompany(CompanyEntity companyEntity) {
        companyDAO.addCompany(companyEntity);
    }

    public void updateCompany(int index, CompanyEntity companyEntity) {
        companyDAO.updateCompany(index, companyEntity);
    }
}
