package csvPack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

public class CSVMethods {

	// Task 1. Collecting all data and provide JSON format
	static String GetDataCsv() {

		ArrayList<String> order = new ArrayList<String>();
		ArrayList<String> orderDate = new ArrayList<String>();
		ArrayList<String> region = new ArrayList<String>();
		ArrayList<String> rep1 = new ArrayList<String>();
		ArrayList<String> rep2 = new ArrayList<String>();
		ArrayList<String> item = new ArrayList<String>();
		ArrayList<String> units = new ArrayList<String>();
		ArrayList<String> unitCost = new ArrayList<String>();
		ArrayList<String> total = new ArrayList<String>();

		String jsonCSV = null;

		for (ArrayList<String> row : readCSV.getWholeSheet()) {

			order.add(row.get(0));
			orderDate.add(row.get(1));
			region.add(row.get(2));
			rep1.add(row.get(3));
			rep2.add(row.get(4));
			item.add(row.get(5));
			units.add(row.get(6));
			unitCost.add(row.get(7));
			total.add(row.get(8));

			String pattern = "{\"orderInfo\": {\"RadNummer\": \"%s\",\"orderDate\": \"%s\", \"region\": \"%s\","
					+ " \"rep1\": \"%s\", \"rep2\": \"%s\", \"item\": \"%s\", \"units\": \"%s\","
					+ " \"unitPrice\": \"%s\", \"total\": \"%s\"}}";
			jsonCSV = String.format(pattern, order, orderDate, region, rep1, rep2, item, units, unitCost, total);
		}
		return jsonCSV;
	}

	// Task 2. collect data from a specific column, (This case is Regions).
	static ArrayList<String> Column() {
		ArrayList<String> showColumn = new ArrayList<String>();
		for (ArrayList<String> row : readCSV.getWholeSheet()) {
			showColumn.add(row.get(2));
		}
		showColumn.remove(0);
		showColumn.sort(null);
		return showColumn;
	}
	
	// Task 2.1 collect data from a specific column, (This case is unitPrice).
	static ArrayList<String> ColumnNumber() {
		ArrayList<String> showColumn = new ArrayList<String>();
		for (ArrayList<String> row : readCSV.getWholeSheet()) {
			showColumn.add(row.get(7));
		}
		showColumn.remove(0);
		showColumn.sort(Comparator.comparing(Double::parseDouble));
		return showColumn;
	}
	
	// Task 3. Controls the math so the calculations are accurate.
	static ArrayList<String> MathControl() {
		ArrayList<String> ControlCheck = new ArrayList<String>();
		for (ArrayList<String> row : readCSV.getWholeSheet()) {
			String unit = (row.get(6));
			String unitPrice = (row.get(7));
			String total = (row.get(8));

			if (unit.equals("Units")) {
			}
			if (unitPrice.equals("UnitCost")) {
			}
			if (total.equals("Total")) {
			} else {

				BigDecimal units = new BigDecimal(unit);
				BigDecimal unitcost = new BigDecimal(unitPrice);
				BigDecimal totalPrice = new BigDecimal(total);
				BigDecimal result = units.multiply(unitcost);

				if (result.compareTo(totalPrice) == 0) {
				} else {
					ControlCheck.add("something wrong on line: " + row.get(0));
				}
			}
		}
		return ControlCheck;
	}
}
