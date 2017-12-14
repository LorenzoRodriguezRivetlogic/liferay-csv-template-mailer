package com.rivetlogic.csvtemplatemailer.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class Utils {
	public static String DEFAULT_COLUMN_NAME = "Column";

	public static String formatColumnName(String columnName) {
		columnName = columnName.toLowerCase();
		columnName = columnName.replace(" ", "_");
		
		return "@{" + columnName + "}";
	}
	
	public static String generateHtmlTable(List<FileColumn> columns) {
		int countCol = 1;
		
		String tableStart = "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:500px;\">"+
								"<tbody>"+
									"<tr>";
		String columnsName = "";
		for (FileColumn fileColumn : columns) {
			if (!fileColumn.getName().equals("")){
				columnsName += "<td>"+ fileColumn.getName() +"</td>";
			} else {
				columnsName += "<td>"+ DEFAULT_COLUMN_NAME + countCol +"</td>";
			}
			
		}
		
		String rowChange = "</tr><tr>";
		
		String columnsData = "";
		for (FileColumn fileColumn : columns) {
			columnsData += "<td>"+ formatColumnName(fileColumn.getName()) +"</td>";
		}
		
		String tableEnd = "</tr></tbody></table>";
		
		return tableStart + columnsName + rowChange + columnsData + tableEnd;
	}
	
	public static String removeEscapeChars(String arg) {
		arg = arg.replace("\n", "");
		arg = arg.replace("\t", "");
		arg = arg.replace("&nbsp;", " ");
		
		return arg;
	}
	
	public static boolean valueIsInArray(String[] values, String value) {
		for (String string : values) {
			if(value.equals(string)){
				return true;
			}
		}
		return false;
	}
	
	public static List<FileColumn> deserializeColumns(String data) {
		List<FileColumn> result = new ArrayList<>();
		JSONArray params;
		
		try {
			params = JSONFactoryUtil.createJSONArray(data);
			
			for (int i = 0; i < params.length(); i++) {
				JSONObject val = params.getJSONObject(i);
				FileColumn col =(FileColumn) JSONFactoryUtil.looseDeserialize(val.toString(), FileColumn.class);

				result.add(col);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String replaceDataFirstRow(String fileId, List<FileColumn> params, FileColumn email, String template) {
		Map<String, String> firstRow = FileUtil.getFirstDataRow(fileId, params, email);
		String result = template;
		for (Map.Entry<String, String> entry : firstRow.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    
		    if (key.equals(WebKeys.EMAIL_TO_SEND)) {
		    	continue;
		    }
		    
		    result = result.replace(key, value);
		}
		
		
		return removeUnwantedColumns(result);
	}
	
	public static String removeUnwantedColumns(String data) {
		
		Pattern pattern = Pattern.compile("(\\$\\{)(.*?)(\\})");
		Matcher matcher = pattern.matcher(data);
		
		List<String> listMatches = new ArrayList<String>();

        while(matcher.find()) {
            listMatches.add(matcher.group(0));
        }

        for(String s : listMatches) {
        	data = data.replace(s, "");
        }
		
		return data;
	}
	
	public static boolean haveRepeatedColumns(List<FileColumn> columns) {
		
		Set<String> elements = new HashSet<>();
		
		for (FileColumn fileColumn : columns) {
			if(elements.add(fileColumn.getName()) == false){
				return true;
			}
		}
		
		return false;
	}
}
