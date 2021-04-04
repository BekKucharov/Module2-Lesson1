package uz.pdp.springbootlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootlesson1task1.entity.Address;
import uz.pdp.springbootlesson1task1.entity.ApiResponse;
import uz.pdp.springbootlesson1task1.entity.Company;
import uz.pdp.springbootlesson1task1.payload.CompanyDto;
import uz.pdp.springbootlesson1task1.repository.AddressRepository;
import uz.pdp.springbootlesson1task1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Company> getAll(){
        return companyRepository.findAll();
    }
    public ApiResponse getById(Integer id){
        Optional<Company> companyId = companyRepository.findById(id);
        if (!companyId.isPresent()) return new ApiResponse("Company not found", false, null);
        Company company = companyId.get();
        return new ApiResponse("Company found!", true, company);
    }
    public ApiResponse add(CompanyDto companyDto){
        boolean exists = companyRepository.existsByCorpNameAndDirectorName(companyDto.getCorpName(), companyDto.getDirectorName());
        if (exists) return new ApiResponse("This company is already exists", false, null);
        Company company = new Company();
        company.setCorpName(company.getCorpName());
        company.setDirectorName(company.getDirectorName());

        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address addressSaved = addressRepository.save(address);
        company.setAddress(addressSaved);
        Company save = companyRepository.save(company);
        return new ApiResponse("Company added", true, save);
    }
    public ApiResponse edit(Integer id, CompanyDto companyDto){
        boolean exists = companyRepository.existsByCorpNameAndDirectorNameAndIdNot(companyDto.getCorpName(), companyDto.getDirectorName(), id);
        if (exists) return new ApiResponse("This company is already exists", true, null);
        Optional<Company> companyId = companyRepository.findById(id);
        if (!companyId.isPresent()) return new ApiResponse("Company not found", false, null);
        Company company = companyId.get();
        company.setCorpName(company.getCorpName());
        company.setDirectorName(company.getDirectorName());

        Optional<Address> addressId = addressRepository.findById(id);
        if (!addressId.isPresent()) return new ApiResponse("Address not found", false, null);
        Address address = addressId.get();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address addressSaved = addressRepository.save(address);
        company.setAddress(addressSaved);
        Company save = companyRepository.save(company);
        return new ApiResponse("Company edited", true, save);
    }
    public ApiResponse del(Integer id){
        Optional<Company> companyId = companyRepository.findById(id);
        if (!companyId.isPresent()) return new ApiResponse("Company not found", false, null);
        companyRepository.deleteById(id);
        addressRepository.deleteById(id);
        return new ApiResponse("Company deleted", true, null);
    }
}








