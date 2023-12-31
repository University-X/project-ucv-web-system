package project.projectucvwebsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.projectucvwebsystem.entity.repository.EmployeeRepository;
import project.projectucvwebsystem.entity.repository.UserRepository;

@Service
@AllArgsConstructor
public class EmployeeService {
    
    @Autowired
    private final EmployeeRepository employeeRepository;

    public List<Object[]> fillEmployeeRow () {return employeeRepository.fillEmployeeRow();}
    
    public void RemoveEmployee (int ID) {
        employeeRepository.DeleteEmployee(ID);
    }

    public void InsertNewEmployee (
        int idUser,
        String email,
        String names,
        String lastname,
        String address
    ) {
        employeeRepository
        .CreateANewEmployee(idUser, email, names, lastname, address);
    }

    /*public void UpdateEmployeeRole (
        String role,
        int ID
    ) {
        /*employeeRepository
        .UpdateEmployeeRole(role, ID);
       
    }*/

    public int RetrieveEmployeeIDByUserID(int idUser) {
        return employeeRepository.RetrieveEmployeeIDByUserID(idUser);
    }

    public void UpdateEmployeeEmail (
        String email,
        int ID
    ) {
        employeeRepository
        .UpdateEmployeeEmail(email, ID);
    }

    public void UpdateEmployeeNames (
        String name,
        int ID
    ) {
        employeeRepository
        .UpdateEmployeeNames(name, ID);
    }

    public void UpdateEmployeeLastname (
        String lastname,
        int ID
    ) {
        employeeRepository
        .UpdateEmployeeLastname(lastname, ID);
    }

    public void UpdateEmployeeAddress (
        String address,
        int ID
    ) {
        employeeRepository
        .UpdateEmployeeAddress(address, ID);
    }

    public int CatchIDEmployee (int ID) {
        return employeeRepository.BeforeRemoveUser(ID);
    }
}
