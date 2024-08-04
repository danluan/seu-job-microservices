package br.com.danluan.user.resource;

import br.com.danluan.user.exception.UserIdAlreadyInUseException;
import br.com.danluan.user.model.dto.CompanyDTO;
import br.com.danluan.user.model.dto.CompanyUpdateDTO;
import br.com.danluan.user.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Integer id) {
        try {
            CompanyDTO companyDTO = companyService.getCompanyById(id);
            return new ResponseEntity<>(companyDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<CompanyDTO> getCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        try {
            CompanyDTO createdCompany = companyService.createCompany(companyDTO);
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
        } catch (UserIdAlreadyInUseException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@Valid @RequestBody CompanyUpdateDTO companyUpdateDTO, @PathVariable Integer id) {
        try {
            companyUpdateDTO.setId(id);
            CompanyDTO company = companyService.updateCompany(companyUpdateDTO);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        try {
            companyService.deleteCompany(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}