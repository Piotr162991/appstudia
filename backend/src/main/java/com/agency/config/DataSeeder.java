package com.agency.config;

import com.agency.employees.EmployeeModel;
import com.agency.employees.EmployeeRepository;
import com.agency.employer.EmployerModel;
import com.agency.employer.EmployerRepository;
import com.agency.offer.JobOfferModel;
import com.agency.offer.JobOfferRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class DataSeeder {
    @Autowired
    private EmployerRepository employerRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private JobOfferRepository jobOfferRepository;

    @PostConstruct
    @Transactional
    public void seedData() {
        clearDatabase();
        
        // Create and save employers first
        List<EmployerModel> employers = employerRepository.saveAll(createEmployers());

        // Create and save job offers
        List<JobOfferModel> jobOffers = createJobOffers(employers);
        jobOfferRepository.saveAll(jobOffers);

        // Create employees and associate them with job offers
        List<EmployeeModel> employees = createEmployees(jobOffers);
        employeeRepository.saveAll(employees);
    }

    private void clearDatabase() {
        employeeRepository.deleteAllInBatch();
        jobOfferRepository.deleteAllInBatch();
        employerRepository.deleteAllInBatch();
    }

    private List<EmployerModel> createEmployers() {
        EmployerModel techCorp = new EmployerModel();
        techCorp.setCompanyName("TechCorp Solutions");
        techCorp.setAddress("789 Tech Avenue");
        techCorp.setCity("San Francisco");
        techCorp.setPostalCode("94105");
        techCorp.setPhone("+1-555-0100");
        techCorp.setEmail("contact@techcorp.com");
        techCorp.setWebsite("www.techcorp.com");
        techCorp.setCompanyDescription("Leading software development company");
        techCorp.setFoundedYear(2010);
        techCorp.setIndustry("Technology");
        techCorp.setNumberOfEmployees(500);

        EmployerModel bioMed = new EmployerModel();
        bioMed.setCompanyName("BioMed Research");
        bioMed.setAddress("321 Science Park");
        bioMed.setCity("Boston");
        bioMed.setPostalCode("02110");
        bioMed.setPhone("+1-555-0200");
        bioMed.setEmail("contact@biomed.com");
        bioMed.setWebsite("www.biomed.com");
        bioMed.setCompanyDescription("Innovative biotech research company");
        bioMed.setFoundedYear(2005);
        bioMed.setIndustry("Biotechnology");
        bioMed.setNumberOfEmployees(300);

        return Arrays.asList(techCorp, bioMed);
    }

    private List<JobOfferModel> createJobOffers(List<EmployerModel> employers) {
        List<JobOfferModel> offers = new ArrayList<>();
        
        // Jobs for first employer (TechCorp)
        JobOfferModel softwareEngineer = new JobOfferModel();
        softwareEngineer.setTitle("Senior Software Engineer");
        softwareEngineer.setDescription("Java/Spring developer position");
        softwareEngineer.setEmployer(employers.get(0));
        softwareEngineer.setLocation("San Francisco");
        softwareEngineer.setEmploymentType("Full-time");
        softwareEngineer.setSalaryFrom(BigDecimal.valueOf(120000));
        softwareEngineer.setSalaryTo(BigDecimal.valueOf(150000));
        softwareEngineer.setPublicationDate(LocalDateTime.now());
        softwareEngineer.setExpirationDate(LocalDateTime.now().plusMonths(1));
        offers.add(softwareEngineer);

        // Jobs for second employer (BioMed)
        JobOfferModel researchScientist = new JobOfferModel();
        researchScientist.setTitle("Senior Research Scientist");
        researchScientist.setDescription("Biotechnology research position");
        researchScientist.setEmployer(employers.get(1));
        researchScientist.setLocation("Boston");
        researchScientist.setEmploymentType("Full-time");
        researchScientist.setSalaryFrom(BigDecimal.valueOf(90000));
        researchScientist.setSalaryTo(BigDecimal.valueOf(120000));
        researchScientist.setPublicationDate(LocalDateTime.now());
        researchScientist.setExpirationDate(LocalDateTime.now().plusMonths(1));
        offers.add(researchScientist);

        return offers;
    }

    private List<EmployeeModel> createEmployees(List<JobOfferModel> jobOffers) {
        List<EmployeeModel> employees = new ArrayList<>();

        // First employee applying to first company's jobs
        EmployeeModel john = new EmployeeModel();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setEmail("john.doe@example.com");
        john.setPhone("+1-555-0123");
        john.setDateOfBirth(LocalDate.of(1990, 1, 15));
        john.setAddress("123 Main St");
        john.setCity("San Francisco");
        john.setPostalCode("94105");
        john.setResume("resume_john_doe.pdf");
        john.setAppliedJobs(jobOffers.stream()
                .filter(jo -> jo.getEmployer().equals(jobOffers.get(0).getEmployer()))
                .collect(Collectors.toSet()));
        employees.add(john);

        // Second employee applying to second company's jobs
        EmployeeModel jane = new EmployeeModel();
        jane.setFirstName("Jane");
        jane.setLastName("Smith");
        jane.setEmail("jane.smith@example.com");
        jane.setPhone("+1-555-0124");
        jane.setDateOfBirth(LocalDate.of(1992, 5, 20));
        jane.setAddress("456 Market St");
        jane.setCity("Seattle");
        jane.setPostalCode("98101");
        jane.setResume("resume_jane_smith.pdf");
        jane.setAppliedJobs(jobOffers.stream()
                .filter(jo -> jo.getEmployer().equals(jobOffers.get(1).getEmployer()))
                .collect(Collectors.toSet()));
        employees.add(jane);

        return employees;
    }
}