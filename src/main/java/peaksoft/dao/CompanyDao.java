package peaksoft.dao;

import peaksoft.entities.Company;
import peaksoft.entities.Course;

import java.util.List;

public interface CompanyDao {
    List<Company> getAllCompanies();
    void addCompany(Company company);
    Company getCompanyById(long id);
    void updateCompany(long id, Company company);
    void deleteCompany(Company company);
    List<Course> getCoursesByCompany(Long companyId);
}
