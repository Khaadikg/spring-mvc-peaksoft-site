package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CompanyDao;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Student;
import peaksoft.service.CompanyService;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyDao companyDao;
    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDao.getAllCompanies();
    }

    @Override
    public void addCompany(Company company) {
        companyDao.addCompany(company);
    }

    @Override
    public Company getCompanyById(long id) {
        return companyDao.getCompanyById(id);
    }

    @Override
    public void updateCompany(long id, Company company) {
        companyDao.updateCompany(id, company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyDao.deleteCompany(company);
    }

    @Override
    public List<Course> getCoursesByCompany(Long companyId) {
        List<Course> courses = companyDao.getCoursesByCompany(companyId);
        return courses;
    }

//    @Override
//    public List<Student> getStudentsByCompany(long companyId) {
//        return companyDao.getCoursesByCompany();
//    }
}
