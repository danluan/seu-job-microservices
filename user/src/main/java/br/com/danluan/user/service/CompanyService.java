package br.com.danluan.user.service;

import br.com.danluan.user.model.Company;
import br.com.danluan.user.model.dto.CompanyDTO;
import br.com.danluan.user.model.dto.CompanyUpdateDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getAllCompanies();
    public CompanyDTO getCompanyById(Integer id);
    public Company getCompanyEntityById(Integer id);
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(CompanyUpdateDTO companyUpdateDTO);
    void deleteCompany(Integer id);
    CompanyDTO toDTO(Company company);
    Company toEntity(CompanyDTO companyDTO);
}
