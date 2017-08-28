package ua.model;

public class ParameterModel {
	 public static final int DEFAULT_LIMIT = 10000;


	    public int getLimit(String strLimit) {
	        return (strLimit == null || strLimit.trim().isEmpty() || !isNumeric(strLimit)) ? DEFAULT_LIMIT : Integer.valueOf(strLimit.trim());
	    }

	   

	    public int getLength(String str) {
	        return str == null || !isNumeric(str) ? -1 : Integer.valueOf(str.trim());
	    }

	    public String getQuery(String str) {
	        return str != null && !str.trim().isEmpty() ? str : "";
	    }

	    public boolean isNumeric(String str) {
	        return str.trim().matches("-?\\d+(\\.\\d+)?");
	    }

}
