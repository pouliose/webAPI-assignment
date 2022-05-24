package org.cite;

import lombok.AllArgsConstructor;
import org.cite.model.Attribute;
import org.cite.model.Employee;
import org.cite.repository.AttributeRepository;
import org.cite.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Configuration
public class PopulateDataToDb {
    private static final Logger log = LoggerFactory.getLogger(PopulateDataToDb.class);
    private EmployeeRepository employeeRepository;
    private AttributeRepository attributeRepository;

    static List<Employee> updatedEmployees = new ArrayList<>();
    static List<Attribute> updatedAttributes = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void restoreDatabase() {
        log.info("Clearing repositories 1st.");
        employeeRepository.deleteAll();
        attributeRepository.deleteAll();
        employeeRepository.saveAll(generateEmployees());
        attributeRepository.saveAll(generateAttributes());
        log.info("Employees-Attributes saved!");
        //employeeRepository.saveAll(matchAttributesEmployees(attributeRepository.findAll(), employeeRepository.findAll()));
        matchEmployeesWithAttributes(employeeRepository.findAll(), attributeRepository.findAll());

        updateEmployeesAndAttributes();
        //matchAttributesWithEmployees(employeeRepository.findAll(), atttributeRepository.findAll());
        //updateEmployeesAndAttributes();

        log.info("Database setup completed!");
    }

    private static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee( "Greg", LocalDate.of(2020, 1, 3),0);
        Employee employee2 = new Employee( "Oleg", LocalDate.of(2020, 3, 23),1);
        Employee employee3 = new Employee( "Pete", LocalDate.of(2020, 4, 7),2);
        Employee employee4 = new Employee( "Paul", LocalDate.of(2020, 6, 9),1);
        Employee employee5 = new Employee( "Aura", LocalDate.of(2021, 11, 3),1);
        Employee employee6 = new Employee( "Phil", LocalDate.of(2021, 7, 7),1);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        return employees;
    }
    private static List<Attribute> generateAttributes() {
        List<Attribute> attributes = new ArrayList<>();
        Employee employee1 = new Employee( "Greg", LocalDate.of(2020, 1, 3), 0);
        List<Employee> employee1List = new ArrayList<>();
        employee1List.add(employee1);

        Attribute attribute1 = new Attribute("Height", "Tall");
        Attribute attribute2 = new Attribute("Height", "Short");
        Attribute attribute3 = new Attribute("Height", "Medium");
        Attribute attribute4 = new Attribute("Height", "Short");
        Attribute attribute5 = new Attribute("Weight", "Medium");
        Attribute attribute6 = new Attribute("Weight", "Thin");
        Attribute attribute7 = new Attribute("Weight", "Heavy");
        attributes.add(attribute1);
        attributes.add(attribute2);
        attributes.add(attribute3);
        attributes.add(attribute4);
        attributes.add(attribute5);
        attributes.add(attribute6);
        attributes.add(attribute7);
        return attributes;
    }
   private static void matchEmployeesWithAttributes(List<Employee> totalEmployees, List<Attribute> totalAttributes){

        Employee employee1 = totalEmployees.get(0);
        Employee employee2 = totalEmployees.get(1);
        Employee employee3 = totalEmployees.get(2);
        Employee employee4 = totalEmployees.get(3);
        Employee employee5 = totalEmployees.get(4);
        Employee employee6 = totalEmployees.get(5);

        Attribute attribute1 = totalAttributes.get(0);
        Attribute attribute2 = totalAttributes.get(1);
        Attribute attribute3 = totalAttributes.get(2);
        Attribute attribute4 = totalAttributes.get(3);
        Attribute attribute5 = totalAttributes.get(4);
        Attribute attribute6 = totalAttributes.get(5);
        Attribute attribute7 = totalAttributes.get(6);
        List<Attribute> tempAttributes = new ArrayList<>();
        tempAttributes.add(attribute1);
        //employee1.setAttributes(Stream.of(attribute1).collect(Collectors.toList()));
        //employee2.setAttributes(Stream.of(attribute2).collect(Collectors.toList()));

        employee1.setAttributes(Stream.of(attribute1,attribute5).collect(Collectors.toList()));
        updatedEmployees.add(employee1);
        employee2.setAttributes(Stream.of(attribute2,attribute6).collect(Collectors.toList()));
        updatedEmployees.add(employee2);
        employee3.setAttributes(Stream.of(attribute3).collect(Collectors.toList()));
        updatedEmployees.add(employee3);
        employee4.setAttributes(Stream.of(attribute4).collect(Collectors.toList()));
        updatedEmployees.add(employee4);
        employee6.setAttributes(Stream.of(attribute7).collect(Collectors.toList()));
        updatedEmployees.add(employee6);

        attribute1.setEmployees(Stream.of(employee1).collect(Collectors.toList()));
        updatedAttributes.add(attribute1);
        attribute2.setEmployees(Stream.of(employee2).collect(Collectors.toList()));
        updatedAttributes.add(attribute2);
        attribute3.setEmployees(Stream.of(employee3).collect(Collectors.toList()));
        updatedAttributes.add(attribute3);
        attribute4.setEmployees(Stream.of(employee4).collect(Collectors.toList()));
        updatedAttributes.add(attribute4);
        attribute7.setEmployees(Stream.of(employee6).collect(Collectors.toList()));
        updatedAttributes.add(attribute7);
        attribute6.setEmployees(Stream.of(employee2).collect(Collectors.toList()));
        updatedAttributes.add(attribute6);

/*        updatedEmployees = totalEmployees.stream().map(employee ->
        {
            Attribute attributeRandom1 = totalAttributes.get((int) (Math.random() * totalAttributes.subList(0,3).size()));
            Attribute attributeRandom2 = totalAttributes.get((int) (Math.random() * totalAttributes.subList(4,6).size()));
            List<Attribute> attributes = new ArrayList<>();
            attributes.add(attributeRandom1);
            attributes.add(attributeRandom2);
            employee.setAttributes(attributes);

            if(updatedAttributes.contains(attributeRandom1)){
                updatedAttributes.remove(attributeRandom1);
            }
            List<Employee> employees = new ArrayList<>();
            try {
                attributeRandom1.getEmployees();
                employees.add(employee);
            } catch (Exception e){
                attributeRandom1.getEmployees().add(employee);
            }
            attributeRandom1.setEmployees(employees);
            updatedAttributes.add(attributeRandom1);

            if(updatedAttributes.contains(attributeRandom2)){
                updatedAttributes.remove(attributeRandom2);
            }
            employees = new ArrayList<>();
            if(attributeRandom2.getEmployees() == null){
                employees.add(employee);
                attributeRandom2.setEmployees(employees);
            } else
                try {
                    attributeRandom2.getEmployees().add(employee);

                } catch (Exception e){
                    employees.add(employee);
                    attributeRandom2.setEmployees(employees);
                }

            updatedAttributes.add(attributeRandom2);
            return employee; }).collect(Collectors.toList());*/
    }

    private void updateEmployeesAndAttributes(){
        employeeRepository.saveAll(updatedEmployees);
        attributeRepository.saveAll(updatedAttributes);
    }
    private static List<Employee> matchAttributesEmployees(List<Attribute> attributes, List<Employee> employees) {
        /*for (Employee employee : employees) {
            Attribute attributeRandom1 = attributes.get((int) (Math.random() * attributes.subList(0,3).size()));
            Attribute attributeRandom2 = attributes.get((int) (Math.random() * attributes.subList(4,6).size()));
            employee.setAttribute(Stream.of(attributeRandom1, attributeRandom2).collect(Collectors.toList()));
            List<Employee> employeesList =  attributeRandom1.getEmployee();
            employeesList.add(employee);
            attributeRandom1.setEmployee(employeesList);
            attributeRandom2.setEmployee(employeesList);
        }
        log.info("Properties and owners were matched!");*/
        return employees;
    }

    private static void matchAttributesWithEmployees(List<Employee> totalEmployees, List<Attribute> totalAttributes){

    }
}
