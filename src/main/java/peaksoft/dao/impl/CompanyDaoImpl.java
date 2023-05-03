package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.CompanyDao;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = manager.createQuery("FROM Company", Company.class).getResultList();
        return companies;
    }

    @Override
    public void addCompany(Company company) {
        manager.persist(company);
    }

    @Override
    public Company getCompanyById(long id) {
        Company company = manager.find(Company.class, id);
        return company;
    }

    @Override
    public void updateCompany(long id, Company company) {
        Company company1 = getCompanyById(id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        manager.merge(company1);
    }

    @Override
    public void deleteCompany(Company company) {
        manager.remove(manager.contains(company)? company : manager.merge(company));
    }
    @Override
    public List<Course> getCoursesByCompany(Long companyId) {
        List<Course> courses = manager.createQuery("select c from  Course c join c.company com where com.id=:key", Course.class)
                .setParameter("key", companyId).getResultList();
        return courses;
    }

}
