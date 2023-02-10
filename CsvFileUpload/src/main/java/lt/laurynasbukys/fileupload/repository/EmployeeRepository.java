package lt.laurynasbukys.fileupload.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.laurynasbukys.fileupload.entity.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
