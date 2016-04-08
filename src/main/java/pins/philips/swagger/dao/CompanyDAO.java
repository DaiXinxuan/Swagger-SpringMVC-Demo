package pins.philips.swagger.dao;


import pins.philips.swagger.entity.CompanyEntity;

import java.util.List;

/**
 * Created by 310231492 on 2016/4/5.
 */
public interface CompanyDAO {
    CompanyEntity getCompany(int index);

    List<CompanyEntity> getAllCompanies();

    void addCompany(CompanyEntity companyEntity);

    void updateCompany(int index, CompanyEntity companyEntity);
}