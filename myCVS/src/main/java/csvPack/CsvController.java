package csvPack;

import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvController {
	
	// Task 1. Collecting all data and provide JSON format, first way.
	@RequestMapping(value = "/showCSV1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showCSV1() {
		return CSVMethods.GetDataCsv();
	}
	
	// Task 1.1 Collecting all data and provide JSON format, second way.
	@RequestMapping(value = "/showCSV2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ArrayList<String>> showCSV2() {
		return readCSV.getWholeSheet();
	}

	// Task 2. Collect data from a specific column(/1 for column 1, /2 for column 2 etc).
	@RequestMapping(value = "{column}", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<String> ColumnPickerByPath(
	  @PathVariable("column") String id) {
	    return CSVMethods.Column(id);
	}
	
	// Task 3. Controls the math so the calculations are accurate.
	@RequestMapping(value = "/mathcheck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<String> mathcheck() {
		return CSVMethods.MathControl();
	}
}
