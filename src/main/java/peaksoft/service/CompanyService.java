package peaksoft.service;

import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Student;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void addCompany(Company company);
    Company getCompanyById(long id);
    void updateCompany(long id, Company company);
    void deleteCompany(Company company);
    List<Course> getCoursesByCompany(Long companyId);
//    public List<Student> getStudentsByCompany(long companyId)
}
