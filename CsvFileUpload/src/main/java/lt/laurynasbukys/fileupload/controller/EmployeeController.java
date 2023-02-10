package lt.laurynasbukys.fileupload.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.record.Record;

import lt.laurynasbukys.fileupload.entity.Employee;
import lt.laurynasbukys.fileupload.repository.EmployeeRepository;

@CrossOrigin
@RestController
//@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository service;
	
	@GetMapping({"/employees", "", "/", "/index"})
    public List<Employee> getAllEmployees() {

        return service.findAll();
    }

	@PostMapping("/upload")
	public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
		List<Employee> employeeList =new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
		parseAllRecords.forEach(record -> {
			Employee employeee = new Employee();
			employeee.setName(record.getString("name"));
			employeee.setEmail(record.getString("email"));
			employeee.setPhone(record.getString("phoneNumber"));
			employeeList.add(employeee);
		});
		
	
		
		service.saveAll(employeeList);
		return "Upload succsessfull!!";
	}
	
	@GetMapping("/test")
	public String testData() {
		return "veikia";
	}
}
