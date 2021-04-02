package csvPack;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CSVMethods {

	// Task 1. Collecting all data and provide JSON format.
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

	// Task 2. Collect data from a specific column.
	static ArrayList<String> Column(String id) {
		int i = Integer.parseInt(id);
		ArrayList<String> showColumn = new ArrayList<String>();
		for (ArrayList<String> row : readCSV.getWholeSheet()) {

			showColumn.add(row.get(i));
		}
		if (i == 0) {
			showColumn.sort(Comparator.comparing(Double::parseDouble));
		} else if (i > 5) {
			showColumn.sort(Comparator.comparing(Double::parseDouble));
		} else if (i == 1) {
			ArrayList<String> datestring = new ArrayList<String>();
			datestring.add(showColumn.get(1));

			Collections.sort(datestring, new Comparator<String>() {
				DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");

				@Override
				public int compare(String line1, String line2) {
					try {
						return DateFormat.parse(line1).compareTo(DateFormat.parse(line2));
					} catch (ParseException e) {
						throw new IllegalArgumentException(e);
					}
				}
			});
		}

		else {
			showColumn.sort(null);
		}
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
					ControlCheck.add(
							"Units: " + row.get(6) + " " + "UnitCost: " + row.get(7) + " " + "Total: " + row.get(8));
				}
			}
		}
		return ControlCheck;
	}
}
